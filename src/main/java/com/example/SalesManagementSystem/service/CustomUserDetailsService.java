package com.example.SalesManagementSystem.service;

import com.example.SalesManagementSystem.model.CustomUserPrincipal;
import com.example.SalesManagementSystem.model.Tenant;
import com.example.SalesManagementSystem.model.TenantUser;
import com.example.SalesManagementSystem.repository.TenantRepo;
import com.example.SalesManagementSystem.repository.TenantUserRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;





@Service
@RequiredArgsConstructor
public class CustomUserDetailsService
implements UserDetailsService {

        @Autowired
        private final TenantRepo tenantRepository;
        @Autowired
        private final TenantUserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(
            String email)
            throws UsernameNotFoundException {

        Optional<Tenant> tenant =
                tenantRepository
                        .findByEmail(email);

        if(tenant.isPresent()){

            return new CustomUserPrincipal(
                    tenant.get()
            );
        }

        Optional<TenantUser> user =
                userRepository
                        .findByEmail(email);

        if(user.isPresent()){

            return new CustomUserPrincipal(
                    user.get()
            );
        }

        throw new UsernameNotFoundException(
                "User not found"
        );
    }
}
