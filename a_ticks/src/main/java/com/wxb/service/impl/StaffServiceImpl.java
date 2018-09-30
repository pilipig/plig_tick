package com.wxb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxb.dao.StaffDao;
import com.wxb.entity.Staff;
import com.wxb.service.StaffService;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

@Service
public class StaffServiceImpl implements StaffService{

	@Autowired
	private StaffDao sDao;
	@Override
	public boolean add(Staff staff) {
		// TODO Auto-generated method stub
		return sDao.insert(staff) > 0;
	}

	@Override
	public ResultVo updateSta(Staff staff) {
		// TODO Auto-generated method stub
		if(sDao.updateByNo(staff) > 0) {
			return ResultVo.setOK("修改成功");
		}else {
			return ResultVo.setERROR("修改失败");
		}
	}

	@Override
	public ResultVo deleteStaByNo(String no) {
		// TODO Auto-generated method stub
		if(sDao.deleteByNo(no) > 0) {
			return ResultVo.setOK("删除成功");
		}else {
			return ResultVo.setERROR("删除失败");
		}
	}

	@Override
	public List<Staff> findAll() {
		// TODO Auto-generated method stub
		return sDao.queryAll();
	}

	@Override
	public PageVo<Staff> findStaByPage(int page, int count) {
		// TODO Auto-generated method stub
		int index = 0;
		if(page > 0) {
			index = (page - 1)*count;
		}
		PageVo<Staff> pv = new PageVo<>();
		pv.setData(sDao.queryByPage(index, count));
		if(pv.getData() != null) {
			pv.setCount(sDao.queryCount().intValue());
		}
		pv.setCode(0);
		pv.setMsg("啊?");
		
		return pv;
	}

	@Override
	public String queryMaxNo() {
		// TODO Auto-generated method stub
		return sDao.queryMaxNo();
	}

	@Override
	public boolean insertBatch(List<Staff> list) {
		// TODO Auto-generated method stub
		try {
			for(Staff s:list) {
				sDao.insert(s);
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
