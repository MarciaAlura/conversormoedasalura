import br.com.alura.conversormoedas.modelos.MoedaExterno;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Conversor {
    public static void main(String[] args) {

        /*Montagem da Lista de moedas de--> para */
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

        moeda05.add("EUR");
        moeda05.add("USD");
        moedasLista.add(moeda05);

        moeda06.add("USD");
        moeda06.add("EUR");
        moedasLista.add(moeda06);

        /* Montar o menu de pesquisa de conversão de moedas*/
        Scanner leitura = new Scanner(System.in);
        System.out.println("**********************************************************************");
        System.out.println("            Seguem abaixo nossas moedas para conversão");
        System.out.println("        Digite o número correspondente a aconversão desejada");
        System.out.println("**********************************************************************");
        System.out.println("                                                                       ");
        System.out.println("                        [1] ---> EUR (Euro) ==== BRL (Real)");
        System.out.println("                        [2] ---> BRL (Real) ==== EUR (Euro)");
        System.out.println("                        [3] ---> USD (Dolar)==== BRL (Real)");
        System.out.println("                        [4] ---> BRL (Real) ==== USD (Dolar)");
        System.out.println("                        [5] ---> EUR (Euro) ==== USD (Dolar)");
        System.out.println("                        [6] ---> USD (Dolar)==== EUR (Euro)");
        System.out.println("                                                                       ");
        System.out.println("                        [7] >>>>>>>>>>>>>[SAIR]<<<<<<<<<<<<");
        System.out.println("                                                                       ");
        System.out.println("**********************************************************************");

        int menu = 1;

        while (menu != 7) {

            System.out.println("Digite o número correspondente a aconversão desejada:");
            menu = leitura.nextInt();


            if ((menu >= 1) && (menu <= 6)) {
                String moedaOrigem = moedasLista.get(menu-1).get(0);
                String moedaDestino = moedasLista.get(menu-1).get(1);
                String chaveAPI = "ad0081a6284a0ee04df4e55f";

                String site = "https://v6.exchangerate-api.com/v6/" + chaveAPI + "/pair/" + moedaOrigem + "/" + moedaDestino;

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(site)).build();

                HttpResponse<String> response;

                try {
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }

                String json = response.body();

                Gson gson = new Gson();

                MoedaExterno moedaExterno = gson.fromJson(json, MoedaExterno.class);
                System.out.println(moedaExterno);

            } else if (menu <= 0 || menu >= 8) {
                System.out.println("Numero incorreto: por favor, digite um numero que estaja no menu acima:");
                menu = 1;
            } else {
                System.out.println("**************************************");
                System.out.println("Obrigada por utilizar nossos serviços!");
                System.out.println("***************************************");

            }
        }
    }
}