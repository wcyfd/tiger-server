package com.randioo.tiger_server.dao;

import org.apache.ibatis.annotations.Param;

import com.randioo.randioo_server_base.db.BaseDao;
import com.randioo.tiger_server.entity.bo.Bank;

public interface BankDao extends BaseDao<Bank>{
	Bank getBank(@Param("account") String account);

}
