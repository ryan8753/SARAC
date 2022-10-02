package com.sarac.sarac.statistics.controller;


import com.sarac.sarac.statistics.payload.response.StatisticsDTO;
import com.sarac.sarac.statistics.service.StatisticsService;
import com.sarac.sarac.user.entitiy.User;
import com.sarac.sarac.user.repository.UserRepository;
import com.sarac.sarac.user.service.UserService;
import com.sarac.sarac.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/statistics")
public class StatisticsController {


    private final UserService userService;

    private final JwtUtil jwtUtil;

    private final StatisticsService statisticsService;

    private final UserRepository userRepository;

    @GetMapping("/other/{userId}")
    public ResponseEntity<StatisticsDTO> getOthersStatistics(@PathVariable Long userId){
        return ResponseEntity.ok().body(statisticsService.getStatistics(userId));
    }

    @GetMapping("/me")
    public ResponseEntity<StatisticsDTO> getMyStatistics(@RequestHeader Map<String,Object> token){

        Long userId = userRepository.findOneByKakaoId((Long)jwtUtil.parseJwtToken((String) token.get("authorization")).get("id")).getId();
        return ResponseEntity.ok().body(statisticsService.getStatistics(userId));
    }



}
