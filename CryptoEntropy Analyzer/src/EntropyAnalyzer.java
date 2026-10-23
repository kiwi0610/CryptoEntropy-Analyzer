import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EntropyAnalyzer {

    public static double calculate(String putanja) throws IOException {
        // 1. Učitavamo fajl kao niz bajtova
        byte[] podaci = Files.readAllBytes(Paths.get(putanja));
        if (podaci.length == 0) return 0.0;

        // 2. Brojimo koliko se puta svaki od 256 mogućih bajtova pojavljuje
        int[] brojac = new int[256];
        for (byte b : podaci) {
            int index = b & 0xFF; // Pretvaramo bajt u broj od 0 do 255
            brojac[index]++;
        }

        // 3. Šenonova formula za entropiju
        double entropija = 0;
        for (int ponavljanja : brojac) {
            if (ponavljanja > 0) {
                double verovatnoca = (double) ponavljanja / podaci.length;
                // Formula: suma od -p * log2(p)
                entropija -= verovatnoca * (Math.log(verovatnoca) / Math.log(2));
            }
        }
        return entropija;
    }
}

