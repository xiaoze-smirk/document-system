package edu.fjnu.service;

import edu.fjnu.entity.User;

import java.util.List;

/**
 * @author xiaoze
 * @date 2018/2/17
 */
public interface UserService {

    void insert(User record);

    void insertSelective(User record);

    void deleteByPrimaryKey(String userAccount);

    void updateByPrimaryKeySelective(User record);

    void updateByPrimaryKey(User record);

    User selectByPrimaryKey(String userAccount);

    List<User> findAllUser();
}
