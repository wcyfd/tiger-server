package com.randioo.tiger_server.entity.bo;

import com.randioo.randioo_server_base.utils.db.DataEntity;

/**
 * Created by admin on 2017/5/16.
 */
public class Bank extends DataEntity{
	/**账号*/
    private  String account;
    /**燃点币*/
    private int lightCoin;

   
    public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getLightCoin() {
        return lightCoin;
    }

    public void setLightCoin(int lightCoin) {
        this.lightCoin = lightCoin;
    }
}
