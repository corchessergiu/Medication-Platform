package ro.tuc.ds2020.controllers;



import com.google.common.collect.ImmutableList;
import ro.tuc.ds2020.controllers.JwtAuthenticationFilter;
import ro.tuc.ds2020.controllers.JwtAuthorizationFilter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.context.annotation.Bean;
import ro.tuc.ds2020.services.UserDataService;

import java.util.Collections;

import static ro.tuc.ds2020.controllers.SecurityConstants.SIGN_UP_URL;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserDataService userDataService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserDataService userDataService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDataService = userDataService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                    .authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers("/socket").permitAll()
                .antMatchers("/socket/**").permitAll()
                .antMatchers(HttpMethod.POST, "/patient/**").permitAll()
               .antMatchers(HttpMethod.PUT, "/patient/**").permitAll()
               .antMatchers(HttpMethod.DELETE, "/patient/**").permitAll()
               .antMatchers(HttpMethod.GET, "/patient/**").permitAll()
                .antMatchers(HttpMethod.POST, "/caregiver/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/caregiver/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/caregiver/**").permitAll()
               .antMatchers(HttpMethod.GET, "/caregiver/**").permitAll()
                .antMatchers( HttpMethod.POST,"/doctor/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/doctor/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/doctor/**").permitAll()
               .antMatchers(HttpMethod.GET, "/doctor/**").permitAll()
                .antMatchers( HttpMethod.POST,"/medication").permitAll()
                .antMatchers(HttpMethod.PUT, "/medication").permitAll()
                .antMatchers(HttpMethod.DELETE, "/medication").permitAll()
                .antMatchers(HttpMethod.GET, "/medication").permitAll()

                .antMatchers( HttpMethod.POST,"/medication/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/medication/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/medication/**").permitAll()
                .antMatchers(HttpMethod.GET, "/medication/**").permitAll()

                .antMatchers( HttpMethod.POST,"/medicationPlan").permitAll()
                .antMatchers(HttpMethod.PUT, "/medicationPlan").permitAll()
                .antMatchers(HttpMethod.DELETE, "/medicationPlan").permitAll()
                .antMatchers(HttpMethod.GET, "/medicationPlan").permitAll()

                .antMatchers( HttpMethod.POST,"/medicationPlan/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/medicationPlan/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/medicationPlan/**").permitAll()
                .antMatchers(HttpMethod.GET, "/medicationPlan/**").permitAll()
              .anyRequest().authenticated()
                .and()
               .addFilter(new JwtAuthenticationFilter(authenticationManager()))
               .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                 http.authorizeRequests()

                .and().anonymous();
        http.logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)));

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDataService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(ImmutableList.of("Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Origin", "X-Requested-With", "Content-Type", "CORELATION_ID", "Authorization"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}