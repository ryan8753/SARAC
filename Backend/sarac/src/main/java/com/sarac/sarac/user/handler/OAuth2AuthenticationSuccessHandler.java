package com.sarac.sarac.user.handler;


import com.sarac.sarac.user.entitiy.User;
import com.sarac.sarac.user.repository.UserRepository;
import com.sarac.sarac.user.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        Long id = (Long)oAuth2User.getAttribute("id");
        User user;
        if((user = userRepository.findOneByKakaoId(id))==null){
            Map<String, Object> properties = (Map<String, Object>) oAuth2User.getAttributes().get("properties");
            System.out.println(properties.toString());
            user = User.builder()
                    .kakaoId(id)
                    .nickname((String)properties.get("nickname"))
                    .imagePath((String)properties.get("profile_image"))
                    .isLibraryOpen(true)
                    .isReviewOpen(true)
                    .build();
        }
        String accessToken = jwtUtil.generateJwtToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(id);

//        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        String url = makeRedirectUrl(accessToken, refreshToken);
        System.out.println("url: " + url);
        if (response.isCommitted()) {
            logger.debug("응답이 이미 커밋된 상태입니다. " + url + "로 리다이렉트하도록 바꿀 수 없습니다.");
            return;
        }
        getRedirectStrategy().sendRedirect(request, response, url);
    }

    private String makeRedirectUrl(String accessToken, String refreshToken) {
        return UriComponentsBuilder.fromUriString("https://test.sarac.tk?accessToken="+accessToken+"&refreshToken="+refreshToken)
                .build().toUriString();
    }
}
