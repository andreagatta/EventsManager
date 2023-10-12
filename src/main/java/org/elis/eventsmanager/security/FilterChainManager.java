package org.elis.eventsmanager.security;

import org.elis.eventsmanager.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.elis.eventsmanager.util.UtilPath.*;

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
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector handlerMappingIntrospector) throws Exception {
        MvcRequestMatcher.Builder builder = new MvcRequestMatcher.Builder(handlerMappingIntrospector);
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .headers(c->c.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(builder.pattern("/h2-console/**")).permitAll()

                        .requestMatchers(builder.pattern(LOGIN_USER_CLIENT)).permitAll()

                        .requestMatchers(builder.pattern(SIGNIN_USER_CLIENT)).permitAll()

                        .requestMatchers(builder.pattern(USER_SUPERADMIN)).hasRole(Role.SUPERADMIN.toString())

                        .requestMatchers(builder.pattern(USER_ADMIN)).hasAnyRole(Role.ADMIN.toString(), Role.SUPERADMIN.toString())

                        .requestMatchers(builder.pattern(USER_VENDOR)).hasAnyRole(Role.VENDOR.toString(), Role.ADMIN.toString(), Role.SUPERADMIN.toString())

                        .requestMatchers(builder.pattern(USER_CLIENT)).hasAnyRole(Role.CLIENT.toString(), Role.VENDOR.toString(), Role.ADMIN.toString(), Role.SUPERADMIN.toString())

                        .requestMatchers(builder.pattern(PLACE_SUPERADMIN)).hasRole(Role.SUPERADMIN.toString())

                        .requestMatchers(builder.pattern(PLACE_ADMIN)).hasAnyRole(Role.ADMIN.toString(), Role.SUPERADMIN.toString())

                        .requestMatchers(builder.pattern(PLACE_VENDOR)).hasAnyRole(Role.VENDOR.toString(), Role.ADMIN.toString(), Role.SUPERADMIN.toString())

                        .requestMatchers(builder.pattern(PLACE_CLIENT)).hasAnyRole(Role.CLIENT.toString(), Role.VENDOR.toString(), Role.ADMIN.toString(), Role.SUPERADMIN.toString())

                        .requestMatchers(builder.pattern(EVENT_SUPERADMIN)).hasRole(Role.SUPERADMIN.toString())

                        .requestMatchers(builder.pattern(EVENT_ADMIN)).hasAnyRole(Role.ADMIN.toString(), Role.SUPERADMIN.toString())

                        .requestMatchers(builder.pattern(EVENT_VENDOR)).hasAnyRole(Role.VENDOR.toString(), Role.ADMIN.toString(), Role.SUPERADMIN.toString())

                        .requestMatchers(builder.pattern(EVENT_CLIENT)).hasAnyRole(Role.CLIENT.toString(), Role.VENDOR.toString(), Role.ADMIN.toString(), Role.SUPERADMIN.toString())

                        .anyRequest().authenticated()

                ).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(filterJwt, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    }

}
