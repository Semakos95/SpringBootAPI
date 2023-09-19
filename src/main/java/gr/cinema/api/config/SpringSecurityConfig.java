package gr.cinema.api.config;

import static org.springframework.security.config.Customizer.withDefaults;

import gr.cinema.api.service.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final SecurityUserDetailsService securityUserDetailsService;
    private final SpringBeans springBeans;
    @Autowired
    private final CustomUserDetailsService customUserDetailsService;

    public SpringSecurityConfig(SecurityUserDetailsService securityUserDetailsService, SpringBeans springBeans, CustomUserDetailsService customUserDetailsService) {
        this.securityUserDetailsService = securityUserDetailsService;
        this.springBeans = springBeans;
        this.customUserDetailsService = customUserDetailsService;
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

    @Bean
    public UserDetailsService users(){
        UserDetails admin = User.builder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        UserDetails user = User.builder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin,user);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserDetailsService).passwordEncoder(springBeans.passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();

    }

}
