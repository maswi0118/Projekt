package appDirectory;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.net.http.HttpClient;
import java.net.http.*;



public class foodData {

    private HashMap<String, String> data = new HashMap<String, String>();

    public foodData(String food) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("X-Api-Key", "kX4Rb5hj4Yxq+1H80TsVjg==eLg609uFSIg8oH2r")
                .uri(URI.create("https://api.calorieninjas.com/v1/nutrition?query=" + food.replace(" ", "%20")))
                .build();
        HttpResponse<String> response;

        {
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String currentData = response.body().replaceAll("}", "").replaceAll("]", "");
                System.out.println(currentData);
                String[] currentDataTab = currentData.split(",");
                data.put("food", food);
                data.put("sugar", currentDataTab[0].split(":")[2]);
                data.put("fiber", currentDataTab[1].split(":")[1]);
                data.put("weight", currentDataTab[2].split(":")[1]);
                data.put("sodium", currentDataTab[3].split(":")[1]);
                data.put("name", currentDataTab[4].split(":")[1]);
                data.put("potassium", currentDataTab[5].split(":")[1]);
                data.put("fatSaturated", currentDataTab[6].split(":")[1]);
                data.put("fatTotal", currentDataTab[7].split(":")[1]);
                data.put("calories", currentDataTab[8].split(":")[1]);
                data.put("cholesterol", currentDataTab[9].split(":")[1]);
                data.put("protein", currentDataTab[10].split(":")[1]);
                data.put("carbohydrates", currentDataTab[11].split(":")[1]);


            }
            catch (MalformedURLException e) {
                    e.printStackTrace();
            }
            catch (UnknownHostException e) {
                    System.out.println("brak neta albo api nie odpowiada");
            }
            catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("zla lokalizacja byniu");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public HashMap<String, String> getData() {
        return data;
    }
}