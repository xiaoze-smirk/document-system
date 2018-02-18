package edu.fjnu.serviceImpl;

import edu.fjnu.entity.User;
import edu.fjnu.mapper.UserMapper;
import edu.fjnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaoze
 * @date 2018/2/17
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void insert(User record) {
        userMapper.insert(record);
    }

    @Override
    public void insertSelective(User record) {
        userMapper.insertSelective(record);
    }

    @Override
    public void deleteByPrimaryKey(String userAccount) {
        userMapper.deleteByPrimaryKey(userAccount);
    }

    @Override
    public void updateByPrimaryKeySelective(User record) {
        userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateByPrimaryKey(User record) {
        userMapper.updateByPrimaryKey(record);
    }

    @Override
    public User selectByPrimaryKey(String userAccount) {
        return userMapper.selectByPrimaryKey(userAccount);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }
}
