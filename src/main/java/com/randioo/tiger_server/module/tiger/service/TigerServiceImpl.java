package com.randioo.tiger_server.module.tiger.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.protobuf.GeneratedMessage;
import com.randioo.randioo_server_base.utils.RandomUtils;
import com.randioo.tiger_server.cache.file.LABARuleAwardConfigCache;
import com.randioo.tiger_server.cache.file.LABARuleConfigCache;
import com.randioo.tiger_server.entity.bo.Role;
import com.randioo.tiger_server.entity.file.LABARuleAwardConfig;
import com.randioo.tiger_server.entity.file.LABARuleConfig;
import com.randioo.tiger_server.protocol.Entity.RandomData;
import com.randioo.tiger_server.protocol.Entity.RowData;
import com.randioo.tiger_server.protocol.ServerMessage.SCMessage;
import com.randioo.tiger_server.protocol.Tiger.TigerResponse;

@Service("tigerService")
public class TigerServiceImpl implements TigerService {

	@Override
	public GeneratedMessage draw(Role role, List<Integer> ruleIdList) {
		int arr[][] = new int[3][5];

		// 随机出老虎机的值
		RandomData.Builder randomDataBuilder = RandomData.newBuilder();
		for (int i = 0; i < arr.length; i++) {
			RowData.Builder rowDataBuilder = RowData.newBuilder();
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = RandomUtils.getRandomNum(11);
				rowDataBuilder.addValue(arr[i][j]);
			}
			randomDataBuilder.addRowData(rowDataBuilder);
		}

		// 积分
		int result = 0;
		int free = 0;

		// 遍历所有规则
		for (int ruleId : ruleIdList) {
			List<LABARuleConfig> configs = LABARuleConfigCache.getLabaRuleConfig(ruleId);
			// 计算连击数
			int targetValue = 0;
			int i = 0;
			for (; i < configs.size(); i++) {
				// 前一个位置
				LABARuleConfig currentConfig = configs.get(i);

				// 获得第一个位置的值
				int currentValue = arr[currentConfig.x][currentConfig.y];

				// 目标值要先赋值，不管是什么值
				if (targetValue == 0)
					targetValue = currentValue;
				else if (currentValue == 10) {
					continue;
				} else {
					if (targetValue == 10) {
						targetValue = currentValue;
					} else if (targetValue == currentValue) {
						continue;
					} else {
						break;
					}
				}
			}

			// point cal
			// 一次都没有命中，则直接跳过
			if (i == 1) {
				continue;
			}
			result += LABARuleAwardConfigCache.getMap(targetValue).get(i + 1).point;
		}
		return SCMessage.newBuilder()
				.setTigerResponse(
						TigerResponse.newBuilder().setRandomData(randomDataBuilder).setResult(result).setFree(free))
				.build();
	}
}
