package com.Spring_Security_Auth.FormSecurityDemo.security;
import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.Spring_Security_Auth.FormSecurityDemo.security.ApplicationUserPermission.*;


public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE)),
    ADMIN_TRAIN(Sets.newHashSet(COURSE_READ,STUDENT_READ));

    private final Set<ApplicationUserPermission> getpermission;

    ApplicationUserRole(Set<ApplicationUserPermission> getpermission){
        this.getpermission=getpermission;
    }
    public Set<SimpleGrantedAuthority>getGrantedAuthorities(){
       Set<SimpleGrantedAuthority>permissions= getpermission.stream()
                       .map(permission->new SimpleGrantedAuthority(
                            permission.getPermission()))
                       .collect(Collectors
                       .toSet());
               permissions.add(new SimpleGrantedAuthority("Role_"+this.name()));
               return permissions;
    }

}
