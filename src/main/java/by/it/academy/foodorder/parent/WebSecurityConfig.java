package by.it.academy.foodorder.parent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{

        http.csrf().disable();

        http.authorizeRequests().antMatchers("/addFood", "/foodList"
                , "/foodList", "/foodList", "/updateFood", "/userList")
                                            .access("hasRole('ROLE_ADMIN')");

        http.authorizeRequests().antMatchers("/changePassword", "/changePhone", "/myAccount"
                , "/myBasket").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

        http.authorizeRequests()
                    .antMatchers("/resources/**", "/registration").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception{
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());

        authenticationManagerBuilder
                .inMemoryAuthentication()
                .withUser("user").password(bCryptPasswordEncoder().encode("user")).roles("USER")
                .and()
                .withUser("admin").password(bCryptPasswordEncoder().encode("admin")).roles("ADMIN");
    }

}
