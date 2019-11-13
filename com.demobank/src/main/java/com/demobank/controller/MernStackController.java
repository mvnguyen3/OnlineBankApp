package com.demobank.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MernStackController {
	
	@RequestMapping("/DoFun")
	ModelAndView doFunThing(ModelAndView model, HttpSession session) {
		model.setViewName("calculate");
		
		
		return model;
	}
	Map<Integer, String> map = new HashMap<>();
	@GetMapping("/calculate")
	ModelAndView doCalculate(@RequestParam int x, HttpSession session) {
		ModelAndView model = new ModelAndView("calculate");
		String result = (x * 2) + "";
		
		map.put(x, result);
		
		session.setAttribute("myMaps", map);
		System.out.println("Item in list: " + map);
		//model.clear();
		
		return doFunThing(model, session);
	}
	
}








