package com.wxb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxb.entity.Role;
import com.wxb.service.RoleService;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

import java.util.List;


@Controller
public class RoleController {
    @Autowired
    private RoleService rService;

    @RequestMapping(value="/roleall.do")
    @ResponseBody
    public List<Role> queryAll(){
        return rService.queryAll();
    }
    
    @RequestMapping(value="/rolepage.do")
    @ResponseBody
    public PageVo<Role> queryByPage(int page, int limit){
		return rService.queryByPage(page, limit);
    	
    }
    
    @RequestMapping(value="roledel.do", method= {RequestMethod.POST})
    @ResponseBody
    public ResultVo delete(int id) {
    	return rService.deleteById(id);
    }
}
