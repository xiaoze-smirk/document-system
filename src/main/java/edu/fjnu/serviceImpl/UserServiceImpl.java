package edu.fjnu.serviceImpl;

import edu.fjnu.entity.User;
import edu.fjnu.mapper.AuthorityMapper;
import edu.fjnu.mapper.UserMapper;
import edu.fjnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaoze
 * @date 2018/2/17
 */
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AuthorityMapper authorityMapper;

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
        User user = userMapper.selectByPrimaryKey(userAccount);
        user.setAuthority(authorityMapper.selectByPrimaryKey(user.getUserAuthorityId()));
        return user;
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByPrimaryKey(username);
        user.setAuthority(authorityMapper.selectByPrimaryKey(user.getUserAuthorityId()));
        return user;
    }
}
