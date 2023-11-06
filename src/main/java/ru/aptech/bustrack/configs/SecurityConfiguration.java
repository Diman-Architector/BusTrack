package ru.aptech.bustrack.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain securiryFilterChain (HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/css/**").permitAll() //**- означает, все что лежит в этой папке
                        .antMatchers("/img/**").permitAll()
                        .antMatchers("/js/**").permitAll()
                        .antMatchers("/reg").permitAll()
                        .antMatchers("/user").permitAll()
                        .antMatchers("/api/**").permitAll()
                        .antMatchers("/admin").permitAll()
                        .antMatchers("/").permitAll() //список паттернов (маппингов) путей которые приходят разрешены
                        //.antMatchers("/user").hasRole("ADMIN") //доступ на определенный раздел сайта, например user, с сущностью ролей под ADMINом
                        .anyRequest().authenticated() // все другие запросы должны быть через авторизацию
                )
                .formLogin((form) -> form
                       .permitAll()
                )

                .logout((logout) -> logout.permitAll()); //страница выхода

            http.csrf().disable(); //отключим временно csrf
            http.headers().frameOptions().disable(); //отключим временно проверку заголовков
            return http.build();
        }
    @Bean
    public UserDetailsService userDetailsService(){ //стандартный класс в пакете
        UserDetails user = //создаем детали пользователя
                User.withDefaultPasswordEncoder() //с дефолтным шифрацией пароля
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();
        return new InMemoryUserDetailsManager(user); //хранить в памяти
    }
}
