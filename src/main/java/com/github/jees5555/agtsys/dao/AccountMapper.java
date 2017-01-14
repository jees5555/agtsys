package com.github.jees5555.agtsys.dao;

import com.github.jees5555.agtsys.domain.Account;

public interface AccountMapper {
	int insertAccount(Account account) throws Exception;

	int deleteAccount(Account account) throws Exception;

	int updateAccount(Account account) throws Exception;

	Account selectAccountByUserId(Account account) throws Exception;

	int insert(Account record);

	int updateByPrimaryKey(Account record);
}