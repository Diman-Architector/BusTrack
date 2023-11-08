package ru.aptech.bustrack.configs;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.aptech.bustrack.services.CustomUserDetailsService;


@Component //применяется к классу за которым Spring будет следить самостоятельно делает его Bin
public class CustomAuthProvider implements AuthenticationProvider {

    private PasswordEncoder passwordEncoder;
    private CustomUserDetailsService userDetailsService;

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

   public void setUserDetailsService(CustomUserDetailsService userDetailsService) {
   this.userDetailsService = userDetailsService;
   }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {//сюда придет логин и пароль
          String login = authentication.getName();
          String password = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(login); //проверка есть ли в базе данных такой user
        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        return new UsernamePasswordAuthenticationToken(
                userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) { //класс поддерживается, если мы используем для входа логин и пароль
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}