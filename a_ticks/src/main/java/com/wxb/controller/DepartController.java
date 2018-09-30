package com.wxb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxb.entity.Depart;
import com.wxb.service.DepartService;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

@Controller
public class DepartController {

	@Autowired
	private DepartService dService;
	//增加
	@RequestMapping(value="departadd.do",method= {RequestMethod.POST})
	public String add(Depart depart) {
		if(dService.add(depart)) {
			return "redirect:/departlist.html";
		}else {
			return "departadd";
		}
	}
	
	//修改
	@RequestMapping(value="departupdate.do", method= {RequestMethod.POST})
	public String update(Depart depart) {
		if(dService.update(depart).getCode() == 1000) {
			return "redirect:/departlist.html";
		}else {
			return "departupdate";
		}
	}
	
	//删除
	@RequestMapping(value="departdelete.do",method= {RequestMethod.POST})
	@ResponseBody
	public ResultVo delete(int id) {
		return dService.delete(id);
	}
	
	
	//分页
	@RequestMapping(value="departpage.do")
	@ResponseBody
	public PageVo<Depart> queryPage(int page,int limit){
		return dService.queryByPage(page, limit);
	}
	//查询全部
	@RequestMapping("departall.do")
	@ResponseBody
	public List<Depart> queryAll(){
		return dService.queryAll();
	}
}
