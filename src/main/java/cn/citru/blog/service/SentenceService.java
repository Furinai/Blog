package cn.citru.blog.service;

import cn.citru.blog.entity.Sentence;

import java.util.Map;

public interface SentenceService {
    int addSentence(Sentence sentence, Map map);

    Sentence getSentence();
}
