package Services;

import Dao.Interface.UserDao;
import Dao.UserDaoImp;
import Services.AuthInterface.Auth;
import model.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;

public class AuthService implements Auth {
    private Connection connection;
    UserDao dao = new UserDaoImp();

    public AuthService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String Login(String email,String password){
        String Role = "Invalid";
       ResultSet resultSet = dao.getUser(email);
       try {
           if(resultSet.next()){
                String emailAddress = resultSet.getString("emailAddress");
                String pass = resultSet.getString("password");
                String role = resultSet.getString("userRole");
              //  if(role.equals("admin"))
                if((email.equalsIgnoreCase(emailAddress)) && pass.equalsIgnoreCase(password))  Role =role;
            }

       }
       catch (Exception e){
           e.getMessage();
       }
       return Role;
    }
    @Override
    public boolean SignUp(HttpServletRequest request){
        String firstname = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email= request.getParameter("email");
        String password = request.getParameter("password");
        User user1 = new User();
        try {
            ResultSet res = dao.getUser(email);
            if(res.next()){
             String result = res.getString("emailAddress");
                if(result.equals(email)){
                    return false;
                }
            }
            user1.setFirstName(firstname);
            user1.setLastName(lastName);
            user1.setEmailAddress(email);
            user1.setPassword(password);

          if(dao.addNewuser(user1)) return true;
        }
        catch (Exception e){
            e.getMessage();
        }
return false;

    }
}
