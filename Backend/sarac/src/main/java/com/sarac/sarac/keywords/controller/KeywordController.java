package com.sarac.sarac.keywords.controller;

import com.sarac.sarac.keywords.dto.response.KeywordListDto;
import com.sarac.sarac.keywords.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/book/keyword")
public class KeywordController {

    private final KeywordService keywordService;

    @GetMapping
    public ResponseEntity<KeywordListDto> getTopWordCount() {
        return ResponseEntity.ok().body(keywordService.getTopKeywords());
    }
}
