package com.wxb.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxb.entity.Authority;
import com.wxb.service.AuthorityService;
import com.wxb.utils.JsonUtils;
import com.wxb.vo.JsonBean;
import com.wxb.vo.ResultVo;

@Controller
public class AuthorityController {
	
	@Autowired
	private AuthorityService authorityService;
	
	@RequestMapping("/findMenu")
	@ResponseBody
	public List<Authority> menus(){
		
		List<Authority> menu = authorityService.menu();
		
		return menu;
		
	}
	
	@RequestMapping("/authoritylist")
	@ResponseBody
	public Map<String,Object> findAllPermit(int page,int limit){
		Map<String,Object> data = new HashMap<>();
		List<Authority> list = authorityService.findAllPermits(page, limit);
		data.put("count", authorityService.count());
		data.put("data",list);
		data.put("msg","");
		data.put("code",0);
		
		return data;
		
	}
	
	//增加
	@RequestMapping("/addauthority")
	@ResponseBody
	public JsonBean addPermit(String title,String aicon,String aurl,String pid) {
		Authority authority = new Authority();

		try {
			authority.setAicon(aicon);
			authority.setTitle(title);
			if(pid.equals("0")) {
				authority.setParentid(0);
			}else {
				authority.setParentid(authorityService.findByAurl(aurl).getId());
			}
			authority.setType(1);
			authority.setAurl(aurl);
			authorityService.addAuthority(authority);
			return JsonUtils.writeJsonUtils(0, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonUtils.writeJsonUtils(1, e.getMessage());
		}
	}
	
	//s删除
	@RequestMapping(value="authoritydelete.do")
	@ResponseBody
	public ResultVo delete(int id) {
		return authorityService.deleteById(id);
	}
	
	//修改
	@RequestMapping(value="authorityupdate.do",method= {RequestMethod.POST})
	public String update(Authority authority) {
		if(authorityService.update(authority).getCode() == 1000) {
			return "redirect:/authoritylist.html";
		}else {
			return "authorityupdate";
		}
	}
	
}

