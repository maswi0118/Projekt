package appDirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.HashMap;

public class weatherData {

    private HashMap<String, String> data = new HashMap<String, String>();

    public weatherData(String location) {

        StringBuilder result = new StringBuilder();
        URL url;


        try {
            url = new URL("http://api.openweathermap.org/data/2.5/find?q=" + location + "&units=metric&appid=a27871929b83012feaec4059182df4f1");
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            System.out.println(result);
            String currentData = result.toString().replaceAll("}", "").replaceAll("]", "").replaceAll("\"", "");
            String [] currentDataTab = currentData.split(",");

            data.put("location", location);
            data.put("temperature", currentDataTab[7].split(":")[2]);
            data.put("feelsLike", currentDataTab[8].split(":")[1]);
            data.put("pressure", currentDataTab[11].split(":")[1]);
            data.put("humidity", currentDataTab[12].split(":")[1]);
            data.put("windSpeed", currentDataTab[14].split(":")[2]);
            data.put("clouds", currentDataTab[22].split(":")[1]);
            data.put("icon", currentDataTab[23].split(":")[1]);

            if(currentDataTab[14].split(":")[1].matches("null")) {
                data.put("windSpeed", "no wind");
            }
            else{
                data.put("rain", currentDataTab[14].split(":")[2]);
            }
            if(currentDataTab[17].split(":")[1].matches("null")) {
                data.put("rain", "no rain precipitation");
            }
            else{
                data.put("rain", currentDataTab[17].split(":")[2]);
            }
            if(currentDataTab[18].split(":")[1].matches("null") ) {
                data.put("snow", "no snow precipitation");
            }
            else{
                data.put("snow", currentDataTab[18].split(":")[2]);
            }

        }
        catch(MalformedURLException e) {
            e.printStackTrace();
        }
        catch(UnknownHostException e) {
            System.out.println("brak neta albo api nie odpowiada");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("zla lokalizacja byniu");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getData() {
        return data;
    }

//    public String temperature(){
//        int i = 0;
//        while(!currentDataTab[i].contains("temp")){
//            i++;
//        }
//        String[] data = currentDataTab[i].split(":");
//
//        return data[2];
//    }
//    public String feelsLike(){
//        int i = 0;
//        while(!currentDataTab[i].contains("feels")){
//            i++;
//        }
//        String [] data = currentDataTab[i].split(":");
//
//        return data[1];
//    }
//    public String pressure(){
//        int i = 0;
//        while(!currentDataTab[i].contains("pressure")){
//            i++;
//        }
//        String [] data = currentDataTab[i].split(":");
//
//        return data[1];
//    }
//    public String humidity(){
//        int i = 0;
//        while(!currentDataTab[i].contains("humidity")){
//            i++;
//        }
//        String [] data = currentDataTab[i].split(":");
//
//        return data[1].replace("}", "");
//    }
//    public String windSpeed(){
//        int i = 0;
//        while(!currentDataTab[i].contains("wind")){
//            i++;
//        }
//        String [] data = currentDataTab[i].split(":");
//
//        return data[2];
//    }
}
    