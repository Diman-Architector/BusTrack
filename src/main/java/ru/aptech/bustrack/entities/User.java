package ru.aptech.bustrack.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")

public class User implements UserDetails { //т.к. мы входим по Логину и Паролю нам надо реализовать функции интерфейса
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String login;
    private String password;
    @ManyToMany (fetch = FetchType.EAGER) //подгружение ролей пользователей в любом случае
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //возвращаем туда список ролей
        return roles;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
