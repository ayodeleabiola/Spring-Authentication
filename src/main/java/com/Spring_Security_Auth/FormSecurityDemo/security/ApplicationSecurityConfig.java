package com.Spring_Security_Auth.FormSecurityDemo.security;
import static com.Spring_Security_Auth.FormSecurityDemo.security.ApplicationUserRole.*;
import static com.Spring_Security_Auth.FormSecurityDemo.security.ApplicationUserPermission.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private final PasswordEncoder passwordEncoder;
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder){
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","Index","/css/*","/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }
    //userDetailService helps you to retrieve your data from the database
    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
                UserDetails abiola= User
                      .builder()
                      .username("Ayodele")
                      .password(passwordEncoder.encode("abiola123"))
                     // .roles(STUDENT.name())
                        .authorities(STUDENT.getGrantedAuthorities())
                        .build();

                UserDetails khodeejah= User.builder()
                          .username("Khodeejah")
                          .password(passwordEncoder.encode("abiodun2000"))
               //         .roles(ADMIN.name())//ROLE_ADMIN
                          .authorities(ADMIN.getGrantedAuthorities())
                          .build();


        UserDetails hassan= User.builder()
                    .username("Hassan")
                    .password(passwordEncoder.encode("abiodun1998"))
                  //.roles(ADMIN_TRAIN.name())//ROLE_ADMINTRAINEE
                    .authorities(ADMIN_TRAIN.getGrantedAuthorities())
                    .build();


                return new InMemoryUserDetailsManager(
                        abiola,
                        khodeejah,
                        hassan

                );
    }
}


/*In Basic Auth,you need to specify Username and password in requset header
i.e. Every single request,you need to specify username and password
Note:antMatcher is used to specified for the individual login

Role:A role is the High level view in which you hava authorities and permission
The Roles and Permissions allow to secure the API

Permission Bases Authentication:

 // .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
 // .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
 // .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
 // .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ADMIN.name(),ADMIN_TRAIN.name())
 */
