package com.okta.config;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
class SpringSecurityConfiguration {

  // @Bean
  // SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  //   return http
  //            .authorizeRequests((authorize) -> authorize.anyRequest().permitAll())
  //            .addFilterBefore(new OktaSecurityFilter(), BasicAuthenticationFilter.class) 
  //            .build();
  // }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
             .authorizeRequests((authorize) -> authorize.anyRequest().authenticated()) // All requests require authentication
             .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt) // validates access tokens as JWTs
             .build();
  }
}

