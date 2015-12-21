package com.restaurant.serviceUtilities;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.entities.User.TypeOfUser;
import com.restaurant.serviceBeans.UserBean;

@WebFilter("/staffPages/*")
public class AuthorizationFilter implements Filter {
//	@Inject
//	private UserBean userBean;

//    private static final String AJAX_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
//        + "<partial-response><redirect url=\"%s\"></redirect></partial-response>";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Get the loginBean from session attribute
//        User loginUser = userBean.getLoginUser();
        UserBean userBean = (UserBean)((HttpServletRequest)request).getSession().getAttribute("userBean");
         
        // For the first application request there is no loginBean in the session so user needs to log in
        // For other requests loginBean is present but we need to check if user has logged in successfully
        if (userBean == null || userBean.getLoginUser().getTypeOfUser()==TypeOfUser.CUSTOMER)/*.getTypeOfUser()!=TypeOfUser.ADMINISTRATOR*/ {
            String contextPath = ((HttpServletRequest)request).getContextPath();
            ((HttpServletResponse)response).sendRedirect(contextPath + "/admin-Staff.xhtml");
        }
         
        chain.doFilter(request, response);
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
