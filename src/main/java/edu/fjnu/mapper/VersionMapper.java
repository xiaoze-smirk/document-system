package edu.fjnu.mapper;

import edu.fjnu.entity.Version;

import java.util.List;

public interface VersionMapper {

    void insert(Version record);

    void insertSelective(Version record);

    void deleteByPrimaryKey(Integer verId);

    void updateByPrimaryKeySelective(Version record);

    void updateByPrimaryKey(Version record);

    Version selectByPrimaryKey(Integer verId);

    Version selectByItemId(Integer itemId);

    List<Version> findAllVersion();
}