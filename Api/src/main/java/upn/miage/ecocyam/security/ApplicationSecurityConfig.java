package upn.miage.ecocyam.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static upn.miage.ecocyam.security.ApplicationUserPermission.ADMIN_WRITE;
import static upn.miage.ecocyam.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Override this method to configure the {@link HttpSecurity}. Typically subclasses
     * should not invoke this method by calling super as it may override their
     * configuration. The default configuration is:
     *
     * <pre>
     * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
     * </pre>
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .antMatchers("/api/**").hasRole(USER.name())
                // On utilise la méthode par annnotation dans le controleur @PreAutorize
//                .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAnyAuthority(ADMIN_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST,"/management/api/**").hasAnyAuthority(ADMIN_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/management/api/**").hasAnyAuthority(ADMIN_WRITE.getPermission())
//                .antMatchers("/management/api/**").hasAnyRole(ADMIN.name(), ADMINLESSPRIVILEGE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    /**
     * Allows modifying and accessing the {@link UserDetailsService} from
     * {@link #userDetailsServiceBean()} without interacting with the
     * {@link ApplicationContext}. Developers should override this method when changing
     * the instance of {@link #userDetailsServiceBean()}.
     *
     * @return the {@link UserDetailsService} to use
     */
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
       UserDetails users =  User.builder()
                .username("Jayson Tatum")
                .password(passwordEncoder.encode("one"))
                //.roles(USER.name())
               .authorities(USER.getGrantedAuthorities())
                .build();

       UserDetails admins = User.builder()
               .username("Jaylen Brown")
               .password(passwordEncoder.encode("two"))
               //.roles(ADMIN.name())
               .authorities(ADMIN.getGrantedAuthorities())
               .build();

        UserDetails adminsLessPrivilege = User.builder()
                .username("Marcus Smart")
                .password(passwordEncoder.encode("three"))
                //.roles(ADMINLESSPRIVILEGE.name())
                .authorities(ADMINLESSPRIVILEGE.getGrantedAuthorities())
                .build();

       return new InMemoryUserDetailsManager(
               users,
               admins,
               adminsLessPrivilege
       );
    }
}