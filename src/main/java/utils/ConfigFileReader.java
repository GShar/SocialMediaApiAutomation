package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileReader {

    private Properties props;
    public static final String CONFIG_PROPERTIES_FILE = "config.properties";
    public ConfigFileReader() {
        props = new Properties();
    }

    private Properties readConfigFile() {
        InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES_FILE);
            props.load(inputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return props;
    }
    public String getValuefromKey(String key){
        return readConfigFile().getProperty(key);
    }
    public String getCommentsUri(){
        return readConfigFile().getProperty("commentsUri");
    }
    public String getPostsUri(){
        return readConfigFile().getProperty("postsUri");
    }
    public String getJsonContentType(){
        return readConfigFile().getProperty("content-type");
    }
    public String getUsersUri(){
        return readConfigFile().getProperty("usersUri");
    }


}
