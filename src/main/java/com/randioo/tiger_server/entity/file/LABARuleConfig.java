package com.randioo.tiger_server.entity.file;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.randioo.tiger_server.cache.file.LABARuleConfigCache;

public class LABARuleConfig{
	public static final String urlKey="laba_rule.tbl";
	/** 编号 */
	public int ruleId;
	/** 坐标X */
	public int x;
	/** 坐标Y */
	public int y;
		
	public static void parse(ByteBuffer buffer){
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		while(buffer.hasRemaining()){
			LABARuleConfig config = new LABARuleConfig();
			config.ruleId=buffer.getInt();
			config.x=buffer.getInt();
			config.y=buffer.getInt();
			
			LABARuleConfigCache.putConfig(config);
		}
	}
}
