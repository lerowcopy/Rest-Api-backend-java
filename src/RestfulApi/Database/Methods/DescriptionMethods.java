package RestfulApi.Database.Methods;

import RestfulApi.Database.Methods.Interface.MethodsInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import RestfulApi.Database.Response.Response;
import RestfulApi.Database.Response.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DescriptionMethods implements MethodsInterface {
    @Override
    public void execute(Connection con, String sql) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.execute();
    }

    @Override
    public String GET(Connection con, Map<String, String> queryParams) throws SQLException {
        StringBuilder sql = GetRequest(queryParams);

        PreparedStatement ps = con.prepareStatement(sql.toString());
        ResultSet resultSet = ps.executeQuery();

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        List<User> users = new ArrayList<>();
        Response response;

        if (resultSet.isClosed()) {
            return "BR";
        } else {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String login = resultSet.getString("login");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("firstName");
                String secondName = resultSet.getString("secondName");
                User user = new User(id, login, email, password, firstName, secondName);

                users.add(user);
            }
            response = new Response(users, "ok", "success");
        }
        return gson.toJson(response);
    }

    private StringBuilder GetRequest(Map<String, String> queryParams) {
        StringBuilder sql = new StringBuilder("SELECT * FROM user WHERE ");
        boolean flag = false;


        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (flag) {
                if (!entry.getKey().equals("id")) {
                    sql.append(String.format(" AND %s = '%s'", entry.getKey(), entry.getValue()));
                } else {
                    sql.append(String.format(" AND %s = %s", entry.getKey(), entry.getValue()));
                }
            } else if (!entry.getKey().equals("id")) {
                sql.append(String.format("%s = '%s'", entry.getKey(), entry.getValue()));
            } else {
                sql.append(String.format("%s = %s", entry.getKey(), entry.getValue()));
            }
            flag = true;
        }
        return sql;
    }

}
