package com.github.jees5555.agtsys.service;

import com.github.jees5555.agtsys.domain.Account;
import com.github.jees5555.agtsys.domain.AccountDetail;

public interface AccountService {

	int addAccount(Account account) throws Exception;

	int updateAccount(Account account) throws Exception;

	int TXoperateAccount(Account account, AccountDetail accountDetail) throws Exception;

	Account getAccountByUserId(Account account) throws Exception;

}
