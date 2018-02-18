package edu.fjnu.serviceImpl;

import edu.fjnu.entity.Authority;
import edu.fjnu.mapper.AuthorityMapper;
import edu.fjnu.service.AuthorityService;
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
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityMapper authorityMapper;


    @Override
    public void insert(Authority record) {
        authorityMapper.insert(record);
    }

    @Override
    public void insertSelective(Authority record) {
        authorityMapper.insertSelective(record);
    }

    @Override
    public void deleteByPrimaryKey(String authorityId) {
        authorityMapper.deleteByPrimaryKey(authorityId);
    }

    @Override
    public void updateByPrimaryKeySelective(Authority record) {
        authorityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateByPrimaryKey(Authority record) {
        authorityMapper.updateByPrimaryKey(record);
    }

    @Override
    public Authority selectByPrimaryKey(String authorityId) {
        return authorityMapper.selectByPrimaryKey(authorityId);
    }

    @Override
    public List<Authority> findAllAuthority() {
        return authorityMapper.findAllAuthority();
    }
}
