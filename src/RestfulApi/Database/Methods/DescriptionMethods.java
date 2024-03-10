package RestfulApi.Database.Methods;

import RestfulApi.Database.Database;
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
    public String GET(Connection con, Map<String, String> queryParams) {
        StringBuilder sql = GetRequest(queryParams);

        try {
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
        } catch (SQLException e) {
            return "BR";
        }
    }

    @Override
    public String POST(Connection con, Map<String, String> queryParams) throws SQLException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        List<User> users = new ArrayList<>();
        Response response;

        StringBuilder sql = PostRequest(queryParams);
        PreparedStatement ps = con.prepareStatement(sql.toString());

        try {
            ps.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        int idP = GetUserId(queryParams);
        sql = new StringBuilder(String.format("SELECT * FROM User WHERE id = %d", idP));

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

    @Override
    public String PUT(Connection con, int id, Map<String, String> queryParams) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Response response;

        StringBuilder sql;
        sql = PutRequest(id, queryParams);

        if (sql.toString().equals("BR")) {
            return "BR";
        }

        try {
            execute(con, sql.toString());

        } catch (SQLException e) {
            return "BR";
        }

        response = new Response(new ArrayList<>(), "User updated", "success");
        return gson.toJson(response);
    }

    @Override
    public String PATCH(Connection con, int id, Map<String, String> queryParams) throws SQLException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Response response;
        List<User> users = new ArrayList<>();

        StringBuilder sql;
        sql = PatchRequest(id, queryParams);

        try {
            execute(con, sql.toString());

        } catch (SQLException e) {
            return "BR";
        }

        sql = new StringBuilder(String.format("SELECT * FROM User WHERE id = %d", id));

        PreparedStatement ps = con.prepareStatement(sql.toString());
        ResultSet resultSet = ps.executeQuery();

        if (resultSet.isClosed()) {
            return "BR";
        } else {
            while (resultSet.next()) {
                String idU = resultSet.getString("id");
                String login = resultSet.getString("login");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("firstName");
                String secondName = resultSet.getString("secondName");
                User user = new User(idU, login, email, password, firstName, secondName);

                users.add(user);
            }
            response = new Response(users, "ok", "success");
        }
        return gson.toJson(response);
    }

    @Override
    public String DELETE(Connection con, int id) throws SQLException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Response response;

        String sql = String.format("SELECT * FROM user WHERE id = %d", id);

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        if (resultSet.isClosed()) {
            return "BR";
        }

        sql = String.format("DELETE FROM user WHERE id = %d", id);
        execute(con, sql);

        response = new Response(new ArrayList<>(), "User deleted", "success");
        return gson.toJson(response);
    }

    private int GetUserId(Map<String, String> queryParams) throws SQLException {
        int id = -1;
        String sql = null;
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (entry.getKey().equals("email") && !entry.getValue().equals("null")) {
                sql = String.format("SELECT * FROM user WHERE email = '%s'", entry.getValue());
                break;
            }
            else if (entry.getKey().equals("login") && !entry.getValue().equals("null")){
                sql = String.format("SELECT * FROM user WHERE login = '%s'", entry.getValue());
                break;
            }
        }

        PreparedStatement ps = Database.con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        id = resultSet.getInt("id");
        return id;
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
                if (entry.getKey().equals("password")) {
                    sql.append(String.format("'%s'", hex(entry.getValue(), 10)));
                } else {
                    sql.append(String.format("'%s'", entry.getValue()));
                }
            } else {
                if (entry.getKey().equals("password")) {
                    sql.append(String.format(", '%s'", hex(entry.getValue(), 10)));
                } else {
                    sql.append(String.format(", '%s'", entry.getValue()));
                }
            }
            flag = false;
        }

        sql.append(")");
        return sql;
    }

    public StringBuilder PutRequest(int id, Map<String, String> queryParams) {
        StringBuilder sql = new StringBuilder("UPDATE user SET ");

        boolean flag = true;

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (entry.getValue().equals("null")) {
                return new StringBuilder("BR");
            }

            if (flag) {
                if (entry.getKey().equals("password")) {
                    sql.append(String.format("%s = '%s'", entry.getKey(), hex(entry.getValue(), 10)));
                } else {
                    sql.append(String.format("%s = '%s'", entry.getKey(), entry.getValue()));

                }
            } else {
                if (entry.getKey().equals("password")) {
                    sql.append(String.format(", %s = '%s'", entry.getKey(), hex(entry.getValue(), 10)));
                } else {
                    sql.append(String.format(", %s = '%s'", entry.getKey(), entry.getValue()));

                }
            }
            flag = false;
        }

        sql.append(String.format(" WHERE id = %d", id));

        return sql;
    }

    public StringBuilder PatchRequest(int id, Map<String, String> queryParams) {
        StringBuilder sql = new StringBuilder("UPDATE user SET ");

        boolean flag = true;

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (flag) {
                if (entry.getValue().equals("null")) {
                    continue;
                } else {
                    if (entry.getKey().equals("password")) {
                        sql.append(String.format("%s = '%s'", entry.getKey(), hex(entry.getValue(), 10)));
                    } else {
                        sql.append(String.format("%s = '%s'", entry.getKey(), entry.getValue()));
                    }
                }
            } else {
                if (entry.getValue().equals("null")) {
                    continue;
                } else {
                    if (entry.getKey().equals("password")) {
                        sql.append(String.format(", %s = '%s'", entry.getKey(), hex(entry.getValue(), 10)));
                    } else {
                        sql.append(String.format(", %s = '%s'", entry.getKey(), entry.getValue()));

                    }
                }
            }
            flag = false;
        }

        sql.append(String.format(" WHERE id = %d", id));

        return sql;
    }

    public static String hex(String str, int k) {

        byte[] messageDigest = new byte[0];
        str += "abrakadabra";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(str.getBytes());
            messageDigest = md.digest();

        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }
        BigInteger bigInt = new BigInteger(1, messageDigest);
        StringBuilder md5Hex = new StringBuilder(bigInt.toString(16));
        while (md5Hex.length() < 32) {
            md5Hex.insert(0, "0");
        }
        if (k == 0) return md5Hex.toString().toUpperCase();
        else return hex(md5Hex.toString(), k - 1);
    }
}
