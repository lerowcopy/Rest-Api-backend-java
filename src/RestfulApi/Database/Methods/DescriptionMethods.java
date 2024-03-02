package RestfulApi.Database.Methods;

import RestfulApi.Database.Methods.Interface.MethodsInterface;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    @Override
    public String POST(Connection con, Map<String, String> queryParams) throws SQLException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        List<User> users = new ArrayList<>();
        Response response;

        StringBuilder sql = PostRequest(queryParams);

        PreparedStatement ps = con.prepareStatement(sql.toString());
        ps.execute();

        String emailUser = GetUserId(queryParams);

        sql = new StringBuilder(String.format("SELECT * FROM User WHERE email = '%s'", emailUser));

        ps = con.prepareStatement(sql.toString());
        ResultSet resultSet = ps.executeQuery();

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

    private String GetUserId(Map<String, String> queryParams) {
        String emailUser = "";
        for (Map.Entry<String, String> entry : queryParams.entrySet()){
            if (entry.getKey().equals("email")){
                emailUser = (entry.getValue());
            }
        }
        return emailUser;
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
            } else {
                if (!entry.getKey().equals("id")) {
                    sql.append(String.format("%s = '%s'", entry.getKey(), entry.getValue()));
                } else {
                    sql.append(String.format("%s = %s", entry.getKey(), entry.getValue()));
                }
            }

            flag = true;
        }
        return sql;
    }

    private StringBuilder PostRequest(Map<String, String> queryParams) {
        StringBuilder sql = new StringBuilder("INSERT INTO user (");
        boolean flag = true;

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (entry.getKey().equals("id")){

            }
            if (flag) {
                sql.append(String.format("%s", entry.getKey()));
            } else {
                sql.append(String.format(", %s", entry.getKey()));
            }
            flag = false;
        }

        sql.append(") VALUES (");

        flag = true;
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (flag) {
                if (entry.getKey().equals("password")){
                    sql.append(String.format("'%s'", hex(entry.getValue(), 10)));
                }else{
                    sql.append(String.format("'%s'", entry.getValue()));
                }
            } else {
                if (entry.getKey().equals("password")){
                    sql.append(String.format(", '%s'", hex(entry.getValue(), 10)));
                }else{
                    sql.append(String.format(", '%s'", entry.getValue()));
                }
            }
            flag = false;
        }

        sql.append(")");
        return sql;
    }

    public String hex(String str, int k){

        byte[] messageDigest = new byte[0];
        str += "abrakadabra";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(str.getBytes());
            messageDigest = md.digest();

        }catch (NoSuchAlgorithmException e){
            System.err.println(e.getMessage());
        }
        BigInteger bigInt = new BigInteger(1, messageDigest);
        StringBuilder md5Hex = new StringBuilder(bigInt.toString(16));
        while (md5Hex.length() < 32){
            md5Hex.insert(0, "0");
        }
        if (k == 0)return md5Hex.toString().toUpperCase();
        else return hex(md5Hex.toString(), k - 1);
    }


}
