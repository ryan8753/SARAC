package com.sarac.sarac.keywords.service;

import com.sarac.sarac.keywords.dto.response.KeywordDto;
import com.sarac.sarac.keywords.dto.response.KeywordListDto;
import com.sarac.sarac.keywords.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public KeywordListDto getTopKeywords() {
        return KeywordListDto.builder()
                .keywords(keywordRepository.findTop20ByOrderByCountDesc().stream()
                        .map(wordCount -> KeywordDto.builder()
                                .text(wordCount.getKeyword())
                                .value(wordCount.getCount())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

}
