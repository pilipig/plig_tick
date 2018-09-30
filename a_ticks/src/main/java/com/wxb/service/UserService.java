package com.wxb.service;


import com.wxb.entity.User;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

public interface UserService {

	//修改密码
	public void updatePassword(User user);
	
	
	//修改有效无效
	public boolean updateFlag(int flag,int id);
	
	//分页
	public PageVo<User> queryByPage(int page, int count);
	
	//修改
	public ResultVo update(User user);
}
