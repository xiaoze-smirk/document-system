package edu.fjnu.serviceImpl;

import edu.fjnu.entity.Version;
import edu.fjnu.mapper.VersionMapper;
import edu.fjnu.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VersionServiceImpl implements VersionService {

    @Autowired
    VersionMapper versionMapper;

    @Override
    public void insert(Version record) {
        versionMapper.insert(record);
    }

    @Override
    public void insertSelective(Version record) {
        versionMapper.insertSelective(record);
    }

    @Override
    public void deleteByPrimaryKey(Integer verId) {
        versionMapper.deleteByPrimaryKey(verId);
    }

    @Override
    public void updateByPrimaryKeySelective(Version record) {
        versionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateByPrimaryKey(Version record) {
        versionMapper.updateByPrimaryKey(record);
    }

    @Override
    public Version selectByPrimaryKey(Integer verId) {
        return versionMapper.selectByPrimaryKey(verId);
    }

    @Override
    public List<Version> selectByDocNum(String docNum) {
        return versionMapper.selectByDocNum(docNum);
    }

    @Override
    public List<Version> findAllVersion() {
        return versionMapper.findAllVersion();
    }
}
