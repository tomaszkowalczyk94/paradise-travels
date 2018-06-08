package paradiseTravels.servlets.servlet;

import paradiseTravels.model.Address;
import paradiseTravels.model.User;
import paradiseTravels.services.AddressService;
import paradiseTravels.services.user.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private UsersService service = new UsersService();
    private AddressService adr_service = new AddressService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("********Register Servlet doPost***********");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String zip = request.getParameter("zip");
        String city = request.getParameter("city");
        String region = request.getParameter("region");
        String country = request.getParameter("country");


        if(password.equals(confirmPassword)){

            Address userAddress = new Address(address,zip,city,region,country);
            adr_service.saveAddress(userAddress);

            User user = new User(firstName,lastName,userAddress, email, login, password,"user");
            service.saveUser(user);

            response.sendRedirect("/menu?userAdded");

        }else  {
            System.out.println("********Haslo nie zgadza sie***********");
            response.sendRedirect("/register?passwordError");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("********Register Servlet doGet***********");
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
