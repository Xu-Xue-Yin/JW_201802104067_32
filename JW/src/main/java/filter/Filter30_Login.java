//信管182 徐学印 201802104067
package filter;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Filter 3", urlPatterns = {"/*"})
public class Filter30_Login implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter 3 - log begins");
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String path= httpServletRequest.getRequestURI();
        if (path.contains("/login")){
            filterChain.doFilter(request,response);
        }else {
            HttpSession session = httpServletRequest.getSession();
            JSONObject message = new JSONObject();
            if (session == null || session.getAttribute("currentUser") == null) {
                message.put("message", "请登录");
                response.getWriter().println(message);
            } else {
                filterChain.doFilter(request, response);
            }
        }
        System.out.println("Filter 3 - log ends");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

}
