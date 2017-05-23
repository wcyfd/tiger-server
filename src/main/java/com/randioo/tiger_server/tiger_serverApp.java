package com.randioo.tiger_server;

import java.io.File;

import com.randioo.randioo_server_base.net.SpringContext;
import com.randioo.randioo_server_base.utils.ConfigLoader;
import com.randioo.randioo_server_base.utils.StringUtils;
import com.randioo.randioo_server_base.utils.sensitive.SensitiveWordDictionary;
import com.randioo.randioo_server_base.utils.system.GameServerInit;
import com.randioo.randioo_server_base.utils.system.GlobleConfig;
import com.randioo.randioo_server_base.utils.system.GlobleConfig.GlobleEnum;

/**
 * Hello world!
 *
 */
public class tiger_serverApp {
	public static void main(String[] args) {
		StringUtils.printArgs(args);
		GlobleConfig.init(args);

		// 添加日志文件夹
		File file = new File("log");
		file.delete();
		file.mkdir();

		ConfigLoader.loadConfig("com.randioo.tiger_server.entity.file", "./config.zip");
		SensitiveWordDictionary.readAll("./sensitive.txt");

		SpringContext.initSpringCtx("ApplicationContext.xml");
		
		((GameServerInit) SpringContext.getBean("gameServerInit")).setHandler(new ServerHandler()).start();
		GlobleConfig.set(GlobleEnum.LOGIN, true);
		
		
	}
}
