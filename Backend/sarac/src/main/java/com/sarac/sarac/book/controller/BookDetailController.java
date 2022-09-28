package com.sarac.sarac.book.controller;

import com.sarac.sarac.book.dto.request.BookDetailRequest;
import com.sarac.sarac.book.dto.response.BookInfoDto;
import com.sarac.sarac.book.service.BookDetailService;
import com.sarac.sarac.library.type.LibraryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/book/detail")
public class BookDetailController {

    @Autowired
    BookDetailService bookDetailService;

    @GetMapping
    public ResponseEntity<BookInfoDto> getBookDetail(@RequestParam String isbn, @RequestParam Long userId) {
        System.out.println(isbn+userId);
        BookInfoDto bookInfoDto = bookDetailService.showBookInfo(userId,isbn);

        return ResponseEntity.status(200).body(bookInfoDto);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> updateLibrary(@RequestBody BookDetailRequest bookDetailRequest) {

        Map<String, Object> resultMap = new HashMap<>();
        try{
            bookDetailService.updateLibraryType(bookDetailRequest);
            resultMap.put("message", "success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteLibrary(@RequestParam String isbn, @RequestParam Long userId) {

        Map<String, Object> resultMap = new HashMap<>();
        try{
            bookDetailService.deleteLibraryType(userId,isbn);
            resultMap.put("message", "success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }






}
