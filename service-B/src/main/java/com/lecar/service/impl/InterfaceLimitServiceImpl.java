package com.lecar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lecar.entity.InterfaceLimit;
import com.lecar.mapper.InterfaceLimitMapper;
import com.lecar.service.InterfaceLimitService;

@Service
public class InterfaceLimitServiceImpl implements InterfaceLimitService {
	
	@Autowired
	private InterfaceLimitMapper mapper;

	@Override
	public InterfaceLimit getEntityByPri(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

}
