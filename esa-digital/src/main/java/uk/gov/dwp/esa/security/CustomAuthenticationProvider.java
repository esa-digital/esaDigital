package uk.gov.dwp.esa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

	@Autowired
	private TokenUtils tokenUtils;

	public void setTokenUtils(TokenUtils tokenUtils) {
		this.tokenUtils = tokenUtils;
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
		CustomAuthenticationToken authenticationToken = (CustomAuthenticationToken) authentication;
		UserDetails user = null;
		if (tokenUtils.validate(authenticationToken.getToken())) {
			
			user = tokenUtils.getUserFromToken(authenticationToken.getToken());
		}
		return user;
	}
}