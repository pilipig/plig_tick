package com.wxb.service.impl;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxb.dao.AuthorityDao;
import com.wxb.entity.Authority;

import com.wxb.service.AuthorityService;
import com.wxb.vo.ResultVo;

@Service
public class AuthorityServiceImpl implements AuthorityService{
	@Autowired
	private AuthorityDao authorityDao;
	@Override
	public Set<String> findPermitByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Authority> menu() {
		
		try {
			List<Authority> list=authorityDao.findMenu();
			return  list;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Authority> findAllPermits(int page,int size) {
		// TODO Auto-generated method stub
		int index;//分页查询索引
		
		//根据页码计算分页查询时的开始索引
		index = (page - 1) * size;
		Map<String, Object> info = new HashMap<>();
		info.put("index", index);
		info.put("size", size);
		
		List<Authority> list;
		try {
			list = authorityDao.findPermits(info);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int count() {
		return authorityDao.count();
		
	}

	@Override
	public void addAuthority(Authority authority) {
		// TODO Auto-generated method stub
		if(authority == null ) {
			throw new RuntimeException("添加数据为空");
		}else if(authorityDao.findByTitle(authority.getTitle()) != null){
			throw new RuntimeException("不能添加同名权限");
		}else {
			authorityDao.addSelective(authority);
		}
	}

	@Override
	public Authority findByAurl(String aurl) {
		// TODO Auto-generated method stub
		Authority authority;
		try {
			authority = authorityDao.findByAurl(aurl);
			return authority;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultVo deleteById(int id) {
		// TODO Auto-generated method stub
		if(authorityDao.deleteById(id) > 0) {
			return ResultVo.setOK("删除成功");
		}else {
			return ResultVo.setERROR("删除失败");
		}
	}

	@Override
	public ResultVo update(Authority authority) {
		// TODO Auto-generated method stub
		if(authorityDao.updateById(authority) > 0) {
			return ResultVo.setOK("修改成功");
		}else {
			return ResultVo.setERROR("修改失败");
		}
	}

	

}
