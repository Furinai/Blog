package cn.citru.blog.mapper;

import cn.citru.blog.entity.Sentence;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SentenceMapper {
    int insertSentence(Sentence sentence);

    Sentence selectSentence();
}
