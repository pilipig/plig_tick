package com.wxb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxb.dao.GradeDao;
import com.wxb.entity.Grade;
import com.wxb.service.GradeService;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

@Service
public class GradeServiceImpl implements GradeService{

	@Autowired
	private GradeDao gDao;
	@Override
	public boolean insert(Grade grade) {
		// TODO Auto-generated method stub
		return gDao.insert(grade) > 0;
	}

	@Override
	public ResultVo update(Grade grade) {
		// TODO Auto-generated method stub
		if(gDao.updateById(grade) > 0) {
			return ResultVo.setOK("修改成功");
		}else {
			return ResultVo.setERROR("修改失败");
		}
	}

	@Override
	public ResultVo delete(int id) {
		// TODO Auto-generated method stub
		if(gDao.deleteById(id) > 0) {
			return ResultVo.setOK("删除成功");
		}else {
			return ResultVo.setERROR("修改失败");
		}
	}

	@Override
	public List<Grade> queryAll() {
		// TODO Auto-generated method stub
		return gDao.queryAll();
	}

	@Override
	public PageVo<Grade> queryByPage(int page, int count) {
		// TODO Auto-generated method stub
		int index = 0;
		if (page > 0) {
			index = (page - 1) * count;
		}

		PageVo<Grade> pv = new PageVo<>();
		pv.setData(gDao.queryByPage(index, count));
		if (pv.getData() != null) {
			pv.setCount(gDao.queryCount().intValue());
		}
		pv.setCode(0);
		pv.setMsg("哦");
		return pv;
	}

	@Override
	public Long queryCount(int index, int count) {
		// TODO Auto-generated method stub
		return gDao.queryCount();
	}

	
}
