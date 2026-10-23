import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {

            System.out.println("=== Detekcija enkripcije u fajl sistemu pomoću analize entropije ===");

            // 1. Poziv preko klase FileHandler
            String putanja = FileHandler.odaberiPutanju();

            // 2. Poziv preko klase EntropyAnalyzer
            double entropijaPre = EntropyAnalyzer.calculate(putanja);
            System.out.printf("📂Entropija originalnog fajla: %.4f\n", entropijaPre);

            // 3. ENKRIPCIJA
            System.out.println("🔒 Šifrovanje u toku...");
            System.out.print("🔑 Unesite userKey: ");
            Scanner s = new Scanner(System.in);
            String userKey = s.nextLine();

            // 4. Klasa Encrypt
            Encrypt.encryptFile(putanja, userKey);

            // 5. MERENJE POSLE
            String novaPutanja = putanja + ".locked";
            double entropijaPosle = EntropyAnalyzer.calculate(novaPutanja);
            System.out.printf("📂Entropija šifrovanog fajla: %.4f\n", entropijaPosle);

            // 6. ZAKLJUČAK
            System.out.println("------------------------------------------");
            if (entropijaPosle > 7.5) {
                System.out.println("🤖 Sistem: Detektovana visoka entropija! Podaci su uspešno kriptovani.");
            }

        } catch (Exception e) {
            System.out.println("Došlo je do greške: " + e.getMessage());
            e.printStackTrace();
        }
    }
}