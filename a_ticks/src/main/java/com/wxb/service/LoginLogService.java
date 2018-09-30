package com.wxb.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wxb.entity.LoginLog;
import com.wxb.vo.PageVo;

public interface LoginLogService {

	//展示所有
	public List<LoginLog> findAllLog();
	
	//分页
	public PageVo<LoginLog> findByPage(int page, int count);
	
	boolean add(String no,HttpServletRequest request);
	
}
