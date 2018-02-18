package edu.fjnu.mapper;

import edu.fjnu.entity.User;

import java.util.List;

public interface UserMapper {

    void insert(User record);

    void insertSelective(User record);

    void deleteByPrimaryKey(String userAccount);

    void updateByPrimaryKeySelective(User record);

    void updateByPrimaryKey(User record);

    User selectByPrimaryKey(String userAccount);

    List<User> findAllUser();

}