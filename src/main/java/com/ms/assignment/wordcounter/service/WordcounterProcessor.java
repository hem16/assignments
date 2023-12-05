package com.ms.assignment.wordcounter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WordcounterProcessor implements WordcounterService{
    private Map<String, Long> wordMap;
    @Autowired
    private TranslatorService translatorService;

    public WordcounterProcessor(TranslatorService translatorService) {
        this.wordMap = new HashMap<>();
        this.translatorService = translatorService;
    }

    public void addWords(List<String> words) {
        wordMap = words
                        .stream()
                        .filter(word -> isValidWord(word))
                        .collect(Collectors
                                .groupingBy(w -> translatorService.translate(w.toLowerCase()),Collectors.counting()));
    }

    public Long getWordCount(String word){
        String translatedWord = translatorService.translate(word);
        return wordMap.getOrDefault(translatedWord,0L);
    }

    private boolean isValidWord(String word) {
        return word.matches("[a-zA-Z]+");
    }

}
