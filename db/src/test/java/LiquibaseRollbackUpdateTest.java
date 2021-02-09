import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.Test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LiquibaseRollbackUpdateTest {

    @Test
    public void updateTestingRollback() throws LiquibaseException, SQLException {
        var props = new Properties();
        props.setProperty("user", "db");
        props.setProperty("password", "db");
        props.setProperty("database", "db");
        var connection = DriverManager.getConnection("jdbc:tc:postgresql:///?TC_REUSABLE=true", props);

        var database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        var liquibase = new Liquibase("db/changelog/db.changelog-master.yaml", new ClassLoaderResourceAccessor(), database);
        var labelExpression = new LabelExpression();
        var contexts = new Contexts("dev");
        liquibase.updateTestingRollback(contexts, labelExpression);

        var statuses = liquibase.getChangeSetStatuses(contexts, labelExpression);
        assertNotNull(statuses);
        assertTrue(statuses.size() > 1);
    }
}

