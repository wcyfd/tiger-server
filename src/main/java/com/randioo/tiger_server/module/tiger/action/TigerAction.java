package com.randioo.tiger_server.module.tiger.action;

import java.util.ArrayList;
import java.util.List;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.google.protobuf.GeneratedMessage;
import com.randioo.randioo_server_base.annotation.PTAnnotation;
import com.randioo.randioo_server_base.cache.RoleCache;
import com.randioo.randioo_server_base.net.IActionSupport;
import com.randioo.tiger_server.cache.file.LABARuleConfigCache;
import com.randioo.tiger_server.entity.bo.Role;
import com.randioo.tiger_server.entity.file.LABARuleConfig;
import com.randioo.tiger_server.module.tiger.service.TigerService;
import com.randioo.tiger_server.protocol.Tiger.TigerRequest;

@Controller
@PTAnnotation(TigerRequest.class)
public class TigerAction implements IActionSupport {
	@Autowired
	private TigerService tigerService;

	@Override
	public void execute(Object data, IoSession session) {
		TigerRequest request = (TigerRequest) data;
		Role role = (Role) RoleCache.getRoleBySession(session);
		List<Integer> list = new ArrayList<>();
		List<LABARuleConfig> configList = LABARuleConfigCache.getList();
		for (LABARuleConfig ruleId : configList) {
			if (list == null)
				list.add(ruleId.ruleId);
		}
		GeneratedMessage sc = tigerService.draw(role, list);
		if (sc != null) {
			session.write(sc);
		}
	}
}