package jym.agtsys.service;

import jym.agtsys.domain.Account;
import jym.agtsys.domain.AccountDetail;

public interface AccountService {
	
	int addAccount(Account account) throws Exception;
	
	int updateAccount(Account account) throws Exception;
	
	int TXoperateAccount(Account account,AccountDetail accountDetail) throws Exception;
	
	 Account getAccountByUserId(Account account) throws Exception;

}
