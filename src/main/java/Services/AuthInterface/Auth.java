package Services.AuthInterface;

import model.User;

import javax.servlet.http.HttpServletRequest;

public interface Auth {
     String Login(String email,String password);
    boolean SignUp(HttpServletRequest request);
}
