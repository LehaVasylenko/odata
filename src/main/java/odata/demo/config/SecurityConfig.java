//package odata.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//    private static final String TESTER = "TESTER";
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.ignoringAntMatchers("/odata.svc/**", "/h2-console/**"))
//                .headers(h -> h.frameOptions().sameOrigin()) // для H2
//                .authorizeHttpRequests(auth -> auth
//                        .antMatchers("/", "/index.html", "/favicon.ico", "/assets/**").permitAll()
//                        .anyRequest().hasRole(TESTER)
//                )
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
//
////    @Bean
////    UserDetailsService users() {
////        var tester = User.withUsername("tester").password("{noop}tester").roles(TESTER).build();
////        var editor = User.withUsername("editor").password("{noop}editor").roles("EDITOR").build();
////        return new InMemoryUserDetailsManager(tester);
////    }
//}
