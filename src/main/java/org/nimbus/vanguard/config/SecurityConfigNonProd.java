package org.nimbus.vanguard.config;

import org.nimbus.vanguard.filters.CsrfCookieFilter;
import org.nimbus.vanguard.filters.JWTGeneratorFilter;
import org.nimbus.vanguard.filters.JWTValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("!prod")
public class SecurityConfigNonProd {

    @Bean
    SecurityFilterChain customSecurityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        http.authorizeHttpRequests(requests -> requests
            .requestMatchers("/account/**").hasAuthority("VIEWACCOUNT")
            .requestMatchers("/balance/**").hasAnyAuthority("VIEWACCOUNT", "VIEWBALANCE")
            .requestMatchers("/loans/**").hasAuthority("VIEWLOANS")
            .requestMatchers("/cards/**").hasAuthority("VIEWCARDS")
            .requestMatchers("/user").authenticated()
            .requestMatchers("/contact/**", "/notices/**", "/register", "/error", "/invalidSession", "/").permitAll()
        );

        http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                            .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                            .ignoringRequestMatchers("/contact", "/register"))
            .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);

        http.sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.addFilterBefore(new JWTValidationFilter(), BasicAuthenticationFilter.class);
        http.addFilterAfter(new JWTGeneratorFilter(), BasicAuthenticationFilter.class);

        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());

        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));  // Allow specific origin
        configuration.setAllowedMethods(Collections.singletonList("*"));  // Allow all methods
        configuration.setAllowedHeaders(Collections.singletonList("*"));  // Allow all headers
        configuration.setExposedHeaders(Collections.singletonList("Authorization"));  // Expose Authorization header
        configuration.setAllowCredentials(true);  // Allow credentials
        configuration.setMaxAge(3600L);  // Cache preflight response for 1 hour

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // Apply CORS settings to all API paths
        return source;
    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
