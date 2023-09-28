package br.com.bloco.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import br.com.bloco.controller.LoginMB;

import javax.servlet.http.HttpServletRequest;

public class LoginFilter implements Filter{
	
	@Inject
	private LoginMB loginMB;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURL().toString();
		if(url.contains("/restricted") && loginMB.getConta() == null) {
			res.sendRedirect(req.getServletContext().getContextPath() + "/login.xhtml");		
		}else {
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {
		
		
	}

}
