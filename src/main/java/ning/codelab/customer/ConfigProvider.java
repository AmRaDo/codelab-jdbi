package ning.codelab.customer;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.skife.config.ConfigurationObjectFactory;

import com.google.inject.Provider;

public class ConfigProvider implements Provider<DBConfig>{

	private static Logger logger= Logger.getLogger(ConfigProvider.class);
	
	@Override
	public DBConfig get() {
		PropertyConfigurator.configure("log4j.properties");
		Properties props = new Properties();
		try {
		FileReader reader = new FileReader("Database.properties");
		props.load(reader);
		}
		catch(IOException io) {
			logger.error("Caught I/O Exception as : "+io.getMessage());
		}
		ConfigurationObjectFactory c= new ConfigurationObjectFactory(props);
		return c.build(DBConfig.class);
	}

}
