package paradiseTravels.servlets.filter;

import com.google.gson.Gson;
import paradiseTravels.model.User;
import paradiseTravels.services.user.UsersService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    final UsersService service = new UsersService();

    public static class Unauthorized {
        String message = "musisz się zalogować";

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("********ServletFilter is init***********");
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse =(HttpServletResponse) response;
        final HttpSession session = httpServletRequest.getSession();

        User user = (User)session.getAttribute("user");

        if(user == null) {
            Unauthorized unauthorized = new Unauthorized();

            httpServletResponse.getWriter().println(new Gson().toJson(unauthorized));
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        filterChain.doFilter(request, response);

    }

//    /**
//     * Move user to menu.
//     * If access 'admin' move to admin menu.
//     * If access 'user' move to user menu.
//     */
//    private void moveToMenu(final HttpServletRequest req,
//                            final HttpServletResponse res,
//                            final String role)
//            throws ServletException, IOException {
//
//
//        if (role.equals("admin")) {
//            req.getRequestDispatcher("/ADMIN/admin_menu.jsp").forward(req, res);
//
//        } else if (role.equals("user")) {
//
//            req.getRequestDispatcher("/USER/user_menu.jsp").forward(req, res);
//
//        } else {
//            req.getRequestDispatcher("/login.jsp").forward(req, res);
//
//        }
//    }


    @Override
    public void destroy() {
        System.out.println("********SERVLET IS DESTROY***********");
    }

}
