package sample.methods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.HashMap;

public class airQualityData {

    private HashMap<String, String> data = new HashMap<String, String>();

    public airQualityData(String location) {

        StringBuilder result = new StringBuilder();
        URL url;


        try {
            url = new URL("https://api.weatherbit.io/v2.0/current/airquality?city=" + location + "&key=1e313668c6ce45c09f3658f8d7747d23");
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();

            System.out.println(result);
            String currentData;
            String[] currentDataTab;
            currentData = result.toString().replaceAll("}", "");
            currentDataTab = currentData.split(",");
            data.put("location", location);
            data.put("aqi", currentDataTab[1].split(":")[1]);
            data.put("co", currentDataTab[3].split(":")[1] + " ug/m3");
            data.put("no2", currentDataTab[9].split(":")[1] + " ug/m3");
            data.put("o3", currentDataTab[4].split(":")[1] + " ug/m3");
            data.put("pm10", currentDataTab[2].split(":")[1] + " ug/m3");
            data.put("pm25", currentDataTab[10].split(":")[1] + " ug/m3");
            data.put("so2", currentDataTab[6].split(":")[1] + " ug/m3");

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (UnknownHostException e) {
            System.out.println("brak neta albo api nie odpowiada");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("index out of bound");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getData() {
        return data;
    }
}