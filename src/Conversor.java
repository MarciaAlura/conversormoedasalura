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
import java.util.regex.Pattern;

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

            try{
                menu = leitura.nextInt();
                String entrada = String.valueOf(menu);
                if (!Pattern.matches("\\d+", entrada)) {
                    System.out.println("Entrada inválida!!!!");
                    menu = 7;
                }
            } catch (Exception e) {
                menu = 7;
                System.out.println("    ");
                System.out.println("*** Caracter inválido. Infelizmente teremos que encerrar a aplicação. ****");
                System.out.println("    ");

            }


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

                /*Digitar a qtde de moeda para conversão*/
                System.out.println("Qual a quantidade para conversão de "+moedaExterno.base_code()+
                                " para "+moedaExterno.target_code()+"?");

                try {
                    double valorMoedaConversao = leitura.nextDouble();
                    String entradaValor = String.valueOf(valorMoedaConversao);

                    double valor = (valorMoedaConversao * moedaExterno.conversion_rate());

                    System.out.println("Valor de conversão: ");
                    System.out.println("Para cada " + moedaExterno.base_code() + " " + valorMoedaConversao +
                            " \nem " + moedaExterno.target_code() +
                            " valor de conversão: " + moedaExterno.target_code() + " " +
                            String.format("%.2f", valor));
                } catch (Exception e){
                    menu = 7;
                    System.out.println("\n*** Quantidade de moedas inválida!! ****");
                    System.out.println("\n*** Infelizmente teremos que encerrar a aplicação. ****");
                }

            } else if (menu <= 0 || menu >= 8) {
                System.out.println("Numero incorreto: por favor, digite um numero que esteja no menu acima:");
                menu = 1;
            } else {
                System.out.println("**************************************");
                System.out.println("Obrigada por utilizar nossos serviços!");
                System.out.println("***************************************");

            }
        }
    }
}