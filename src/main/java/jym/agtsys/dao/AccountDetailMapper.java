package jym.agtsys.dao;

import jym.agtsys.domain.AccountDetail;

public interface AccountDetailMapper {
	
	int insertAccountDetail(AccountDetail accountDetail) throws Exception;
	 
	 
    int deleteByPrimaryKey(Long id);

    int insert(AccountDetail record);

    AccountDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountDetail record);

    int updateByPrimaryKey(AccountDetail record);
}