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
            url = new URL("https://api.waqi.info/feed/" + location + "/?token=8ff8681a5fa4a3e59c14b179ca224987ecd348a3");
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
            if(currentData != "{\"status\":\"error\",\"data\":\"Unknown station\"}") {
                data.put("location", location);
                data.put("aqi", currentDataTab[1].split(":")[2]);
//                data.put("co", currentDataTab[18].split(":")[3]);
//                data.put("no2", currentDataTab[21].split(":")[2]);
//                data.put("o3", currentDataTab[22].split(":")[2]);
//                data.put("pm10", currentDataTab[24].split(":")[2]);
//                data.put("pm25", currentDataTab[25].split(":")[2]);
//                data.put("so2", currentDataTab[26].split(":")[2]);
            }
            else{
                System.out.println("zle miasto mordo");
            }

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