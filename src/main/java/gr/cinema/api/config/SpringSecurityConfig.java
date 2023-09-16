package gr.cinema.api.config;

import static org.springframework.security.config.Customizer.withDefaults;

import gr.cinema.api.service.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final SecurityUserDetailsService securityUserDetailsService;
    private final SpringBeans springBeans;


    public SpringSecurityConfig(SecurityUserDetailsService securityUserDetailsService, SpringBeans springBeans) {
        this.securityUserDetailsService = securityUserDetailsService;
        this.springBeans = springBeans;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login","/error", "/register","/favicon.ico", "/api/**", "/static/**", "webjars/**").permitAll()
                .anyRequest().authenticated()
                .and().cors(withDefaults())
                .formLogin()
                .defaultSuccessUrl("/admin", true)
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserDetailsService).passwordEncoder(springBeans.passwordEncoder());
    }

}
