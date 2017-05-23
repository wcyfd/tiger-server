package com.randioo.tiger_server.cache.file;

import java.util.IdentityHashMap;
import java.util.Map;

import com.randioo.randioo_server_base.utils.GameUtils;
import com.randioo.tiger_server.entity.file.LABARuleAwardConfig;

public class LABARuleAwardConfigCache {

	private static Map<Integer, Map<Integer, LABARuleAwardConfig>> map = new IdentityHashMap<>();

	public static void putConfig(LABARuleAwardConfig config) {
		GameUtils.mapABCInsert(map, config.fruitId, config.hit, config);
	}

	public static Map<Integer, LABARuleAwardConfig> getMap(int fruitId) {
		return map.get(fruitId);
	}

}
