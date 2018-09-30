package com.wxb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.wxb.entity.Depart;

public interface DepartDao {

	//新增
	@Insert("insert into t_depart(name,createtime,flag) value(#{name},#{createtime},1)")
	public int add(Depart depart);
	
	//修改
	@Update("update t_depart set name=#{name},createtime=#{createtime} where flag=1 and id=#{id}")
	public int update(Depart depart);
	
	//假删除，1显示2删除
	@Update("update t_depart set flag=2 where id=#{id}")
	public int delete(int id);
	
	//查询  分页
	@Select("select d.id,d.name,d.createtime,d.flag,s.dcount from t_depart d left join (select count(*) dcount,did from t_staff group by did) s on d.id=s.did where d.flag=1 limit #{rowindex},#{count}")
	@ResultType(Depart.class)
	List<Depart> queryByPage(@Param("rowindex")int index,@Param("count")int count);
	
	
	//总数量
	@Select("select count(*) from t_depart where flag=1")
	@ResultType(Long.class)
	Long queryCount();
	
	//展示所有
	@Select("select id,name from t_depart where flag=1")
	@ResultType(Depart.class)
	List<Depart> queryAll();
	
	//根据id找资源
	@Select("select * from t_depart where id=#{id}")
	public Depart findById(int id);
}
