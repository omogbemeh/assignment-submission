package codes.praise.submission.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static codes.praise.submission.model.Permission.*;

public enum Role {
    STUDENT(Collections.emptySet()),
    CODE_REVIEWER(
            Set.of(
                    CODE_REVIEWER_CREATE,
                    CODE_REVIEWER_READ,
                    CODE_REVIEWER_UPDATE,
                    CODE_REVIEWER_DELETE
            )
    ),
    ADMIN(
            Set.of(
                    CODE_REVIEWER_CREATE,
                    CODE_REVIEWER_READ,
                    CODE_REVIEWER_UPDATE,
                    CODE_REVIEWER_DELETE,
                    ADMIN_CREATE,
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE
            )
    );

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission())).toList();
    }
}
