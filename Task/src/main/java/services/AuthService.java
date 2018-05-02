package services;



import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lv.ctco.zephyr.Config;
import lv.ctco.zephyr.enums.ConfigProperty;


public class AuthService {

    private static Config config;

    public AuthService(Config config) {
        this.config = config;
    }

    public static String getAuth() {
        return Base64.encode((config.getValue(ConfigProperty.USERNAME) + ":" + config.getValue(ConfigProperty.PASSWORD)).getBytes());
    }

}