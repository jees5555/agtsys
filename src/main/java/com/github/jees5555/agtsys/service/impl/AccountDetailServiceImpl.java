package com.github.jees5555.agtsys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.jees5555.agtsys.dao.AccountDetailMapper;
import com.github.jees5555.agtsys.domain.AccountDetail;
import com.github.jees5555.agtsys.service.AccountDetailService;

public class AccountDetailServiceImpl implements AccountDetailService {
	@Autowired
	private AccountDetailMapper adm;

	@Override
	public int addAccountDetail(AccountDetail accountDetail) throws Exception {
		return adm.insertAccountDetail(accountDetail);
	}

}
