package appDirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class airQualityData {
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
            url = new URL("https://api.waqi.info/feed/krak%C3%B3w/?token=8ff8681a5fa4a3e59c14b179ca224987ecd348a3");
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
    public static String aqi(){
        int i = 0;
        while(!currentDataTab[i].contains("aqi")){
            i++;
        }
        String[] data = currentDataTab[i].split(":");

        return data[2];
    }
    //mozna dopisac wiecej metod, ale ja nawet nie wiem co te rzeczy oznaczają xD
}
