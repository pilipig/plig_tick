package com.wxb.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxb.dao.UserDao;
import com.wxb.entity.Role;
import com.wxb.entity.User;
import com.wxb.service.UserService;
import com.wxb.utils.MD5Utils;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao uDao;

	//修改密码
	@Override
	public void updatePassword(User user) {
		// TODO Auto-generated method stub
		user.setPassword(MD5Utils.md5(user.getPassword()));
		try {
			uDao.updatePassword(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public boolean updateFlag(int flag, int id) {
		// TODO Auto-generated method stub
		return uDao.updateFlag(flag, id) > 0;
	}


	//分页
	@Override
	public PageVo<User> queryByPage(int page, int count) {
		// TODO Auto-generated method stub
		int index = 0;
		if(page > 0) {
			index = (page - 1)*count;
		}
		PageVo<User> pv = new PageVo<>();
		pv.setData(uDao.queryByPage(index, count));
		if(pv.getData() != null) {
			pv.setCount(uDao.querycount().intValue());
		}
		pv.setCode(0);
		pv.setMsg("哈哈");
		return pv;
	}


	@Override
	public ResultVo update(User user) {
		// TODO Auto-generated method stub
		if(uDao.updateById(user) > 0) {
			return ResultVo.setOK("修改成功");
		}else {
			return ResultVo.setERROR("修改失败");
		}
	}


	
}
