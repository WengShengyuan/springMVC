package org.company.web.controller;

import java.util.List;

import javax.annotation.Resource;

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
	public ModelAndView home(){
		ModelAndView view = new ModelAndView();
		view.setViewName("index");
		List<TestTable> list = null;
		try {
			list = testTableService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		view.addObject("static", StaticValue.STATICPARAM);
		view.addObject("profile",config.getTESTPARAM());
		view.addObject("list", list);
		view.addObject("customvalue", "This is a custom text.");
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value="/addOne")
	public List<TestTable> addOne(){
		TestTable o = new TestTable();
		List<TestTable> list = null;
		o.setName("test");
		try {
			testTableService.save(o);
			list = testTableService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
}
