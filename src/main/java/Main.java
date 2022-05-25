import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        try {
           System.out.println("Podaj miasto: ");
            Scanner input = new Scanner(System.in);
            String miasto = input.nextLine();

            URL pogoda = new URL("https://api.openweathermap.org/data/2.5/weather?q="+miasto+"&appid=2683291865313ab6a35a59db7413056a");

            HttpsURLConnection pol = (HttpsURLConnection) pogoda.openConnection();
            pol.setRequestMethod("GET");
            pol.connect();

            int test = pol.getResponseCode();

            if(test != 200){
                throw new RuntimeException("Kod testu: "+ test);
            }
            else
            {
                StringBuilder info = new StringBuilder();
                Scanner scanner = new Scanner(pogoda.openStream());

                while (scanner.hasNext()){
                    info.append(scanner.nextLine());
                }
                scanner.close();

                JSONParser parse = new JSONParser();
                Object error1 = parse.parse(String.valueOf(info));
                JSONArray tabela = new JSONArray();
                tabela.add(error1);
                JSONObject dane = (JSONObject) tabela.get(0); //dodanie do obiektu wartości z 1 tabeli

                JSONArray tabela2 = new JSONArray();
                tabela2.add(dane.get("main"));
                JSONObject dane2 = (JSONObject) tabela2.get(0); // stworzenie 2 obiektu i dodanie do niego wartości z main
                System.out.println("Temperatura w kelvin: "+dane2.get("temp"));

                System.out.println(miasto);
            }

        } catch (Exception e){
            e.printStackTrace();
        }





    }

}
