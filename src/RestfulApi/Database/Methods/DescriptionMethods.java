package RestfulApi.Database.Methods;

import RestfulApi.Database.Methods.Interface.MethodsInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DescriptionMethods implements MethodsInterface {
    @Override
    public void execute(Connection con, String sql) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.execute();
    }

    @Override
    public String GET(Connection con, String params) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT * FROM user WHERE ");
        String test = "";
        boolean flag = false;

        Map<String, String> queryParams = new HashMap<>();

        String queryStr = params;

        if (queryStr != null && !queryStr.isEmpty()) {
            String[] paramsr = queryStr.split("&");

            for (String param : paramsr) {
                String[] keyValue = param.split("=");

                if (keyValue.length == 2) {
                    queryParams.put(keyValue[0], keyValue[1]);
                } else {
                    queryParams.put(keyValue[0], "");
                }
            }
        }

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (flag){
                if (!entry.getKey().equals("id")){
                    sql.append(String.format(" AND %s = '%s'", entry.getKey(), entry.getValue()));
                }
                else{
                    sql.append(String.format(" AND %s = %s", entry.getKey(), entry.getValue()));
                }
            }else if (!entry.getKey().equals("id")){
                sql.append(String.format("%s = '%s'", entry.getKey(), entry.getValue()));
            }
            else {
                sql.append(String.format("%s = %s", entry.getKey(), entry.getValue()));
            }
            flag = true;
        }

        System.out.println(sql);
        PreparedStatement ps = con.prepareStatement(sql.toString());
        ResultSet resultSet = ps.executeQuery();

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        Map<String, Object> jsonObj = null;
        String s = null;
        while (resultSet.next()) {
            jsonObj = new LinkedHashMap<>();

            jsonObj.put("id", resultSet.getString("id"));
            jsonObj.put("login", resultSet.getString("login"));
            jsonObj.put("email", resultSet.getString("email"));
            jsonObj.put("password", resultSet.getString("password"));
            jsonObj.put("firstName", resultSet.getString("firstName"));
            jsonObj.put("secondName", resultSet.getString("secondName"));

            s = gson.toJson(jsonObj);

        }
        return s;
    }

}
