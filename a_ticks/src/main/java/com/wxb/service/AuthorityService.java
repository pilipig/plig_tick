package com.wxb.service;

import java.util.List;
import java.util.Set;

import com.wxb.entity.Authority;
import com.wxb.vo.ResultVo;

public interface AuthorityService {
	public Set<String> findPermitByName(String name);
	public List<Authority> menu();
	public Authority findByAurl(String aurlss);
	
	public void addAuthority(Authority authority);
	public int count();
	public List<Authority> findAllPermits(int page,int size);
	
	
	//删除
	public ResultVo deleteById(int id);
	
	//修改
	public ResultVo update(Authority authority);

}
