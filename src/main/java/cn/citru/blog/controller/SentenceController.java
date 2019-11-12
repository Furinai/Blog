package cn.citru.blog.controller;

import cn.citru.blog.entity.Response;
import cn.citru.blog.entity.Sentence;
import cn.citru.blog.service.SentenceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

@RestController
@EnableScheduling
@RequestMapping("/api")
public class SentenceController {

    private final SentenceService sentenceService;
    private final RestTemplate restTemplate;

    public SentenceController(SentenceService sentenceService, RestTemplate restTemplate) {
        this.sentenceService = sentenceService;
        this.restTemplate = restTemplate;
    }

    @Scheduled(cron = "0 0 4 * * ?")
    public void addSentence() throws IOException {
        Sentence sentence = new Sentence();
        String url = "https://open.iciba.com/dsapi/";
        ObjectMapper objectMapper = new ObjectMapper();
        String json = restTemplate.getForObject(url, String.class);
        Map map = objectMapper.readValue(json, Map.class);
        sentenceService.addSentence(sentence, map);
    }

    @GetMapping("/sentence")
    public Response getSentence() {
        Sentence sentence = sentenceService.getSentence();
        if (sentence == null) return new Response("error", "获取失败");
        return new Response("success", "获取成功", sentence);
    }
}
