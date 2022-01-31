package software.carter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

// Singleton
public class Config
{
    private static Config config;
    private static Properties properties = new Properties();

    static
    {
        try
        {
            config = new Config();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static Config getConfig()
    {
        return config;
    }

    public Config() throws FileNotFoundException
    {
        //--------------------------------------------------
        // Load-or-create configuration file.
        try
        {
            File file = new File("owrt.config");

            if ( file.createNewFile() )
                System.out.println("[Config] Configuration file did not exist, writing default to path owrt.config");
            else
                System.out.println("[Config] Configuration file exists at path owrt.config");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

       try
       {
           FileInputStream is =  new FileInputStream("owrt.config");
           properties.load(is);
           is.close();
       }
       catch (IOException ex)
       {
           ex.printStackTrace();
       }
    }

    public String read(String key)
    {
        return properties.getProperty(key);
    }
}