package com.randioo.tiger_server.entity.file;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.randioo.tiger_server.cache.file.LABARuleAwardConfigCache;

public class LABARuleAwardConfig{
	public static final String urlKey="laba_award_rule.tbl";
	/** 水果编号 */
	public int fruitId;
	/** 连击数 */
	public int hit;
	/** 积分 */
	public int point;
		
	public static void parse(ByteBuffer buffer){
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		while(buffer.hasRemaining()){
			LABARuleAwardConfig config = new LABARuleAwardConfig();
			config.fruitId=buffer.getInt();
			config.hit=buffer.getInt();
			config.point=buffer.getInt();
			
			LABARuleAwardConfigCache.putConfig(config);
		}
	}
}
