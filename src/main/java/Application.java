import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import Dal.Business.Entities.DbContext;
import Entities.Generic.Services.Configuration.ConfigurationManager;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		

        ConfigurationManager config = ConfigurationManager.getInstance();
        String connectionString = config.getProperty("db.connectionString");
        String databaseName = config.getProperty("db.databaseName");
        DbContext dbContext = DbContext.getInstance(connectionString, databaseName);
        System.out.println("Connected to MongoDB: " + databaseName);
        dbContext.close();

        SpringApplication.run(Application.class, args);
	}

}
