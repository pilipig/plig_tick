package com.wxb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxb.entity.Course;
import com.wxb.service.CourseService;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

@Controller
public class CourseController {

	@Autowired
	private CourseService cService;
	
	//添加
	@RequestMapping(value="courseadd.do",method= {RequestMethod.POST})
	public String add(Course course, BindingResult bindResult) {
		if(cService.insert(course)) {
			return "redirect:/courselist.html";
		}else {
			return "courseadd";
		}
	}
	
	//修改
	@RequestMapping(value="courseupdate.do", method= {RequestMethod.POST})
	//@ResponseBody
	public String update(Course course) {
		if(cService.update(course).getCode() == 1000) {
			return "redirect:/courselist.html";
		}else {
			return "courseupdate";
		}
	}
	//删除
	@RequestMapping(value="coursedelete.do",method= {RequestMethod.POST})
	@ResponseBody
	public ResultVo delete(int id) {
		return cService.delete(id);
	}
	
	//分页
	@RequestMapping(value="coursepage.do")
	@ResponseBody
	public PageVo<Course> queryPage(int page, int limit){
		return cService.queryByPage(page, limit);
	}
	
	//展示所有
	@RequestMapping(value="courseall.do")
	@ResponseBody
	public List<Course> queryAll(){
		return cService.queryAll();
	}
	
}
