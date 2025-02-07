package fp.dam.psp.unidad5.ejemplos;

import java.security.*;
import java.util.Base64;

public class EjemploFirma {

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        keyGen.initialize(4096, random);
        KeyPair par = keyGen.generateKeyPair();
        PrivateKey privada = par.getPrivate();
        PublicKey publica = par.getPublic();
        String firma = sign("Hola Mundo".getBytes(), "SHA3-512", privada);
        System.out.println(verify("Hola Mundo".getBytes(), firma, publica));
    }

    static String sign(byte[] datos, String hashAlgorithm, PrivateKey key) throws GeneralSecurityException {
        String algorithm = hashAlgorithm + "with" + key.getAlgorithm();
        Signature signature = Signature.getInstance(algorithm);
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        signature.initSign(key, random);
        signature.update(datos);
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(signature.sign()) + "#" + algorithm;
    }

    static boolean verify(byte[] datos, String firma, PublicKey key) throws GeneralSecurityException {
        String [] aux = firma.split("#");
        Signature signature = Signature.getInstance(aux[1]);
        signature.initVerify(key);
        signature.update(datos);
        return signature.verify(Base64.getDecoder().decode(aux[0]));
    }

}
