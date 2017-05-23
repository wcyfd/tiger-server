package com.randioo.tiger_server;

import com.randioo.randioo_server_base.cache.SessionCache;
import com.randioo.randioo_server_base.db.DBRunnable;
import com.randioo.randioo_server_base.db.GameDB;
import com.randioo.randioo_server_base.net.SpringContext;
import com.randioo.randioo_server_base.utils.TimeUtils;
import com.randioo.tiger_server.entity.bo.Role;

/**
 * session关闭角色数据处理
 * 
 */
public class SessionCloseHandler {
	/**
	 * 移除session缓存
	 * 
	 * @param id
	 */
	public static void asynManipulate(Role role) {

		System.out.println("[account:" + role.getAccount() + ",name:" + role.getName() + "] manipulate");

		SessionCache.removeSessionById(role.getRoleId());


		role.setOfflineTimeStr(TimeUtils.getDetailTimeStr());

		GameDB gameDB = SpringContext.getBean("gameDB");
		if (!gameDB.isUpdatePoolClose()) {
			gameDB.getUpdatePool().submit(new DBRunnable<Role>(role) {
				@Override
				public void run(Role role) {
					roleDataCache2DB(role, true);
				}
			});
		}
	}

	public static void roleDataCache2DB(Role role, boolean mustSave) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("id:" + role.getRoleId() + ",account:" + role.getAccount() + ",name:" + role.getName()
					+ "] save error");
		}

	}

}
