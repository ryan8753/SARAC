package com.sarac.sarac.book.controller;

import com.sarac.sarac.book.dto.request.BookDetailRequest;
import com.sarac.sarac.book.dto.response.BookInfoDto;
import com.sarac.sarac.book.service.BookDetailService;
import com.sarac.sarac.library.type.LibraryType;
import com.sarac.sarac.user.repository.UserRepository;
import com.sarac.sarac.user.service.UserService;
import com.sarac.sarac.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/book/detail")
public class BookDetailController {

    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    private final BookDetailService bookDetailService;

    @GetMapping
    public ResponseEntity<BookInfoDto> getBookDetail(@RequestParam String isbn, @RequestHeader Map<String,Object> token) {

        Long userId = userRepository.findOneByKakaoId((Long)jwtUtil.parseJwtToken((String) token.get("authorization")).get("id")).getId();
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
    public ResponseEntity<Map<String, Object>> deleteLibrary(@RequestParam String isbn, @RequestHeader Map<String,Object> token) {

        Long userId = userRepository.findOneByKakaoId((Long)jwtUtil.parseJwtToken((String) token.get("authorization")).get("id")).getId();
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
