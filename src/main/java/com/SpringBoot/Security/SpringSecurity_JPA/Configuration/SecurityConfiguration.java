package com.SpringBoot.Security.SpringSecurity_JPA.Configuration;

import com.SpringBoot.Security.SpringSecurity_JPA.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);  //set the custom user details service
        authenticationProvider.setPasswordEncoder(passwordEncoder());  //set the password encoder - bcrypt
        return authenticationProvider;
    }

    //Adding Database Connection for retrieving users from Database.
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//        JdbcUserDetailsManager userDetailsManager=new JdbcUserDetailsManager(dataSource);
////        return new JdbcUserDetailsManager(dataSource);
//        //writing low level JPA code for fetching username,password and roles from database.
//
//        //fetching user details from user table
//        userDetailsManager.setUsersByUsernameQuery("select * from employee where user_name=?");
//
//        //fetching authorities details from user table
//        userDetailsManager.setAuthoritiesByUsernameQuery("select * from employee_role where user_name=?");
//
//        return userDetailsManager;
//
//    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//
//        //Adding all the Users --->in-memory user details strategy.
//        //Here we are storing all the passwords with no encoding algorithms=====>{noop}
//        UserDetails suvendu= User.builder()
//                .username("suvendu")
//                .password("{noop}test@123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails sonali= User.builder()
//                .username("sonali")
//                .password("{noop}test@123")
//                .roles("EMPLOYEE","MANAGER")
//                .build();
//        UserDetails akash= User.builder()
//                .username("akash")
//                .password("{noop}test@123")
//                .roles("EMPLOYEE","MANAGER","ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(suvendu,sonali,akash);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer->
                configurer.requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form->
                        form
                                .loginPage("/showCustomLogin")
                                .loginProcessingUrl("/authenticateUser")
                                .permitAll()
                )
                .logout(logout->logout.permitAll()
                ).exceptionHandling(configurer->
                        configurer.accessDeniedPage("/access-denied")
                );
        return http.build();
    }
}
