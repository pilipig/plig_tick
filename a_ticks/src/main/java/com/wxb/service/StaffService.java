package com.wxb.service;

import java.util.List;

import com.wxb.entity.Staff;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

public interface StaffService {

	//增加
	public boolean add(Staff staff);
	//修改
	public ResultVo updateSta(Staff staff);
	//删除
	public ResultVo deleteStaByNo(String no);
	//找到所有
	public List<Staff> findAll();
	//分页
	public PageVo<Staff> findStaByPage(int page, int count);
	
	//标记
	String queryMaxNo();
	boolean insertBatch(List<Staff> list);
}
