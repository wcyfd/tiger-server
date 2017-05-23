package com.randioo.tiger_server.entity.file;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.randioo.tiger_server.cache.file.TableRuleConfigCache;

public class TableRuleConfig{
	public static final String urlKey="table.tbl";
	/** 行数 */
	public int rows;
	/** 列数 */
	public int columns;
		
	public static void parse(ByteBuffer buffer){
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		while(buffer.hasRemaining()){
			TableRuleConfig config = new TableRuleConfig();
			config.rows=buffer.getInt();
			config.columns=buffer.getInt();
			
			TableRuleConfigCache.putConfig(config);
		}
	}
}
