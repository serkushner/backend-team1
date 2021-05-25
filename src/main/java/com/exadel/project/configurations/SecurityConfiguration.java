package com.exadel.project.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final DataSource dataSource;

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/**")
                .permitAll();
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {//.cors().configurationSource(corsConfigurationSource())
        http.httpBasic().and().cors().configurationSource(corsConfigurationSource()).and().authorizeRequests().antMatchers("/internship/**", "/signin/**", "/login", "/email/**", "interview/token/**", "/")
                .permitAll().and()//.formLogin().loginPage("/signin").permitAll().and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                /*.and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/authout"))
                .logoutSuccessUrl("/internship")
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .permitAll()*/
                .and()
                .csrf().disable();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("*");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200",
                "http://front-internship.s3-website.eu-central-1.amazonaws.com"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication().dataSource(dataSource).authoritiesByUsernameQuery(
                "SELECT administrator.login as login, role.role_name as role FROM administrator INNER JOIN administrator_has_role ON administrator.id = administrator_has_role.administrator_id INNER JOIN role ON administrator_has_role.role_id = role.id WHERE administrator.login = ?")
                .usersByUsernameQuery("SELECT login, password, 1 as enabled FROM administrator WHERE administrator.login = ?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();

    }

}