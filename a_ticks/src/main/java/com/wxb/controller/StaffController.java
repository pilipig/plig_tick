package com.wxb.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wxb.entity.Staff;
import com.wxb.service.StaffService;
import com.wxb.utils.ExcelUtils;
import com.wxb.utils.FileUtils;
import com.wxb.vo.PageVo;
import com.wxb.vo.ResultVo;

@Controller
public class StaffController {

	@Autowired
	private StaffService sService;
	//增加
	@RequestMapping(value="/staffadd.do",method= {RequestMethod.POST})
	public String add(Staff staff) {
		if(sService.add(staff)) {
			return "redirect:/stafflist.html";
		}else {
			return "staffadd";
		}
		
		
	}
	//删除
	@RequestMapping(value="staffdelete.do",method= {RequestMethod.POST})
	@ResponseBody
	public ResultVo delete(String no) {
		return sService.deleteStaByNo(no);
	}
	//x修改
	@RequestMapping(value ="staffupdate.do", method = {RequestMethod.POST })
	public String update(Staff staff) {
		if(sService.updateSta(staff).getCode() == 1000) {
			return "redirect:/stafflist.html";
		}else {
			return "staffupdate";
		}
	}
	
	// 查询 --分页
	@RequestMapping(value ="staffpage.do")
	@ResponseBody
	public PageVo<Staff> queryPage(int page, int limit) {
		return sService.findStaByPage(page, limit);
	}

	// 查询全部信息
	@RequestMapping(value ="staffall.do")
	@ResponseBody
	public List<Staff> queryAll() {
		return sService.findAll();
	}
	@RequestMapping(value = "staffno.do")
	@ResponseBody
	public ResultVo queryMaxNo() {
		return ResultVo.setOK(sService.queryMaxNo());
	}
	//批量导入
	@RequestMapping("staffbatch.do")
	public String batch(@RequestParam MultipartFile mFile,HttpServletRequest request) throws IOException {
		List<Staff> list= ExcelUtils.parseExcel(mFile.getInputStream());
		if(sService.insertBatch(list)) {
			return "redirect:/stafflist.html";
		} else {
			return "staffbatch";
		}
		
	}

	//上传照片
	@RequestMapping(value="photoupload.do",method= {RequestMethod.POST})
	@ResponseBody
	public ResultVo upload(@RequestParam("file")MultipartFile mFile,HttpServletRequest request) {
		File dir=FileUtils.createDir(request.getServletContext().getRealPath("/"), "photos");
		File file=new File(dir,FileUtils.createFileName(mFile.getOriginalFilename()));
		try {
			mFile.transferTo(file);
			return ResultVo.setOK(dir.getName()+File.separator+file.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultVo.setERROR("错了");
		}
		
		
	}
	
	
}
