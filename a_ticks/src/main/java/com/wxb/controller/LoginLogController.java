package com.wxb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxb.entity.LoginLog;
import com.wxb.service.LoginLogService;
import com.wxb.utils.HttpUtils;
import com.wxb.vo.PageVo;

@Controller
public class LoginLogController {

	@Autowired
	private LoginLogService lService;
	
	@RequestMapping(value="/listloginlog.do")
	@ResponseBody
	public PageVo<LoginLog> queryByPage(int page,int limit){
		return lService.findByPage(page, limit);
	}
	
	
	@RequestMapping("/location.do")
	public void test(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println(request.getRemoteAddr());
		String json=HttpUtils.getLocation(request.getRemoteAddr());
		System.out.println(json);
		response.getWriter().println(json);
	}
	
	
}
