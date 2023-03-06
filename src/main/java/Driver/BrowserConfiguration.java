package Driver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class BrowserConfiguration {

    private static final Logger logger = LogManager.getLogger(BrowserConfiguration.class);

    private static Map<String, Object> config;
    private static final String filePath = "resources\\browser_config.json";

    public static Map<String, Object> getConfig() {
        if (config == null){
            try {
                config = new ObjectMapper().readValue(new File(filePath), new TypeReference<>() {
                });

                logger.info("Browser config init from " + filePath);

            }catch (IOException e){
                logger.error("Browser config failed init with " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return config;
    }

}
