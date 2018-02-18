package edu.fjnu.service;

import edu.fjnu.entity.Authority;

import java.util.List;

/**
 * @author xiaoze
 * @date 2018/2/17
 */
public interface AuthorityService {
    void insert(Authority record);

    void insertSelective(Authority record);

    void deleteByPrimaryKey(String authorityId);

    void updateByPrimaryKeySelective(Authority record);

    void updateByPrimaryKey(Authority record);

    Authority selectByPrimaryKey(String authorityId);

    List<Authority> findAllAuthority();
}
