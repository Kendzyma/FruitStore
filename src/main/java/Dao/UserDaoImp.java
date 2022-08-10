package Dao;

import utils.dbConnect;
import Dao.Interface.UserDao;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImp implements UserDao {
    PreparedStatement preparedStatement;
    ResultSet resultSet;



    @Override
    public String UserLogin() {
        return null;
    }

    @Override
    public boolean addNewuser(User user) {
        try {
            String Query = "INSERT INTO Users" + " (firstName,lastName,emailAddress,password) VALUES" + "(?,?,?,?);";
            preparedStatement = dbConnect.getConnection().prepareStatement(Query);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmailAddress());
            preparedStatement.setString(4, user.getPassword());

                int resultSet1 = preparedStatement.executeUpdate();
                if (resultSet1 > 0) return true;
            } catch (Exception e) {
                e.getMessage();
            }
            return false;
        }

    @Override
    public boolean deleteUser() {
        return false;
    }

    @Override
    public ResultSet getUser(String emailAddress) {
        try {
            String Query = ("Select * from Users where emailAddress = '"+emailAddress+"';");
            preparedStatement = dbConnect.getConnection().prepareStatement(Query);
            resultSet = preparedStatement.executeQuery(Query);
        }
        catch (Exception e){
            e.getMessage();
        }
      return resultSet;
    }
}
