package com.example.jwtdemo2.security;


        import lombok.RequiredArgsConstructor;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.authentication.AuthenticationProvider;
        import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.http.SessionCreationPolicy;
        import org.springframework.security.web.SecurityFilterChain;
        import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
        import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
        import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

        import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {


    @Autowired
    private JwtAuthenticationEntryPoint point;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,MvcRequestMatcher.Builder mvc) throws Exception {
        try {
            http.cors().disable();
            http.httpBasic().disable();
            http.csrf().disable();

            http.authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers(mvc.pattern("/api/v1/auth/**")).permitAll()
                    .anyRequest()
                    .authenticated());
//                    .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                   http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            http.authenticationProvider(authenticationProvider);
            http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

            ;
        }
        catch (Exception e){

        }
        return http.build();

    }
}