package co.istad.mbanking.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Bean
    DaoAuthenticationProvider configDaoAuthenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

//    @Bean
//    InMemoryUserDetailsManager configUserSecurity(){
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder.encode("admin123"))
//                .roles("USER","ADMIN").build();
//
//
//        UserDetails editor = User
//                .withUsername("customer")
//                .password(passwordEncoder.encode("customer123"))
//                .roles("USER", "CUSTOMER")
//                .build();
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(admin);
//        manager.createUser(editor);
//        return  manager;
//    }


    // create method SecurityFilterChain
    @Bean // create bean
    SecurityFilterChain configureApiSecurity(HttpSecurity http) throws Exception {

        // make all endpoint have security
        http.authorizeHttpRequests(
                endpoint -> endpoint
                        .requestMatchers("/api/v1/auth/**").permitAll()

                        .requestMatchers(HttpMethod.POST,"api/v1/accounts/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"api/v1/accounts/**").hasAnyRole("ADMIN","CUSTOMER")
                        .requestMatchers(HttpMethod.DELETE,"api/v1/accounts/**").hasAnyRole("ADMIN","CUSTOMER")
                        .requestMatchers(HttpMethod.PUT,"api/v1/accounts/**").hasAnyRole("ADMIN","CUSTOMER")
                        .requestMatchers(HttpMethod.PATCH,"api/v1/accounts/**").hasAnyRole("ADMIN","CUSTOMER")

                        .requestMatchers(HttpMethod.POST,"api/v1/account-types/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"api/v1/account-types/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"api/v1/account-types/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"api/v1/account-types/**").hasAnyRole("ADMIN")

                        .anyRequest().authenticated());

        // make http basic auth mean use userName and password for login
        http.httpBasic(Customizer.withDefaults());

        // disable csrf token
        http.csrf(token->token.disable());

        // make session stateless
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();

    }
}
