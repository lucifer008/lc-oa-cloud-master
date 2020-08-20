package com.lecar.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 *
 *
  记录请求轨迹Filter
 * @return
 * @author lucifer
 * @date 2019/12/13 9:22
 */
@Slf4j
@Order(2)
public class ServiceFilter implements Filter {
    final static List<String> nonFilterKeys=new ArrayList<>();
    static {
        nonFilterKeys.add("/exportService/exportExcel");
        nonFilterKeys.add("/recruitService/getRecruitItem");
        //nonFilterKeys.add("loginService/login");
        nonFilterKeys.add("/recruitService/saveOrupdateOffer");
        nonFilterKeys.add("/exportService/exportReportExcel");
        nonFilterKeys.add("/exportService/selectExportExcel");
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("-------->>>>Request Begin>>>>---------->");
        long startTime = System.currentTimeMillis();
        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
        log.info("RemoteHost={}", httpServletRequest.getRemoteHost());
        String method = httpServletRequest.getMethod();
        log.info("Method={}", method);
        String url = httpServletRequest.getRequestURL().toString();
        log.info("URL={}", url);

        //放行静态资源，宋含露
        if (url.contains(".png") || url.contains(".js") || url.contains(".css") || url.contains(".txt")
                || url.contains(".png") || url.contains(".jpg") || url.contains(".pem")) {
            log.info("<--------<<<<Request End<<<<----------");
            chain.doFilter(request, response);
            return;
        }
        //放行特殊请求
        if (nonFilterKeys.stream().anyMatch(f->url.contains(f))) {
            log.info("<--------<<<<Request End<<<<----------");
            chain.doFilter(requestWrapper, response);
            return;
        }
        if (url.contains("loginService/login")){
            String json = ReqHelper.getBodyString(requestWrapper);
            JSONObject jsonObject = JSON.parseObject(json);
            if (jsonObject != null){
                Object UserId = jsonObject.get("UserId");
                log.info("用户{} -------->登录",UserId);
            }
            log.info("<--------<<<<Request End<<<<----------");
            chain.doFilter(requestWrapper, response);
            return;
        }


        String queryString = httpServletRequest.getQueryString();
        log.info("QueryString={}", queryString);
        Map<String, String> headersInfo = ReqHelper.getHeadersInfo(httpServletRequest);
        log.info("Headers={}", headersInfo);
        String json = ReqHelper.getBodyString(requestWrapper);
        log.info("Body={}", json);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        String remoteHost = httpServletRequest.getRemoteHost();
        String remoteXRealHost = httpServletRequest.getParameter("x-real-ip");
        log.info("发送请求IP: remoteHost={},remoteXRealHost={}", remoteHost, remoteXRealHost);
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);
        chain.doFilter(requestWrapper, responseWrapper);
        long endTime = System.currentTimeMillis();
        // 获取response返回的内容并重新写入response
        String result = responseWrapper.getResponseData(response.getCharacterEncoding());
        response.getOutputStream().write(result.getBytes());

        //String uri = httpServletRequest.getRequestURI();
        log.info("URL={}", url);

        log.info("ResponseCode={}", String.valueOf(responseWrapper.getStatus()));
        log.info("ResponseString={}", result);
        log.info("TimeConsuming={}", (int) (endTime - startTime));

        log.info("<--------<<<<Request End<<<<----------");
    }

    @Override
    public void destroy() {

    }



}

class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {


    private final byte[] body;

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = ReqHelper.getBodyString(request).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }
}
class ReqHelper{
    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static  Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }
}
class ResponseWrapper extends HttpServletResponseWrapper {


    private ByteArrayOutputStream buffer = null;

    private ServletOutputStream out = null;

    private PrintWriter writer = null;


    public ResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);

        buffer = new ByteArrayOutputStream();
        out = new WapperedOutputStream(buffer);
        writer = new PrintWriter(new OutputStreamWriter(buffer, "UTF-8"));
    }

    //重载父类获取outputstream的方法
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return out;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return writer;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
        if (writer != null) {
            writer.flush();
        }
    }

    @Override
    public void reset() {
        buffer.reset();
    }

    public String getResponseData(String charset) throws IOException {
        flushBuffer();//将out、writer中的数据强制输出到WapperedResponse的buffer里面，否则取不到数据
        byte[] bytes = buffer.toByteArray();
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }

    }


    /**
     *
     *
     内部类，对ServletOutputStream进行包装，指定输出流的输出端
     * @return
     * @author lucifer
     * @date 2019/12/13 9:25
     */
    private class WapperedOutputStream extends ServletOutputStream {

        private ByteArrayOutputStream bos = null;

        public WapperedOutputStream(ByteArrayOutputStream stream) throws IOException {
            bos = stream;
        }

        //将指定字节写入输出流bos
        @Override
        public void write(int b) throws IOException {
            bos.write(b);
        }

        @Override
        public boolean isReady() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void setWriteListener(WriteListener listener) {
            // TODO Auto-generated method stub

        }
    }
}