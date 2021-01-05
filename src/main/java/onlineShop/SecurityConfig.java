package onlineShop;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.context.annotation.Bean;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource; // dataSource is our database, in ApplicationConfig

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin()
                .loginPage("/login");
        http
                .authorizeRequests()
                .antMatchers("/cart/**").hasAuthority("ROLE_USER")
                .antMatchers("/get*/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/admin*/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().permitAll();
        //check if url is qualify with authority

        http
                .logout()
                .logoutUrl("/logout");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //define how this framework help us to valid information

        // Admin way, when server down, this will delete
        auth
                .inMemoryAuthentication().withUser("yudaiyin@gmail.com").password("123").authorities("ROLE_ADMIN");

        // normal way, get information from our current database
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT emailId, password, enabled FROM users WHERE emailId=?") // ? in url from client
                .authoritiesByUsernameQuery("SELECT emailId, authorities FROM authorities WHERE emailId=?");

    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        //NoOpPasswordEncoder will not encode, just for early stage development
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
