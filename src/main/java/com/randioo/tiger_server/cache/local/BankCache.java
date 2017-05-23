package com.randioo.tiger_server.cache.local;

import java.util.HashMap;
import java.util.Map;

import com.randioo.tiger_server.entity.bo.Bank;

/**
 * Created by admin on 2017/5/16.
 */
public class BankCache {
	public static Map<String, Bank> bankMap = new HashMap<>();

	public static Map<String, Bank> getBankMap() {
		return bankMap;
	}

	public static Bank getBank(String account) {
		return bankMap.get(account);
	}
}
