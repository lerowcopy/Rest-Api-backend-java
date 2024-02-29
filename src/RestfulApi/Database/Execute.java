package RestfulApi.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Execute extends Database{
    Execute(String sql) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.execute();
    }
}
