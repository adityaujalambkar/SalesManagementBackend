package com.example.SalesManagementSystem.config;

import com.example.SalesManagementSystem.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;




@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtFilter jwtFilter;

    /*
     * Password Encoding
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    /*
     * Authentication Provider
     * Uses database through UserDetailsService
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(customUserDetailsService);
        provider.setPasswordEncoder(
                passwordEncoder()
        );

        return provider;
    }

    /*
     * Security Rules
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {


        return http

                .csrf(csrf ->
                        csrf.disable()
                )

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        /*
                         * Public APIs
                         */
                        .requestMatchers(
                                "/auth/**"
                        )
                        .permitAll()

                        /*
                         * Platform APIs
                         */
                        .requestMatchers(
                                "/platform/**"
                        )
                        .hasAuthority(
                                "SUPER_ADMIN"
                        )

                        /*
                         * Tenant Management
                         */
                        .requestMatchers(
                                "/tenant/users/**"
                        )
                        .hasAuthority(
                                "ADMIN"
                        )

                        /*
                         * Lead APIs
                         */
                        .requestMatchers(
                                "/leads/**"
                        )
                        .hasAnyAuthority(
                                "ADMIN",
                                "SALES_MANAGER",
                                "SALES_PERSON"
                        )

                        /*
                         * Restricted user
                         */
                        .requestMatchers(
                                "/restricted/**"
                        )
                        .hasAuthority(
                                "RESTRICTED"
                        )
                        .requestMatchers("/HelloNexa").authenticated()

                        .anyRequest()
                        .authenticated()
                )

                /*
                 * Authentication provider
                 */
                .authenticationProvider(
                        authenticationProvider()
                )

                /*
                 * JWT filter before username/password filter
                 */
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{

        return config.getAuthenticationManager();
    }

}