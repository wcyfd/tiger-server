package com.randioo.tiger_server.module.tiger.service;

import java.util.List;
import java.util.Map;

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
		for (int ruleId : ruleIdList) {
			List<LABARuleConfig> configs = LABARuleConfigCache.getLabaRuleConfig(ruleId);
			// 计算连击数
			int count = 1;
			for (int i = 0; i < configs.size() - 1; i++) {
				// 前一个
				LABARuleConfig config = configs.get(i);
				// 后一个
				LABARuleConfig nextConfig = configs.get(i + 1);

				int value = arr[config.x][config.y];
				int nextValue = arr[nextConfig.x][nextConfig.y];
				// 判断百搭的位置
				if (value == 10 && i == 0)
					value = nextValue;

				if (nextValue == 10 && i > 0)
					nextValue = value;

				// 判断第一个与和后面是否相等，并计算连击
				if (i == 0 && value == nextValue) {
					count++;
					// 如果是百搭五连，另外计算
					if (count == 5 && value == 10) {
						value = 10;
					}
					// 如果是免费,得到免费次数
					if (value == 0 && count > 1) {
						free = count;
					}
				}
				// 获取对应编号相关的奖励
				Map<Integer, LABARuleAwardConfig> laba = LABARuleAwardConfigCache.getMap(value);
				LABARuleAwardConfig point = laba.get(count);
				// 计算积分
				if (point != null) {
					result += point.point;
					continue;
				}
			}
		}
		return SCMessage
				.newBuilder()
				.setTigerResponse(
						TigerResponse.newBuilder().setRandomData(randomDataBuilder).setResult(result).setFree(free))
				.build();
	}
}
