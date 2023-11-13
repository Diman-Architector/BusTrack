package ru.aptech.bustrack.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.aptech.bustrack.services.CustomUserDetailsService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;
@SuppressWarnings("unused")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private CustomAuthProvider customAuthProvider; // указываем bean нашего customAuthProvider
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mail.ru");
        mailSender.setPort(465);

        mailSender.setUsername("");
        mailSender.setPassword("");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public CustomUserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthFailureHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthSuccessHandler();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authProvider() {
        CustomAuthProvider authProvider = new CustomAuthProvider();
        authProvider.setUserDetailsService(userDetailsService()); //сюда передаем компоненты из CustomUserDetailsService
        authProvider.setPasswordEncoder(passwordEncoder()); //сюда передаем компоненты из CustomUserDetailsService
        return authProvider;
    }
    @Bean
    public SecurityFilterChain securiryFilterChain (HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/css/**").permitAll() //**- означает, все что лежит в этой папке
                        .antMatchers("/img/**").permitAll()
                        .antMatchers("/js/**").permitAll()
                        .antMatchers("/api/user").permitAll()
                        .antMatchers("/user").hasAuthority(Constants.Roles.ROLE_USER_NAME) //разрешено только для юзера
                        .antMatchers("/admin").hasAuthority(Constants.Roles.ROLE_ADMIN_NAME)
                        .antMatchers("/").permitAll() //список паттернов (маппингов) путей которые приходят разрешены
                        //.antMatchers("/user").hasRole("ADMIN") //доступ на определенный раздел сайта, например user, с сущностью ролей под ADMINом
                        .anyRequest().authenticated() // все другие запросы должны быть через авторизацию
                )
                .authenticationProvider(authProvider()) //это Builder-метод сюда передается сконфигурированный bean - AuthenticationProvider authProvider
                .formLogin((form) -> form  //страница входа
                        .failureHandler(authenticationFailureHandler()) //для bean аутентификации
                        .successHandler(authenticationSuccessHandler()) //для bean успешной аутентификации
                        .loginPage("/") //страница входа, в случае чего нас туда перебрасывает
                        .loginProcessingUrl("/login") //куда мы отправляем наш логин и пароль
                        .usernameParameter("login") //параметр для входа юзера - по логину
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll()); //страница выхода

            http.csrf().disable(); //отключим временно csrf встраивание третьего человека в сеть
            http.cors().disable(); //отключили защиту когда с другого сервера пытаются на наш обратиться (используются только доверенные адреса)
            return http.build();
        }
}
