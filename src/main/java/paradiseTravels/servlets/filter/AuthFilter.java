package paradiseTravels.servlets.filter;

import paradiseTravels.model.Users;
import paradiseTravels.services.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

/**
 * Acidification filter.
 */
public class AuthFilter implements Filter {

    final UsersService service = new UsersService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("********ServletFilter is init***********");
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

         String login = req.getParameter("login");
         String password = req.getParameter("password");


//        final Logger logger = Logger.getLogger("MyBestLogger");
//        logger.log(Level.INFO, "Wiadomosc dla konsoli. Dziala w Tomcad");

        String ipAddress = request.getRemoteAddr();
        System.out.println("=================================================");
        System.out.println("Request...");
        System.out.println("IP: "+ipAddress);
        System.out.println("Login: "+req.getSession().getAttribute("login"));

        final HttpSession session = req.getSession();


        //Logged user.
        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {//jezeli nie ma ni sesji, ni atrybutow(jest zalogowany)

            final String role = (String) session.getAttribute("role");

            moveToMenu(req, res, role);


        } else if (service.userIsExist(login)) {// jezeli ok - logujemy
            final Users user = service.findByLoginPassword(login,password);
            final String role,firstName;

            if(nonNull(user)) {
                role = user.getRole();
                firstName = user.getFirstName();
            }
            else {moveToMenu(req, res, "UNKNOWN");return;}

            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);
            req.getSession().setAttribute("firstName",firstName);

            moveToMenu(req, res, role);

        } else {

            moveToMenu(req, res, "UNKNOWN");
        }
    }

    /**
     * Move user to menu.
     * If access 'admin' move to admin menu.
     * If access 'user' move to user menu.
     */
    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final String role)
            throws ServletException, IOException {


        if (role.equals("admin")) {
            req.getRequestDispatcher("/ADMIN/admin_menu.jsp").forward(req, res);

        } else if (role.equals("user")) {

            req.getRequestDispatcher("/USER/user_menu.jsp").forward(req, res);

        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, res);

        }
    }


    @Override
    public void destroy() {
        System.out.println("********SERVLET IS DESTROY***********");
    }

}
