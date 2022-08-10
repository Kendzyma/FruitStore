package Dao.Interface;

import model.User;

import java.sql.ResultSet;
import java.util.List;

public interface UserDao {
    String UserLogin();
    boolean addNewuser(User user);
    boolean deleteUser();
    public ResultSet getUser(String emailAddress);

}
