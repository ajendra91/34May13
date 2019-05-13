package com.example.Client4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AppController {

    @Autowired
    public OAuth2ClientContext oauth2ClientContext;

    @RequestMapping("/")
    public String hello() {
        return "Hello";
    }

    @RequestMapping("/emp1")
    public String emp1() {
        String token= oauth2ClientContext.getAccessToken().getValue();
        return token;
    }

    @RequestMapping("/emp2")
    public String emp2() {
        return "emp2";
    }


    /*@RequestMapping("/")
    public String home(Principal user) {
        return "Hello " + user.getName();
    }*/


}
