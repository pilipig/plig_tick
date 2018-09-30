package com.wxb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxb.dao.RoleDao;
import com.wxb.entity.Role;
import com.wxb.service.RoleService;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao rDao;

	@Override
	public int add(Role role) {
		// TODO Auto-generated method stub
		return rDao.insert(role);
	}

	@Override
	public List<Role> queryAll() {
		// TODO Auto-generated method stub
		return rDao.queryAll();
	}

	@Override
	public boolean updateAuth(int rid, int[] aids) {
		// TODO Auto-generated method stub
		try {
			rDao.delAuth(rid);
			for (int i = 0; i < aids.length; i++) {
				rDao.insertAuth(rid, aids[i]);
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public PageVo<Role> queryByPage(int page, int count) {
		// TODO Auto-generated method stub
		int index = 0;
		if(page > 0) {
			index = (page - 1)*count;
		}
		PageVo<Role> pv = new PageVo<>();
		pv.setData(rDao.queryByPage(index, count));
		if(pv.getData() != null) {
			pv.setCount(rDao.querycount().intValue());
		}
		pv.setCode(0);
		pv.setMsg("哈哈");
		return pv;
	}

	@Override
	public ResultVo deleteById(int id) {
		// TODO Auto-generated method stub
		if(rDao.delete(id) > 0) {
			return ResultVo.setOK("删除成功");
		}else {
			return ResultVo.setERROR("删除失败");
		}
	}
	
}
