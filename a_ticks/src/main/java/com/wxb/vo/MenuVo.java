package com.wxb.vo;

import java.util.List;
import java.util.Map;


public class MenuVo {
	private String pname;
	private List<Map<String,String>> menus;
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public List<Map<String, String>> getMenus() {
		return menus;
	}
	public void setMenus(List<Map<String, String>> menus) {
		this.menus = menus;
	}
	
	
}
