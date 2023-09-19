package com.eduardokp.handson.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // require all requests to be authenticated except for the resources
        http
                .authorizeRequests()
                    .antMatchers("/**/emissao.xhtml").permitAll()
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/login.xhtml").permitAll()
                .failureUrl("/login.xhtml?error=true");
        http.logout().logoutSuccessUrl("/login.xhtml");
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}admin").roles("ADMIN")
                .and()
                .withUser("gestor1").password("{noop}gestor1").roles("GESTOR")
                .and()
                .withUser("gestor2").password("{noop}gestor2").roles("GESTOR");
    }
}
