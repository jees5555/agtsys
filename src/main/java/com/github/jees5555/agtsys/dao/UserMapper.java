package com.github.jees5555.agtsys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.jees5555.agtsys.domain.User;
import com.github.jees5555.agtsys.util.MySqlPageTool;

public interface UserMapper {
	User selectUserByUser(User user) throws Exception;

	List<User> selectPageUsersByUser(@Param("user") User user, @Param("pageTool") MySqlPageTool pageTool)
			throws Exception;

	int selectUsersCount(User user) throws Exception;

	int updateUser(User user) throws Exception;

	int insertUser(User user) throws Exception;

	int deleteUser(User user) throws Exception;

	int insert(User record);

	User selectByPrimaryKey(Long id);

	int updateByPrimaryKey(User record);
}