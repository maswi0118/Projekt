package appDirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class loadWeatherData {

    public static void printWeather() {

        StringBuilder result = new StringBuilder();
        URL url;

        {
            try {
                url = new URL("http://api.openweathermap.org/data/2.5/find?q=krak%C3%B3w&units=metric&mode=xml&appid=a27871929b83012feaec4059182df4f1");
                URLConnection connection = url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
                System.out.println(result);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
    