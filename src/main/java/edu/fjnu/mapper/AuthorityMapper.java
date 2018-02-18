package edu.fjnu.mapper;

import edu.fjnu.entity.Authority;

import java.util.List;

public interface AuthorityMapper {

    void insert(Authority record);

    void insertSelective(Authority record);

    void deleteByPrimaryKey(String authorityId);

    void updateByPrimaryKeySelective(Authority record);

    void updateByPrimaryKey(Authority record);

    Authority selectByPrimaryKey(String authorityId);

    List<Authority> findAllAuthority();

}