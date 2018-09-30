package com.wxb.service;

import java.util.List;

import com.wxb.entity.Depart;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

public interface DepartService {

	//增加 删除 修改
	public boolean add(Depart depart);
	public ResultVo update(Depart depart);
	public ResultVo delete(int id);
	
	//分页 展示所有 总数
	public PageVo<Depart> queryByPage(int page,int count);
	public Long queryCount(int index,int count);
	public List<Depart> queryAll();
}
