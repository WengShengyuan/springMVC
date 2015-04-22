package org.company.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.company.core.common.comstatic.ConfigStatic;
import org.company.core.common.comstatic.StaticValue;
import org.company.core.moduel.domain.TestTable;
import org.company.core.moduel.service.TestTableService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Resource(name="TestTableServiceImpl")
	private TestTableService testTableService;
	
	@Resource(name = "ConfigStatic")
	private ConfigStatic config;
	
	@RequestMapping(value="/home")
	public ModelAndView home(HttpServletRequest request){
		
		String findName = request.getParameter("name");
		
		ModelAndView view = new ModelAndView();
		view.setViewName("index");
		List<TestTable> list = new ArrayList<TestTable>();
		long timetosql = 0;
		try {
			long startTime=System.currentTimeMillis();
			list = testTableService.findByName(findName);
			long endTime=System.currentTimeMillis();
			timetosql = endTime - startTime;
		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("time",timetosql);
		view.addObject("static", StaticValue.STATICPARAM);
		view.addObject("profile",config.getTESTPARAM());
		view.addObject("list", list);
		view.addObject("customvalue", "This is a custom text.");
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value="/flush")
	public boolean flush(){
		
		return true;
	}
	
	@ResponseBody
	@RequestMapping(value="/addOne")
	public boolean addOne(){
		
		try {
			testTableService.batchAdd(10000);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
		
	}
	
}
