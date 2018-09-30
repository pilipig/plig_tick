package com.wxb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxb.dao.CourseDao;
import com.wxb.entity.Course;
import com.wxb.service.CourseService;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseDao cDao;
	@Override
	public boolean insert(Course course) {
		// TODO Auto-generated method stub
		return cDao.insert(course) > 0; 
	}

	@Override
	public ResultVo update(Course course) {
		// TODO Auto-generated method stub
		if(cDao.updateById(course) > 0) {
			return ResultVo.setOK("修改成功");
		}else {
			return ResultVo.setERROR("修改失败");
		}
	}

	@Override
	public ResultVo delete(int id) {
		// TODO Auto-generated method stub
		if(cDao.deleteById(id) > 0) {
			return ResultVo.setOK("删除成功");
		}else {
			return ResultVo.setERROR("删除失败");
		}
	}

	@Override
	public List<Course> queryAll() {
		// TODO Auto-generated method stub
		return cDao.queryAll();
	}

	@Override
	public PageVo<Course> queryByPage(int page, int count) {
		// TODO Auto-generated method stub
		int index = 0;
		if(page > 0) {
			index = (page - 1)*count;
		}
		PageVo<Course> pv = new PageVo<>();
		pv.setData(cDao.queryByPage(index, count));
		if(pv.getData() != null) {
			pv.setCount(cDao.queryCount().intValue());
		}
		pv.setCode(0);
		pv.setMsg("好");
		return pv;
	}

}
