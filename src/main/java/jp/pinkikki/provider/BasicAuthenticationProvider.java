package jp.pinkikki.provider;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class BasicAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication paramAuthentication) throws AuthenticationException {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        return new UsernamePasswordAuthenticationToken(paramAuthentication.getPrincipal(), authorities);
    }

    @Override
    public boolean supports(Class<?> paramClass) {
        return true;
    }

}
