package dao;

import java.sql.Connection;
import java.sql.SQLException;

// DAO -> Data Access Object
public class BaseDAO {


    public Connection con() throws SQLException {
        return ConexaoBD.getInstance().getConnection();
    }

}
