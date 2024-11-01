import br.com.alura.conversormoedas.modelos.MoedaExterno;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Conversor {
    public static void main(String[] args) {

        List<List<String>> moedasLista = new ArrayList<>();
        List<String> moeda01 = new ArrayList<>();
        List<String> moeda02 = new ArrayList<>();
        List<String> moeda03 = new ArrayList<>();
        List<String> moeda04 = new ArrayList<>();
        List<String> moeda05 = new ArrayList<>();
        List<String> moeda06 = new ArrayList<>();

        moeda01.add("EUR");
        moeda01.add("BRL");
        moedasLista.add(moeda01);

        moeda02.add("BRL");
        moeda02.add("EUR");
        moedasLista.add(moeda02);

        moeda03.add("USD");
        moeda03.add("BRL");
        moedasLista.add(moeda03);

        moeda04.add("BRL");
        moeda04.add("USD");
        moedasLista.add(moeda04);

        moeda05.add("BRL");
        moeda05.add("USD");
        moedasLista.add(moeda05);

        moeda06.add("BRL");
        moeda06.add("USD");
        moedasLista.add(moeda06);

        System.out.println(moedasLista);
        System.out.println(moedasLista.get(0).get(0));
        System.out.println(moedasLista.get(0).get(1));


        String moedaOrigem = moedasLista.get(0).get(0);
        String moedaDestino = moedasLista.get(0).get(1);
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