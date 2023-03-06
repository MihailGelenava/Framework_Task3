package model;

import BasePageObjects.BaseForm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UsersList {

    private static final Logger logger = LogManager.getLogger(BaseForm.class.getName());

    private static final String filePath = "resources\\users.json";

    private static List<User> users;

    public List<User> getUsers() {
        if (users == null){
            ObjectMapper mapper = new ObjectMapper();
            try {
                users = mapper.readValue(new File(filePath), new TypeReference<List<User>>(){});

                logger.info("List of users init from " + filePath);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return users;
        }
        return users;
    }
}
