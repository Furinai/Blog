package cn.citru.blog.service;

import cn.citru.blog.entity.Sentence;
import cn.citru.blog.mapper.SentenceMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

@Service
public class SentenceServiceImpl implements SentenceService {

    private final SentenceMapper sentenceMapper;

    public SentenceServiceImpl(SentenceMapper sentenceMapper) {
        this.sentenceMapper = sentenceMapper;
    }

    @Override
    public int addSentence(Sentence sentence, Map map) {
        sentence.setNote((String) map.get("note"));
        sentence.setContent((String) map.get("content"));
        sentence.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return sentenceMapper.insertSentence(sentence);
    }

    @Override
    public Sentence getSentence() {
        return sentenceMapper.selectSentence();
    }
}
