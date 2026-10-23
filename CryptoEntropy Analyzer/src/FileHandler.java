import java.io.File;
import java.util.Scanner;

public class FileHandler {

    public static String odaberiPutanju() {
        Scanner s = new Scanner(System.in);
        System.out.println("Unesite punu putanju do fajla (npr. C:/Folder/fajl.txt):");
        String putanja = s.nextLine().trim(); // .trim() briše nevidljive razmake na početku/kraju

        File fajl = new File(putanja);

        if (fajl.exists() && fajl.isFile()) {
            System.out.println("🤖Sistem: Fajl uspešno pronađen!✅");
            return putanja;
        } else {
            System.out.println("🤖Sistem: GREŠKA! Fajl ne postoji na: " + fajl.getAbsolutePath() + "❎");
            return odaberiPutanju();
        }
    }
}
