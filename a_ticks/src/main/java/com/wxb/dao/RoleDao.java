package com.wxb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.wxb.entity.Role;

public interface RoleDao {

	//新增
	@Insert("insert into t_role(info,name,parentId) values(#{info},#{root},#{parentId})")
	int insert(Role role);
	//删除角色
	@Delete("delete from t_role where id=#{id}")
	int delete(int id);
	//展示所有的
	@Select("select * from t_role")
	@ResultType(Role.class)
	List<Role> queryAll();
	
	//查询角色对应的权限
    //修改角色对应的权限
    //新增角色对应的权限
    @Insert("insert into t_roleauthority(rid,aid) values(#{rid},#{aid});")
    int insertAuth(@Param("rid") int rid, @Param("aid") int aid);
    //删除
    @Delete("delete from t_roleauthority where rid=#{rid}")
    int delAuth(int rid);
    //分页查询
    @Select("select id,name,info from t_role LIMIT #{rowindex},#{count}")
    @ResultType(Role.class)
    List<Role> queryByPage(@Param("rowindex")int index,@Param("count")int count);
    
    @Select("select count(*) from t_role")
    @ResultType(Long.class)
    Long querycount();
}
