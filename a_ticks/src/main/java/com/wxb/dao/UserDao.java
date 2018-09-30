package com.wxb.dao;

import org.apache.ibatis.annotations.*;


import com.wxb.entity.Role;
import com.wxb.entity.User;

import java.util.List;

public interface UserDao {
	//增加user
	@Insert("insert into t_user(no,password,name,flag) values(#{no},#{password},#{name},#{flag})")
	public int add(User user);
	//查询登录
	@Select("select * from t_user where no=#{no} and flag=1")
	@ResultType(User.class)
	public User findUserByNo(String no);
	//修改密码
	@Update("update t_user set password=#{password} where flag=1 and no=#{no}")
	public int updatePassword(User user);
	
	
	
	//修改角色有没有效果
	@Update("update t_user set flag=#{flag} where id=#{id}")
	int updateFlag(@Param("flag")int flag,@Param("id") int id);
	
	//分页
	@Select("select u.id,u.no,u.name,u.flag,r.info rinfo from t_user u LEFT JOIN t_userrole ur on ur.uid=u.id LEFT JOIN t_role r on r.id=ur.rid LIMIT #{rowindex},#{count}")
	@ResultType(User.class)
	List<User> queryByPage(@Param("rowindex")int index, @Param("count")int count);

	//展示所有信息
	@Select("select count(*) from t_role ")
	@ResultType(Long.class)
	Long querycount();
	
	//删除
	@Delete("delete from t_userrole where uid=#{uid}")
	int deleteRole(int uid);
	
	@Update("update t_user set name=#{name},no=#{no} where id=#{id}")
	int updateById(User user);
}
