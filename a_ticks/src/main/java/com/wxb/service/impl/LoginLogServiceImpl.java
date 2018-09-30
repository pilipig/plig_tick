package com.wxb.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxb.dao.LoginLogDao;
import com.wxb.dao.UserDao;
import com.wxb.entity.LoginLog;
import com.wxb.entity.User;
import com.wxb.service.LoginLogService;
import com.wxb.utils.AddressUtils;
import com.wxb.vo.PageVo;

@Service
public class LoginLogServiceImpl implements LoginLogService{

	@Autowired
	private LoginLogDao lDao;
	
	@Autowired
	private UserDao uDao;

	@Override
	public List<LoginLog> findAllLog() {
		return lDao.findAll();
	}

	//分页查询
	@Override
	public PageVo<LoginLog> findByPage(int page, int count) {
		// TODO Auto-generated method stub
		int index = 0;
		if(page > 0) {
			index = (page - 1)* count;
		}
		PageVo<LoginLog> pv = new PageVo<>();
		pv.setData(lDao.queryByPage(index, count));
		if(pv.getData() != null) {
			pv.setCount(lDao.queryCount().intValue());
		}
		pv.setCode(0);
		pv.setMsg("哦哦");
		return pv;
	}

	@Override
	public boolean add(String no,HttpServletRequest request) {
		User user = uDao.findUserByNo(no);
		LoginLog log = new LoginLog();
		log.setCreatetime(new Date());
		try {
			log.setLocation(AddressUtils.getAddresses("ip=" + AddressUtils.getIpAddr(request),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.setNo(no);
		log.setIp(AddressUtils.getIpAddr(request));
		return lDao.add(log)>0;
	}

	
}
