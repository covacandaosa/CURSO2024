package fp.dam.psp.unidad3.ejercicio1;

/*
 * Programa que reciba a través de un parámetro de línea de comando una URL de una página web
 * y descargue todas las imágenes referenciadas en el parámetro src de cada etiqueta <img> de
 * dicha página.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {
        URL url = new URI(args[0]).toURL();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            con.getInputStream()));
            String linea;
            while ((linea = in.readLine()) != null) {
                System.out.println(linea);
            }
            con.disconnect();
        }
        else
            System.out.println(responseCode);
    }

}
