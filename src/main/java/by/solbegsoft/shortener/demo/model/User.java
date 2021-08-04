package by.solbegsoft.shortener.demo.model;

import by.solbegsoft.shortener.demo.util.UserRole;
import by.solbegsoft.shortener.demo.util.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Data @Builder
@AllArgsConstructor @NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "uuid", nullable = false)
    @GeneratedValue
    private UUID uuid;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRole.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserStatus.ACTIVE.equals(userStatus);
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserStatus.ACTIVE.equals(userStatus);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserStatus.ACTIVE.equals(userStatus);
    }

    @Override
    public boolean isEnabled() {
        return UserStatus.ACTIVE.equals(userStatus);
    }
}