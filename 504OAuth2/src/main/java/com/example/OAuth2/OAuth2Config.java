/*package com.example.OAuth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    //PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory().withClient("ci")
                .secret(fun().encode("sc"))
                .authorizedGrantTypes("password","refresh_token",
                        "authorization_code","client_credential")
                .scopes("read","write");
                //.redirectUris();
    }

    @Bean
    public NoOpPasswordEncoder fun(){
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }



    //{
    //    "error": "unsupported_grant_type",
    //    "error_description": "Unsupported grant type: password"
    //}

}*/
