package com.poc;

import java.io.IOException;

import javax.json.JsonObject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.servlet.annotation.WebFilter;

@WebFilter("/protected/*")
public class AuthFilter implements Filter {

	public static final String SLACK_USER_NAME = "SLACK_NAME";
	public static final String SLACK_USER_ID = "SLACK_ID";
	public static final String SLACK_USER_AVATAR = "SLACK_AVATAR";
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		
		if(session.getAttribute(SLACK_USER_NAME) == null) {		
			
			/* on veut accéder à une ressource protected et on n'est pas authentifié en session
			 * il faut passer un code */
			String url = "https://slack.com/api/oauth.access" 
					+ "?client_id=" + com.poc.Conf.clientId.getValue() 
					+ "&client_secret=" + com.poc.Conf.clientSecret.getValue()
					+ "&code=" + request.getParameter("code");
			
			JsonObject retour = new HttpRequest().queryPublic(url);

			if(retour != null && retour.getJsonObject("user") != null) {
				session.setAttribute(SLACK_USER_NAME, retour.getJsonObject("user").getString("name"));
				session.setAttribute(SLACK_USER_ID, retour.getJsonObject("user").getString("id"));
				session.setAttribute(SLACK_USER_AVATAR, retour.getJsonObject("user").getString("image_72"));
			} 
			System.out.println(retour);
		}
		
		filterChain.doFilter(request, response) ;

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
