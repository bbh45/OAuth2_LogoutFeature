package com.example;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SampleController {

    @GetMapping("/user_details")
    public OAuth2User getUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (OAuth2User) authentication.getPrincipal();
    }

    @RequestMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        //return Collections.singletonMap("name", principal.getAttribute("name"));
        Map<String, Object> map = new HashMap<>();
        map.put("name", principal.getAttribute("name"));
        map.put("email", principal.getAttribute("email"));
        map.put("following",principal.getAttribute("following"));
        return map;
    }
}
