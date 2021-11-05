package KarolSzymaniak.org.repository;

import KarolSzymaniak.org.Client;

import java.sql.*;

public class JDBCClientRepository implements ClientRepository {

    public  static  final  String USER = "postgres";
    public  static  final  String PASSWORD = "h@slosz1";
    //public  static  final  String JDBC_URL = "jdbc:postgresql://117.0.0.1:5432/test";
    public  static  final  String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";

    @Override
    public void saveClient(Client client) {

        String name = client.getName();
        String mail = client.getEmail();

        try(Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("insert into users(first_name, mail) values(?,?) ");
            statement.setString(1, name);
            statement.setString(2, mail);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client findByEmail(String email) {
        try(Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("select first_name, mail from users where mail = ?");
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                String name = resultSet.getString("First_name");
                String mail = resultSet.getString("mail");
                return new Client(name,mail,0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}