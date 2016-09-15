package com.shorterurl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shorterurl.security.Permission;

/**
 * Security configure.
 *
 *
 * @author Sergey Stotskiy
 *
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static DefaultRolesPrefixPostProcessor defaultRolesPrefixPostProcessor() {
        return new DefaultRolesPrefixPostProcessor();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        // @formatter:off
        http
            .csrf().disable()   // disable for testing only
            .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**", "/webjars/**", "/test", "/help")
                    .permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/shorturl/**").permitAll()
                .antMatchers("/statistic/**").permitAll()
                .antMatchers("/user/**")
                    .hasAuthority(Permission.CABINET_ADMIN.name())
                .antMatchers("/references/admins/")
                    .hasAuthority(Permission.REF_ADMINS_VIEW.name())
                .antMatchers("/admin/settings/**")
                    .hasAuthority(Permission.EDIT_SETTINGS.name())
                .antMatchers("/references/")
                    .hasAuthority(Permission.REF_MENU.name())
//                .and()
//                    .authorizeRequests()
//                    .antMatchers("/statistic/**") // use short links for anonymous access
//                    .anonymous()
                .and()
                    .authorizeRequests()
                    .antMatchers("/account/") // registration for anonymous access
                    .anonymous()
                .anyRequest()
                    .fullyAuthenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .usernameParameter("logonName")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .deleteCookies("remember-me")
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .permitAll()
                .and()
            .rememberMe();
        // @formatter:on
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(new BCryptPasswordEncoder());
    }
}
