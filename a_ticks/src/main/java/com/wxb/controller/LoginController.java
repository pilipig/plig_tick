package com.wxb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxb.entity.LoginLog;
import com.wxb.service.LoginLogService;
import com.wxb.utils.MD5Utils;
import com.wxb.vo.JsonBean;
import com.wxb.vo.Pages;

@Controller
public class LoginController {

	@Autowired
	private LoginLogService lService;
	
	
	//登录
	@RequestMapping("/login")
	@ResponseBody
	public JsonBean login(String no,String password,HttpServletRequest request) {
		UsernamePasswordToken token = new UsernamePasswordToken(no,MD5Utils.md5(password));
		Subject subject = SecurityUtils.getSubject();
		JsonBean bean = new JsonBean();
		try {
			subject.login(token);
			lService.add(no, request);
			bean.setCode(1);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		return bean;
	}
	//注销
	@RequestMapping("userloginout.do")
	public String loginout(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}
	
	
	
	
}
