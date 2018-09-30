package com.wxb.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wxb.entity.Student;
import com.wxb.service.StudentService;
import com.wxb.utils.ExcelUtils;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

@Controller
public class StudentController {

	@Autowired
	private StudentService sService;
	
	//分页
	@RequestMapping("studentpage.do")
	@ResponseBody
	public PageVo<Student> page(int page,int limit){
		return sService.queryByPage(page, limit);
	}
	
	//增加
	@RequestMapping(value="studentadd.do",method= {RequestMethod.POST})
	public String add(Student student) {
		if(sService.insert(student)) {
			return "redirect:/studentlist.html";
		}else {
			return "studentadd";
		}
	}
	
	//删除
	@RequestMapping("studentdelete.do")
	@ResponseBody
	public ResultVo del(String no){
		return sService.delete(no);
	}
	
	//展示所有
	@RequestMapping(value="studentall.do")
	@ResponseBody
	public List<Student> queryAll(){
		return sService.queryAll();
	}
	
	//批量导入
	@RequestMapping("studentbatch.do")
	public String batch(@RequestParam MultipartFile mFile, HttpServletRequest request) throws IOException {
		List<Student> list = ExcelUtils.parseExcelS(mFile.getInputStream());
		if(sService.insertBatch(list)) {
			return "redirect:/studentlist.html";
		}else {
			return "studentbatch";
		}
	}
}
