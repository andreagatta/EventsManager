package org.elis.eventsmanager.security;

import org.elis.eventsmanager.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class FilterChainManager {
    private final FilterJwt filterJwt;
    private final AuthenticationProvider authenticationProvider;

    public FilterChainManager(FilterJwt filterJwt, AuthenticationProvider authenticationProvider) {
        this.filterJwt = filterJwt;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/all/**").permitAll()
                        .requestMatchers("/client/**").hasRole(org.elis.eventsmanager.model.Role.CLIENT.toString())
                        .requestMatchers("/vendor/**").hasAnyRole(Role.VENDOR.toString(), Role.ADMIN.toString(), Role.SUPERADMIN.toString())
                        .requestMatchers("/admin/**").hasAnyRole(Role.ADMIN.toString(), Role.SUPERADMIN.toString())
                        .requestMatchers("/superadmin/**").hasAnyRole(Role.SUPERADMIN.toString())
                        .anyRequest().permitAll()
                ).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(filterJwt, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    }

}
