package codes.praise.submission.model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User implements UserDetails {
    private String firstName;
    private String lastName;
    private String username = this.getFirstName()
            .substring(0, 1)
            .toLowerCase(Locale.ROOT) +
            this.getLastName().toLowerCase();
    private String email;
    private Role role;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = this.getRole().getAuthorities();
        return authorities;
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
