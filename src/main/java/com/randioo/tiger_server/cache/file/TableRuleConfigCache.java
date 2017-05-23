package com.randioo.tiger_server.cache.file;

import java.util.ArrayList;
import java.util.List;

import com.randioo.tiger_server.entity.file.TableRuleConfig;

public class TableRuleConfigCache {
	private static TableRuleConfig tableConfig = new TableRuleConfig();

	public static void putConfig(TableRuleConfig config) {
		List<TableRuleConfig>list=new ArrayList<>();
		if(list==null)
		list.add(config);		
	}

	public static TableRuleConfig getTable() {
		return tableConfig;
	}

}
