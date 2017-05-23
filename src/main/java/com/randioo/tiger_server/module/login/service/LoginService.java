package com.randioo.tiger_server.module.login.service;

import org.apache.mina.core.session.IoSession;

import com.google.protobuf.GeneratedMessage;
import com.randioo.randioo_server_base.utils.service.ObserveBaseServiceInterface;
import com.randioo.tiger_server.entity.bo.Role;

public interface LoginService extends ObserveBaseServiceInterface {

	GeneratedMessage getRoleData(String account, IoSession ioSession);

	GeneratedMessage creatRole(String account);

	GeneratedMessage login(String account);

	/**
	 * 通过id获取玩家
	 * 
	 * @param roleId
	 * @return
	 * @author wcy 2017年1月10日
	 */
	public Role getRoleById(int roleId);

	/**
	 * 通过帐号获得玩家
	 * 
	 * @param account
	 * @return
	 * @author wcy 2017年1月10日
	 */
	public Role getRoleByAccount(String account);

}
