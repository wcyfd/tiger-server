package com.randioo.tiger_server.module.gm.service;

import org.apache.mina.core.session.IoSession;

import com.randioo.randioo_server_base.utils.service.ObserveBaseServiceInterface;

public interface GmService extends ObserveBaseServiceInterface{

	void loopSaveData(boolean mustSave);

	void command(String command,IoSession session);
}
