package edu.fjnu.mapper;

import edu.fjnu.entity.PersistentLogins;

public interface PersistentLoginsMapper {

    void insert(PersistentLogins record);

    void insertSelective(PersistentLogins record);

    void deleteByUsername(String username);

    void updateByPrimaryKeySelective(PersistentLogins record);

    void updateByPrimaryKey(PersistentLogins record);

    PersistentLogins selectByPrimaryKey(String series);
}