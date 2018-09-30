package com.wxb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxb.dao.DepartDao;
import com.wxb.entity.Depart;
import com.wxb.service.DepartService;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

@Service
public class DepartServiceImpl implements DepartService{

	@Autowired
	private DepartDao dDao;

	@Override
	public boolean add(Depart depart) {
		// TODO Auto-generated method stub
		return dDao.add(depart) > 0;
	}

	@Override
	public ResultVo update(Depart depart) {
		// TODO Auto-generated method stub
		if( dDao.update(depart) > 0) {
			return ResultVo.setOK("修改部门成功");
		}else {
			return ResultVo.setERROR("修改部门失败");
		}
	}

	@Override
	public ResultVo delete(int id) {
		// TODO Auto-generated method stub
		if(dDao.delete(id)>0) {
			return ResultVo.setOK("删除部门成功");
		}else {
			return ResultVo.setERROR("删除部门失败");
		}
	}

	@Override
	public PageVo<Depart> queryByPage(int page, int count) {
		// TODO Auto-generated method stub
		PageVo<Depart> vo = new PageVo<>();
		int index = 0;
		if(page > 0) {
			index=(page-1)*count;
		}
		List<Depart> list = dDao.queryByPage(index, count);
		if(list != null) {
			vo.setCode(0);
			vo.setMsg("");
			vo.setData(list);
			vo.setCount(dDao.queryCount().intValue());
		}else {
			vo.setCode(1);
			vo.setMsg("啊啊啊");
			vo.setData(new ArrayList<>());
			vo.setCount(0);
		}
		return vo;
	}

	@Override
	public Long queryCount(int index, int count) {
		// TODO Auto-generated method stub
		return dDao.queryCount();
	}

	@Override
	public List<Depart> queryAll() {
		// TODO Auto-generated method stub
		return dDao.queryAll();
	}
}
