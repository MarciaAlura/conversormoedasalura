import br.com.alura.conversormoedas.modelos.Moeda;
import br.com.alura.conversormoedas.modelos.MoedaExterno;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversor {
    public static void main(String[] args) {


        String moedaOrigem ="EUR";
        String moedaDestino ="BRL";
        String chaveAPI = "ad0081a6284a0ee04df4e55f";

        String site = "https://v6.exchangerate-api.com/v6/"+chaveAPI+"/pair/"+moedaOrigem+"/"+moedaDestino;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(site)).build();

        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(response.body());

        String json = response.body();

        Gson gson = new Gson();

        MoedaExterno moedaExterno = gson.fromJson(json, MoedaExterno.class);
        System.out.println(moedaExterno);

    }
}