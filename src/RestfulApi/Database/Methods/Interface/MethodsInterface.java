package RestfulApi.Database.Methods.Interface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface MethodsInterface {
    void execute (Connection con, String sql) throws SQLException;
    String GET (Connection con, Map<String, String> queryParams);
    List<String> GETUsers (Connection con, String name) throws SQLException;
    String POST(Connection con, Map<String, String> queryParams, String path)throws SQLException;
    String PUT(Connection con, int id, Map<String, String> queryParams)throws SQLException;
    String PATCH (Connection con, int id, Map<String, String> queryParams)throws SQLException;
    String DELETE (Connection con, int id) throws SQLException;
}
