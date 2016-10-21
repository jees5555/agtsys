package jym.agtsys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import jym.agtsys.domain.User;
import jym.agtsys.util.MySqlPageTool;

public interface UserMapper {
	User selectUserByUser(User user)  throws Exception;
	
	List <User> selectPageUsersByUser (@Param("user")User user,@Param("pageTool")MySqlPageTool pageTool) throws Exception;
	
	int selectUsersCount(User user) throws Exception ;
	
	int updateUser(User user)  throws Exception;
	
	
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKey(User record);
}