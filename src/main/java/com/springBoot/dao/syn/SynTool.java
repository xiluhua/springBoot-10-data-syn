package com.springBoot.dao.syn;

import java.util.HashMap;
import java.util.Map;

public class SynTool {
	
	@SuppressWarnings({ "rawtypes" })
	public static Map<String, Object> put(Class clazz, String json) {
		Map<String, Object> map = new HashMap<>();
		map.put("class", clazz);
		map.put("list", json);
		return map;
	}
}
