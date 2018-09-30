package com.wxb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wxb.entity.Staff;

public interface StaffDao {

	//假删除 1存在2删除
	@Update("update t_staff set flag=2 where no=#{no}")
	int deleteByNo(String no);
	
	//增加
	@Insert("insert into t_staff (no,name,did,flag,sex,email,qq,phone,createdate,photo) values (#{no},#{name},#{did},1,#{sex},#{email},#{qq},#{phone},#{createdate},#{photo})")
	int insert(Staff staff);
	//修改
	@Update("update t_staff set name = #{name},sex=#{sex}, did =#{did}, email=#{email},qq =#{qq}, phone=#{phone},photo=#{photo}  where no= #{no}")
	int updateByNo(Staff staff);
	
	//展示所有
	@Select("select * from t_staff where flag=1")
	List<Staff> queryAll();
	
	//分页
	@Select("select s.no, s.name, s.did, s.flag, s.sex,s.email, s.qq, s.phone, s.createdate,d.name dname from t_staff  s left join t_depart d on s.did=d.id  where s.flag=1 limit #{rowindex},#{count}")
	@ResultType(Staff.class)
	List<Staff> queryByPage(@Param("rowindex")int index,@Param("count") int count);
	
	//员工总数量
	@Select("select count(*) from t_staff where flag=1")
	@ResultType(Long.class)
	Long queryCount();
	
	@Select("select no from t_staff order by no desc limit 1 ")
	@ResultType(String.class)
	String queryMaxNo();
	}