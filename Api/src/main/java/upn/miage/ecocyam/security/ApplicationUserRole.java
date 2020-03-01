package upn.miage.ecocyam.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static upn.miage.ecocyam.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    /**
    * Définit les permissions pour les types d'utilisateurs
    * utilisations de Google Guava
    * Un utilisateur peut avoir 0..n permissions
     */
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(
            ADMIN_READ,
            ADMIN_WRITE,
            USER_READ,
            USER_WRITE)
    ),
    ADMINLESSPRIVILEGE(Sets.newHashSet(
            ADMIN_READ,
            USER_READ)
    );


    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
