package com.sarac.sarac.user.entitiy;

import com.sarac.sarac.baseEntitiy.BaseEntity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
public class User implements UserDetails {

    @Transient
    private final Set<GrantedAuthority> authorities = new HashSet<>();

    public User() {
        authorities.add(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Long kakaoId;

    @Column(length = 10)
    String nickname;

    String imagePath;

    Boolean isLibraryOpen;

    Boolean isReviewOpen;

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
