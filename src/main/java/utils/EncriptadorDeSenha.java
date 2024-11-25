package utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncriptadorDeSenha {


    public static String encode(String password) {
        String passwordEncrypted = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(password.getBytes());
            StringBuilder builder = new StringBuilder();
            for(byte b: bytes) {
                builder.append(String.format("%02X", b));
            }
            passwordEncrypted = builder.toString();
        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao buscar algoritmo", e);
        }
        return passwordEncrypted;
    }


}

