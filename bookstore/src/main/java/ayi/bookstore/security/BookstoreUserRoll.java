package ayi.bookstore.security;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum BookstoreUserRoll {
    USER(Sets.newHashSet(BookstoreUserPermission.AUTHOR_READ, BookstoreUserPermission.PUBLISHING_READ, BookstoreUserPermission.BOOK_READ)),
    ADMIN(Sets.newHashSet(BookstoreUserPermission.AUTHOR_READ, BookstoreUserPermission.PUBLISHING_READ, BookstoreUserPermission.BOOK_READ,
                        BookstoreUserPermission.AUTHOR_WRITE, BookstoreUserPermission.PUBLISHING_WRITE, BookstoreUserPermission.BOOK_WRITE));


    private final Set<BookstoreUserPermission> permissions;

    BookstoreUserRoll(Set<BookstoreUserPermission> permissions){
        this.permissions = permissions;
    }

    public Set<BookstoreUserPermission> getPermissions() {
        return permissions;
    }

    /*
    Con el objetivo de asignar los distintos permisos que tiene cada rol, 
    para ser utilizados por la annotation @PreAuthorize.
    */
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROL_" + this.name()));
        return permissions;
    }
}
