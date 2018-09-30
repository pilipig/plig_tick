package com.wxb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxb.entity.Grade;
import com.wxb.service.GradeService;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

@Controller
public class GradeController {

	@Autowired
	private GradeService gService;
	//增加
	@RequestMapping(value="gradeadd.do",method= {RequestMethod.POST})
	public String add(Grade grade) {
		
		if(gService.insert(grade)) {
			return "redirect:/gradelist.html";
		}else {
			return "gradeadd";
		}
	}
	
	//修改
	@RequestMapping(value="gradeupdate.do",method= {RequestMethod.POST})
	public String update(Grade grade) {
		if(gService.update(grade).getCode() == 1000) {
			return "redirect:/gradelist.html";
		}else {
			return "gradeupdate";
		}
	}
	
	//删除
	@RequestMapping(value="gradedelete.do",method= {RequestMethod.POST})
	@ResponseBody
	public ResultVo delete(int id) {
		return gService.delete(id);
	}
	
	//分页
	@RequestMapping(value="gradepage.do")
	@ResponseBody
	public PageVo<Grade> queryPage(int page, int limit){
		return gService.queryByPage(page, limit);
	}
	
	//展示所有
	@RequestMapping(value="gradeall.do")
	@ResponseBody
	public List<Grade> queryAll(){
		return gService.queryAll();
	}
}
