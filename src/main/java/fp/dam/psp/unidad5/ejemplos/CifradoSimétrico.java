package fp.dam.psp.unidad5.ejemplos;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;

public class CifradoSimétrico {

    public static void main(String[] args) throws Exception {
        /* crear de una clave secreta AES de 256 bits */
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(256);
        SecretKey key = kg.generateKey();

        String cifrado = cifrarAES("Hola Mundo".getBytes(), key);
        System.out.println("Texto cifrado: " + cifrado);
        System.out.println("Texto original: " + descifrarAES(cifrado, key));
    }

    static String cifrarAES(byte[] datos, SecretKey key)
            throws GeneralSecurityException {
        byte [] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        Cipher c = Cipher.getInstance("AES/GCM/NoPadding");
        c.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(128, iv));
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(c.doFinal(datos)) +
                "#" + encoder.encodeToString(iv);
    }

    static String descifrarAES(String datos, SecretKey key)
            throws GeneralSecurityException {
        Base64.Decoder decoder = Base64.getDecoder();
        String [] aux = datos.split("#");
        Cipher c = Cipher.getInstance("AES/GCM/NoPadding");
        c.init(Cipher.DECRYPT_MODE, key,
                new GCMParameterSpec(128, decoder.decode(aux[1])));
        return new String(c.doFinal(decoder.decode(aux[0])));
    }

}
