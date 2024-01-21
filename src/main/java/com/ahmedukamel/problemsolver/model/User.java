package com.ahmedukamel.problemsolver.model;

import com.ahmedukamel.problemsolver.dto.RegisterRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String phone;
    private String picture;
    private String title;
    private String bio;
    private Gender gender;
    @Column(nullable = false)
    private Integer rate;
    @Column(nullable = false)
    private Boolean accountExpired;
    @Column(nullable = false)
    private Boolean accountLocked;
    @Column(nullable = false)
    private Boolean credentialsExpired;
    @Column(nullable = false)
    private Boolean enabled;
    @Transient
    private Set<Role> roles = new HashSet<>();
    @CreationTimestamp
    private Date register;

    public User(RegisterRequest user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail().toLowerCase().strip();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.picture = user.getPicture();
        this.title = user.getTitle();
        this.bio = user.getBio();
        this.gender = user.getGender();
        this.roles = Set.of(Role.STUDENT);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
