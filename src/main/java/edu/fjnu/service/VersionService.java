package edu.fjnu.service;

import edu.fjnu.entity.Version;

import java.util.List;

public interface VersionService {

    void insert(Version record);

    void insertSelective(Version record);

    void deleteByPrimaryKey(Integer verId);

    void updateByPrimaryKeySelective(Version record);

    void updateByPrimaryKey(Version record);

    Version selectByPrimaryKey(Integer verId);

    List<Version> findAllVersion();

}
