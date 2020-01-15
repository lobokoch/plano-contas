/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:56:10.144
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.planocontas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.kerubin.api.database.core.ServiceContext;

public class ServiceHandlerInterceptorAdapter extends HandlerInterceptorAdapter {
	
	public static final String HEADER_USER = "X-User-Header";
	public static final String HEADER_TENANT = "X-Tenant-Header";
	public static final String HEADER_TENANT_ACCOUNT_TYPE = "X-Tenant-AccountType-Header";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		updateServiceContext(request);
		
		return true;
	}
	
	private void updateServiceContext(HttpServletRequest request) {
		String currentTenant = request.getHeader(HEADER_TENANT);
		String currentUser = request.getHeader(HEADER_USER);
		String currentTenantAccountType = request.getHeader(HEADER_TENANT_ACCOUNT_TYPE);
		
		ServiceContext.setTenant(currentTenant);
		ServiceContext.setUser(currentUser);
		ServiceContext.setTenantAccountType(currentTenantAccountType);
		
		ServiceContext.setDomain(FinanceiroPlanoContasConstants.DOMAIN);
		ServiceContext.setService(FinanceiroPlanoContasConstants.SERVICE);
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		ServiceContext.clear();
		
	}

}

