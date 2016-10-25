package jym.agtsys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jym.agtsys.dao.AccountDetailMapper;
import jym.agtsys.dao.AccountMapper;
import jym.agtsys.domain.Account;
import jym.agtsys.domain.AccountDetail;
import jym.agtsys.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountMapper am;
	@Autowired
	private AccountDetailMapper adm;

	@Override
	public int addAccount(Account account) throws Exception {
		return am.insertAccount(account);
	}
	
	@Override
	public int updateAccount(Account account) throws Exception {
		return am.updateAccount(account);
	}

	@Override
	public int TXoperateAccount(Account account, AccountDetail accountDetail) throws Exception {
		//资金计算，设置账户余额
		account.setMoney(account.getMoney()+accountDetail.getMoney());
		//设置账户余额备份
		account.setMoneybak(account.getMoneybak()+accountDetail.getMoney());
		//设置财务流水账户余额
		accountDetail.setAccountmoney(account.getMoney());
		//修改账户资金
		am.updateAccount(account);
		//记录流水
		return adm.insertAccountDetail(accountDetail);
	}

	@Override
	public Account getAccountByUserId(Account account) throws Exception {
		return am.selectAccountByUserId(account);
	}

	

}
