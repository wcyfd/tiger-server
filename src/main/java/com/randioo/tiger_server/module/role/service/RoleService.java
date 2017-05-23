package com.randioo.tiger_server.module.role.service;

import com.randioo.randioo_server_base.utils.service.ObserveBaseServiceInterface;
import com.randioo.tiger_server.entity.bo.Role;

public interface RoleService extends ObserveBaseServiceInterface {

	void newRoleInit(Role role);

	public void roleInit(Role role);

}
