package com.example.SalesManagementSystem.model;

import io.jsonwebtoken.lang.Collections;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;









@Getter
@RequiredArgsConstructor
public class CustomUserPrincipal implements UserDetails {

    private Tenant tenant;

    private TenantUser tenantUser;

    public CustomUserPrincipal(Tenant tenant){

        this.tenant = tenant;

    }

    public CustomUserPrincipal(TenantUser tenantUser){

        this.tenantUser = tenantUser;

    }

    @Override
    public Collection<? extends GrantedAuthority>
    getAuthorities() {
       return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return tenant.getPassword();
    }

    @Override
    public String getUsername() {
        return tenant.getEmail();
    }

    @Override
    public boolean isEnabled() {
        return true;
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
}
