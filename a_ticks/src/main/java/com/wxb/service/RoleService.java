package com.wxb.service;

import java.util.List;

import com.wxb.entity.Role;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

public interface RoleService {

	//新增
    int add(Role role);
    //查询
    List<Role> queryAll();
    //修改角色对应的权限
    boolean updateAuth(int rid, int[] aids);
    
    //分页查询
    public PageVo<Role> queryByPage(int page, int count);
    
    //删除
    public ResultVo deleteById(int id);
}
