package ro.tuc.ds2020.controllers;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static  ro.tuc.ds2020.controllers.SecurityConstants.*;

import ro.tuc.ds2020.entities.UserData;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

        @Override
        public Authentication attemptAuthentication(HttpServletRequest req,
            HttpServletResponse res) throws AuthenticationException {
        try {

            UserData creds = new ObjectMapper()
                    .readValue(req.getInputStream(), UserData.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
        protected void successfulAuthentication(HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader("Access-Control-Expose-Headers","Authorization");
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }


}
