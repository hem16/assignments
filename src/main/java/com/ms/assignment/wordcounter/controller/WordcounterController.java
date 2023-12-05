package com.ms.assignment.wordcounter.controller;

import com.ms.assignment.wordcounter.service.WordcounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/word-counter")
public class WordcounterController {

    @Autowired
    private final WordcounterService wordcounterService;

    public WordcounterController(WordcounterService wordcounterService) {
        this.wordcounterService = wordcounterService;
    }
    @PostMapping("/add-words")
    public ResponseEntity addWords(@RequestBody List<String> words) {
        wordcounterService.addWords(words);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/word/{word}")
    public ResponseEntity<Long> getWordCount(@PathVariable String word) {
        return new ResponseEntity<>(wordcounterService.getWordCount(word),HttpStatus.OK);
    }

}
