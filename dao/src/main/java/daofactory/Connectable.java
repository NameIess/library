package daofactory;

import dao.GenericDao;
import model.Identified;

import java.sql.Connection;

public interface Connectable {

    Connection getConnection();

    <T extends Identified> GenericDao<T> getDao(Class<T> dtoClass);

    interface DaoBuilder {
        GenericDao create(Connection connection);
    }
}