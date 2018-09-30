package com.wxb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wxb.entity.Grade;

public interface GradeDao {

	//假删除
	@Update("update t_grade set flag=2 where id=#{id}")
	int deleteById(int id);
	
	//修改名字，周期，地点
	@Update("update t_grade set name=#{name},week=#{week},location=#{location} where id=#{id}")
	public int updateById(Grade grade);
	
	//增加
	@Insert("insert into t_grade ( name, flag, week, createdate, location ,cid)values ( #{name}, 1,#{week}, #{createdate}, #{location},#{cid} )")
	int insert(Grade grade);
	
	//展示所有
	@Select("select id,name from t_grade where flag=1")
	@ResultType(Grade.class)
	List<Grade> queryAll();
	
	//查询 分页
	@Select("select g.id,g.name,g.flag,g.week,g.createdate,g.location,g.cid, s.scount, c.name cname FROM t_grade g LEFT JOIN(select count(*) scount,gid from t_student GROUP BY gid) s on g.id=s.gid LEFT JOIN t_course c on g.cid=c.id where g.flag=1 limit #{rowindex},#{count}")
	List<Grade> queryByPage(@Param("rowindex")int index, @Param("count")int count);
	
	//总数量
	@Select("select count(*) from t_grade where flag=1")
	@ResultType(Long.class)
	Long queryCount();
	
}
