package api.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;
/**
 *
 * @author Albertino Augusto */

public class Security 
{
    
    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    SecretKey key;

    public Security()
    {
        try 
        {
            myEncryptionKey = "ThisIsSpartaThisIsSparta";
            myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
            arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
            ks = new DESedeKeySpec(arrayBytes);
            skf = SecretKeyFactory.getInstance(myEncryptionScheme);
            cipher = Cipher.getInstance(myEncryptionScheme);
            key = skf.generateSecret(ks);
        } 
        catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException ex) 
        {
            System.out.println("Error: " + ex);
        }
    }


    public String encrypt(String unencryptedString) 
    {
        String encryptedString = null;
        try 
        {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } 
        catch (InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) 
        {

        }
        return encryptedString;
    }


    public String decrypt(String encryptedString) 
    {
        String decryptedText=null;
        try 
        {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString.getBytes());
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } 
        catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e)
        {

        }
        return decryptedText;
    }
    
    
//    public static void main(String[] args)
//    {
//        String senhaTeste = "123456";
//        
//        Security security = new Security();
//        
//        System.out.println("Senha: \n" + senhaTeste);
//        System.out.println("Senha Encriptada: \n" + security.encrypt(senhaTeste) + "\n");
//        
////        for (int i = 0; i < 20; i++)
////        {
////            System.out.println(security.encrypt(senhaTeste));
////        }
//    }
    
}
