package appDirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class weatherData {

    private static String currentData;
    private static String [] currentDataTab;

    //public static void main(String[] args){
    //    loadCurrentData();
    //    System.out.println(currentData);
    //}

    public static void loadCurrentData() {

        StringBuilder result = new StringBuilder();
        URL url;

        try {
            url = new URL("http://api.openweathermap.org/data/2.5/find?q=krak%C3%B3w&units=metric&appid=a27871929b83012feaec4059182df4f1");
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(result);
        currentData = result.toString();
        currentDataTab = currentData.split(",");
    }

    public static String temperature(){
        int i = 0;
        while(!currentDataTab[i].contains("temp")){
            i++;
        }
        String[] data = currentDataTab[i].split(":");

        return data[2];
    }
    public static String feelsLike(){
        int i = 0;
        while(!currentDataTab[i].contains("feels")){
            i++;
        }
        String [] data = currentDataTab[i].split(":");

        return data[1];
    }
    public static String pressure(){
        int i = 0;
        while(!currentDataTab[i].contains("pressure")){
            i++;
        }
        String [] data = currentDataTab[i].split(":");

        return data[1];
    }
    public static String humidity(){
        int i = 0;
        while(!currentDataTab[i].contains("humidity")){
            i++;
        }
        String [] data = currentDataTab[i].split(":");

        return data[1].replace("}", "");
    }
    public static String windSpeed(){
        int i = 0;
        while(!currentDataTab[i].contains("wind")){
            i++;
        }
        String [] data = currentDataTab[i].split(":");

        return data[2];
    }
}
    