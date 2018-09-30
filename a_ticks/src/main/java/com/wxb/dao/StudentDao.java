package com.wxb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import com.wxb.entity.Student;

public interface StudentDao {
	//删除1存在2删除
	@Update("update t_student set flag=2 where no=#{no}")
    int deleteByNo(String no);
	//增加
	@Insert("insert into t_student (no, name, sex, birthday, cardno, school, education, gid, flag,  email, qq, phone, introno,  createdate, photo) values (#{no}, #{name}, #{sex},  #{birthday,jdbcType=DATE}, #{cardno}, #{school},  #{education}, #{gid}, 1,  #{email}, #{qq}, #{phone}, #{introno}, #{createdate,jdbcType=DATE},null)")
    int insert(Student record);
	//查询
	@Select("select  no, name, sex, birthday, cardno, school, education, gid, flag, email, qq, phone,introno, createdate, photo from t_student where no=#{no}")
    Student selectByNo(String no);
	//修改
	@Update("update t_student set  photo = #{photo} where no = #{no}")
    int updateByNo(@Param("photo")String photo,@Param("no")String no);
    //查询  分页
  	@Select("select s.no, s.name, s.sex, s.birthday, s.cardno, s.school, s.education, s.gid, s.flag, s.email, s.qq, s.phone,s.introno, s.createdate, s.photo,g.name gname from t_student s LEFT JOIN t_grade g on s.gid=g.id where s.flag=1 limit #{rowindex},#{count}")
  	@ResultType(Student.class)
  	List<Student> queryByPage(@Param("rowindex")int index,@Param("count")int count);
  	
  	//总数量
  	@Select("select count(*) from t_student where flag=1")
  	@ResultType(Long.class)
  	Long queryCount();
  	//查询  班级人数
  	@Select("select no, name, sex, birthday, cardno, school, education, gid, flag, email, qq, phone,introno, createdate, photo from t_student where flag=1 and gid=#{gid}")
  	@ResultType(Student.class)
  	List<Student> queryByGid(int gid);
  	
  	
  	//展示所有
  	@Select("select * from t_student where no=#{no}")
  	@ResultType(Student.class)
  	List<Student> queryAll();
  	//总数量
  	@Select("select count(*) from t_student where flag=1 and gid=#{gid}")
  	@ResultType(Long.class)
  	Long queryCountGid(int gid);
}