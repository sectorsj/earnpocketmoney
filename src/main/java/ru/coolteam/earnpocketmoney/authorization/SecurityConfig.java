package ru.coolteam.earnpocketmoney.authorization;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.coolteam.earnpocketmoney.authorization.jwt.JwtFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   private final JwtFilter jwtFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          //      .httpBasic().disable()
           //     .csrf().disable()
         //       .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         //       .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
//                .antMatchers("/api/v1/tasks/create").hasRole("PARENT")
//                .antMatchers("/api/v1/children/**").hasRole("CHILDREN")
                .antMatchers("/register", "/auth").permitAll()
                .and()
                .headers().frameOptions().disable();
       //         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }


    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

}
