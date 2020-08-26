package lc.oa.applayer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
  *
  * @Description:     首页
  * @Author:         lucifer
  * @CreateDate:     2020/8/24 11:45
  * @UpdateUser:
  * @UpdateDate:
  * @UpdateRemark:
  * @Version:        1.0
 */
@RequestMapping("/homeService")
@RestController
public class HomeController {
     /**
      * @method  我的待办
      * @description 
      * @date: 2020/8/26 17:58
      * @author: lucifer
      * @param 
      * @return 
      */
     @RequestMapping("/my-todo")
    public String myTodo(){
        return null;
    }

     /**
      * @method  我的消息
      * @description 
      * @date: 2020/8/26 17:58
      * @author: lucifer
      * @param 
      * @return 
      */
     @RequestMapping("/my-message")
    public String myMessage(){
        return "";
    }

}
