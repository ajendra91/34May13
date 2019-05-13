package com.example.Client5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableOAuth2Client
public class AppSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    public OAuth2ClientContext oauth2ClientContext;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**").authorizeRequests()
                .antMatchers("/", "/login**")
                .permitAll().anyRequest()
                .authenticated();

        http.authorizeRequests().and().logout()
                .logoutUrl("/logout").logoutSuccessUrl("/");

        http.authorizeRequests()
                .and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);

    }

    private Filter ssoFilter() {

        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();

        OAuth2ClientAuthenticationProcessingFilter myFilter =
                new OAuth2ClientAuthenticationProcessingFilter
                        ("/login/my");
        OAuth2RestTemplate myTemplate = new OAuth2RestTemplate(my(), oauth2ClientContext);
        myFilter.setRestTemplate(myTemplate);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(myResource().getUserInfoUri(), my().getClientId());
        tokenServices.setRestTemplate(myTemplate);
        myFilter.setTokenServices(tokenServices);
        filters.add(myFilter);

        /*OAuth2ClientAuthenticationProcessingFilter myFilter2 =
                new OAuth2ClientAuthenticationProcessingFilter
                        ("/login/my2");
        OAuth2RestTemplate myTemplate2 = new OAuth2RestTemplate(my2(), oauth2ClientContext);
        myFilter2.setRestTemplate(myTemplate2);
        UserInfoTokenServices tokenServices2 = new UserInfoTokenServices(myResource2().getUserInfoUri(), my2().getClientId());
        tokenServices2.setRestTemplate(myTemplate2);
        myFilter2.setTokenServices(tokenServices2);
        filters.add(myFilter2);*/

        filter.setFilters(filters);
        return filter;
    }

    @Bean
    @ConfigurationProperties("security.oauth2.client")
    public AuthorizationCodeResourceDetails my() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("security.oauth2.resource")
    public ResourceServerProperties myResource() {
        return new ResourceServerProperties();
    }

    /*@Bean
    @ConfigurationProperties("github.client")
    public AuthorizationCodeResourceDetails my2() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("github.resource")
    public ResourceServerProperties myResource2() {
        return new ResourceServerProperties();
    }*/

    @Bean
    public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }
}