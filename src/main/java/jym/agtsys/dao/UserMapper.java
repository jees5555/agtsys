package jym.agtsys.dao;

import jym.agtsys.domain.User;

public interface UserMapper {
	User selectUserByUser(User user);
	
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}