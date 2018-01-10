package edu.fjnu.serviceImpl;

import edu.fjnu.entity.Document;
import edu.fjnu.mapper.DocumentMapper;
import edu.fjnu.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    DocumentMapper documentMapper;

    @Override
    public void insert(Document record) {
        documentMapper.insert(record);
    }

    @Override
    public void insertSelective(Document record) {
        documentMapper.insertSelective(record);
    }

    @Override
    public void deleteByPrimaryKey(String docNum) {
        documentMapper.deleteByPrimaryKey(docNum);
    }

    @Override
    public void updateByPrimaryKeySelective(Document record) {
        documentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateByPrimaryKey(Document record) {
        documentMapper.updateByPrimaryKey(record);
    }

    @Override
    public Document selectByPrimaryKey(String docNum) {
        return documentMapper.selectByPrimaryKey(docNum);
    }

    @Override
    public List<Document> findAllDocument() {
        return documentMapper.findAllDocument();
    }
}
