package jym.agtsys.dao;

import jym.agtsys.domain.User;

public interface UserMapper {
	User selectUserByUser(User user)  throws Exception;
	
	int updateUser(User user)  throws Exception;
	
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKey(User record);
}