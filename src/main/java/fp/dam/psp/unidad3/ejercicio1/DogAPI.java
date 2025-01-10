package fp.dam.psp.unidad3.ejercicio1;

/*
 * Programa que reciba a través de un parámetro de línea de comando una URL de una página web
 * y descargue todas las imágenes referenciadas en el parámetro src de cada etiqueta <img> de
 * dicha página.
 */

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.stream.Collectors;

public class DogAPI {

    public static void main(String[] args) throws URISyntaxException, IOException {
        URL url = new URI("https://dog-api.kinduff.com/api/facts?number=10").toURL();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            con.getInputStream()));
            Gson gson = new Gson();
            RespuestaJSON rjson = gson.fromJson(
                    in.lines().collect(Collectors.joining()),
                    RespuestaJSON.class
            );
            con.disconnect();
            for(String fact: rjson.facts)
                System.out.println(fact);
        }
        else
            System.out.println(responseCode);
    }

}
