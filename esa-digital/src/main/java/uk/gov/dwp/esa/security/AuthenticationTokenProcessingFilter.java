package uk.gov.dwp.esa.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;


public class AuthenticationTokenProcessingFilter extends GenericFilterBean {


	private static final String TOKEN_SESSION_ATTRIBUTE = "token";

	private AuthenticationManager authenticationManager;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		@SuppressWarnings("unchecked")

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession(false);
		
		try{
		if(session != null){
			String sessionId = session.getId();
			String token = (String) session.getAttribute(TOKEN_SESSION_ATTRIBUTE);
			String[] tokenList = token.split(":");
			if (!tokenList[0].equals("") && tokenList[0].equals(sessionId)) {
				// validate the token

				// build an Authentication object with the user's info
				CustomAuthenticationToken authentication = new CustomAuthenticationToken(token);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
				// set the authentication into the SecurityContext
				SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authentication));
			}
			chain.doFilter(request, response);
		}
		}catch(Exception e){
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		}
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
}
