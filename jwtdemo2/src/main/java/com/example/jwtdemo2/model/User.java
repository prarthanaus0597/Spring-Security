package com.example.jwtdemo2.model;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="_user")
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer userId;
    private String name;
    private String  email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
//    Returns the authorities granted to the user. Cannot return null.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }
    //Returns the username used to authenticate the user. Cannot return null.

    @Override
    public String getUsername() {
        return email;
    }

//    //Indicates whether the user's account has expired. An expired account cannot be authenticated.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


//    Indicates whether the user is locked or unlocked. A locked user cannot be authenticated.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
//Indicates whether the user's credentials (password) has expired. Expired credentials prevent authentication.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
//Indicates whether the user is enabled or disabled. A disabled user cannot be authenticated.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
