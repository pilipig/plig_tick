package com.wxb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxb.entity.Role;
import com.wxb.entity.User;
import com.wxb.service.UserService;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

@Controller
public class UserController {

	@Autowired
	private UserService uService;
	
	//查看用户对应角色
	
	//删除
	@RequestMapping(value="userdel.do")
	@ResponseBody
	public ResultVo delete(int id) {
		if (uService.updateFlag(2, id)) {
			return ResultVo.setOK("恩");
		} else {
			return ResultVo.setERROR("失败");
		}
	}
	//有无效果
	@RequestMapping(value="userchange.do")
	@ResponseBody
	public ResultVo change(int id) {
		 if(uService.updateFlag(1,id)){
	         return ResultVo.setOK("啊");
	     }else{
	         return ResultVo.setERROR("哦?");
	     }
	}

	// 查询 --分页
	@RequestMapping(value ="/userrolepage.do")
	@ResponseBody
	public PageVo<User> queryPage(int page, int limit) {
		return uService.queryByPage(page, limit);
	}

	//修改
	@RequestMapping(value ="userroleedit.do")
	@ResponseBody
	public String update(User user) {
		if(uService.update(user).getCode() == 1000) {
			return "redirect:/userlimit.html";
		}else {
			return "userroleedit";
		}
	}
}
