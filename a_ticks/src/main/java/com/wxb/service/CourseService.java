package com.wxb.service;

import java.util.List;

import com.wxb.entity.Course;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

public interface CourseService {

	//增加
	boolean insert(Course course);
	//修改
	ResultVo update(Course course);
	ResultVo delete(int id);
	List<Course> queryAll();
	PageVo<Course> queryByPage(int page, int count);
}
