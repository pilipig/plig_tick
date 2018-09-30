package com.wxb.service;

import java.util.List;

import com.wxb.entity.Student;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;


public interface StudentService {

	//新增
	boolean insert(Student student);
	ResultVo update(Student student);
	ResultVo delete(String no);
	//展示所有
	List<Student> queryAll();
	PageVo<Student> queryByPage(int page,int count);
	List<Student> queryByGid(int gid);
	Long queryCountBiGid(int gid);
	//批量导入
	boolean insertBatch(List<Student> list);
	
}
