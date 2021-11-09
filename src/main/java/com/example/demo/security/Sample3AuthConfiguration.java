package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Sample3AuthConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    // 平文のパスワードをエンコーダにかけてハッシュ化し，"user1"と関連付けている．ロール名は"USER"
    // プログラム中に素のパスワードが含まれることになるので望ましくない
    auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder().encode("p@ss")).roles("USER");
    auth.inMemoryAuthentication().withUser("user2").password(passwordEncoder().encode("p@ss")).roles("USER");

    // $ sshrun htpasswd -nbBC 10 admin adm1n
    auth.inMemoryAuthentication().withUser("admin")
        .password("$2y$10$3e7Hs2QZ/p91yJVgP5y/1OC7AC8jfc6YDYDzMGK1XieDlNR2nBGDe").roles("ADMIN");

  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin();
    http.authorizeRequests().antMatchers("/").authenticated();
    http.logout().logoutSuccessUrl("/");

    http.csrf().disable();
    http.headers().frameOptions().disable();
  }
}
