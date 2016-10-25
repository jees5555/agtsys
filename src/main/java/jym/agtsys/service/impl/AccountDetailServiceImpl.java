package jym.agtsys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import jym.agtsys.dao.AccountDetailMapper;
import jym.agtsys.domain.AccountDetail;
import jym.agtsys.service.AccountDetailService;

public class AccountDetailServiceImpl implements AccountDetailService {
	@Autowired
	private AccountDetailMapper adm;

	@Override
	public int addAccountDetail(AccountDetail accountDetail) throws Exception {
		return adm.insertAccountDetail(accountDetail);
	}

}
