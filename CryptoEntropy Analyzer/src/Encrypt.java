import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Encrypt {

    // Definišem algoritam koji koristim
    private static final String ALGORITHM = "AES";

    public static void encryptFile(String filePath, String userKey) throws Exception {
        // 1. Priprema ključa
        // AES zahteva ključ od tačno 16 karaktera (128 bita) za osnovnu verziju
        byte[] keyBytes = fixKey(userKey);
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, ALGORITHM);

        // 2. Inicijalizacija Cipher objekta
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // 3. Čitanje originalnog fajla
        byte[] fileContent = Files.readAllBytes(Paths.get(filePath));

        // 4. Pretvaranje podataka u šifrat
        byte[] encryptedContent = cipher.doFinal(fileContent);

        // 5. Čuvanje šifrovanog fajla sa novom ekstenzijom
        Files.write(Paths.get(filePath + ".locked"), encryptedContent);

        System.out.println("✅Fajl uspešno šifrovan: " + filePath + ".locked");
    }

    // Pomoćna metoda koja osigurava da ključ uvek ima 16 bajtova
    private static byte[] fixKey(String key) {
        byte[] keyBytes = new byte[16];
        byte[] originalBytes = key.getBytes();
        int len = Math.min(originalBytes.length, 16);
        System.arraycopy(originalBytes, 0, keyBytes, 0, len);
        return keyBytes;
    }
}
