package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileReader {

    private Properties props;
    private String propertyFileName;
    public ConfigFileReader(String _propertyFileName) {
        propertyFileName = _propertyFileName;
        props = new Properties();
    }

    private Properties readConfigFile() {
        InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
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


}
