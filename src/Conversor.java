import br.com.alura.conversormoedas.modelos.Moeda;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Conversor {
    public static void main(String[] args) {


        String moedaOrigem ="EUR";
        String moedaDestino ="BRL";
        String site = "https://v6.exchangerate-api.com/v6/YOUR-API-KEY/pair/"+moedaOrigem+"/"+moedaDestino;


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(site)).build();

        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response.body());

        String json = response.body();

        Gson gson = new Gson();
        Moeda moeda = gson.fromJson(json, Moeda.class);

        System.out.println(moeda);




    }
}