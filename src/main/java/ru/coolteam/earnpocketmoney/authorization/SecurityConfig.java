package ru.coolteam.earnpocketmoney.authorization;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
//                .antMatchers("/api/**").authenticated()
//                .antMatchers("/api/v1/tasks/create").hasRole("PARENT")
//                .antMatchers("/api/v1/children/**").hasRole("CHILDREN")
                .antMatchers("/register", "/auth").permitAll()
                .and()
                .headers().frameOptions().disable();
       //         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }

    //почему то появилась закольцованность, хотя в аналогичном проекте такой ошибки не было

   /* @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

}
