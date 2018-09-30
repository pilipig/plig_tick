package com.wxb.service;

import java.util.List;

import com.wxb.entity.Grade;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

public interface GradeService {

	boolean insert(Grade grade);
	ResultVo update(Grade grade);
	//删除
	ResultVo delete(int id);
	//展示所有
	List<Grade> queryAll();
	//分页
	PageVo<Grade> queryByPage(int page,int count);
	//找到总数
	public Long queryCount(int index, int count);
}
