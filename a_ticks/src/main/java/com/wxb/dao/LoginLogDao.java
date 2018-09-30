package com.wxb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.wxb.entity.LoginLog;

public interface LoginLogDao {

    //展示所有登录记录
	@Select("select id, ip, no, createtime, location from t_loginlog order by createtime desc limit 10")
    @ResultType(LoginLog.class)
    public List<LoginLog> findAll();
	//分页查询记录
	@Select("SELECT * from t_loginlog  LIMIT #{rowindex},#{count}")
	@ResultType(LoginLog.class)
    List<LoginLog> queryByPage(@Param("rowindex")int index, @Param("count")int count);
	
	@Select("select count(*) from t_loginlog ")
	@ResultType(Long.class)
	Long queryCount();

	@Insert("insert into t_loginlog (ip,no,createtime,location) values (#{ip},#{no},#{createtime},#{location})")
	int add(LoginLog loginLog);

}