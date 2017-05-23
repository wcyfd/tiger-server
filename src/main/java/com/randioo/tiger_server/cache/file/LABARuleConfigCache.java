package com.randioo.tiger_server.cache.file;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.randioo.tiger_server.entity.file.LABARuleConfig;

public class LABARuleConfigCache {

	private static Map<Integer, List<LABARuleConfig>> configMap = new HashMap<>();
	private static List<LABARuleConfig> list=new ArrayList<>();
	
	public static void putConfig(LABARuleConfig config) {
		List<LABARuleConfig> list = configMap.get(config.ruleId);
		if (list == null) {
			list = new ArrayList<>();
			configMap.put(config.ruleId, list);
		}
		list.add(config);
	}

	public static List<LABARuleConfig> getLabaRuleConfig(int ruleId) {
		return configMap.get(ruleId);
	}
	
	public static List<LABARuleConfig> getList(){
		return list;
	}
}
