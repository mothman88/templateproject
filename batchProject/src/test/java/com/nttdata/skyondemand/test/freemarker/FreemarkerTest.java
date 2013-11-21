package com.nttdata.skyondemand.test.freemarker;

import java.util.HashMap;
import java.util.Map;

import net.mothman.project.service.FreemarkerService;

public class FreemarkerTest {

	public static void main(String[] args) {
		FreemarkerService f = new FreemarkerService();
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("data", "123test");
		
		System.out.println("Result : |" + f.process("template/page.ftl", map) +"|");
	}
	
}
