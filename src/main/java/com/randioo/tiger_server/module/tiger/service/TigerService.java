package com.randioo.tiger_server.module.tiger.service;

import java.util.List;

import com.google.protobuf.GeneratedMessage;
import com.randioo.tiger_server.entity.bo.Role;

public interface TigerService {
	GeneratedMessage draw(Role role,List<Integer> ruleIdList);
}
