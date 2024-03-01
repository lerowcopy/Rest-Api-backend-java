package RestfulApi.Database.Methods.Interface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public interface MethodsInterface {
    void execute (Connection con, String sql) throws SQLException;

    String GET (Connection con, Map<String, String> queryParams) throws SQLException;
    String POST(Connection con, Map<String, String> queryParams) throws SQLException;
}
