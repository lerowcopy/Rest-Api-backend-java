package API.Database;

import API.Database.Methods.DescriptionMethods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database extends DescriptionMethods {
    public static Connection con = null;

    public Database() throws SQLException {
        if (con == null){
            con = DriverManager.getConnection("jdbc:sqlite:users.db");
            setUpDatabase();
        }
    }

    private void setUpDatabase () throws SQLException {
        String sql = """
                create table if not exists user (
                    id integer primary key,
                    login text unique,
                    email text unique,
                    password text,
                    firstName text,
                    secondName text
                )
                """;
        execute(con, sql);
    }


}
