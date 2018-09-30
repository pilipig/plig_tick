package com.wxb.dao;


import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wxb.entity.Authority;


public interface AuthorityDao {
	//登录相关权限
	Set<String> findRoleByName(String name);
	Set<String> findPermitByName(String name);
	Set<Authority> findByName(String name);
	//找到任务栏
	Authority findByTitle(String name);
	Authority findByAurl(String aurl);
	List<Authority> findMenu();
    
	//增加
    int addSelective(Authority authority);
    //删除
    int deleteById(int id);
    //修改
    int updateById(Authority authority);
    
    List<Authority> findPermits(Map<String,Object> info);
    int count();
    
   
  
    
    
   
}