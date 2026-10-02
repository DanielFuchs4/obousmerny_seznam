import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        MyDoublyLinkedList playlist = new MyDoublyLinkedList();

        while (true) {
            System.out.println("\n================ MENU ==================");
            System.out.println("a. Přidej song na konec seznamu");
            System.out.println("b. Vypiš názvy všech písní v tvém playlistu");
            System.out.println("c. Přidej song na začátek playlistu");
            System.out.println("d. Přehraj první song playlistu");
            System.out.println("e. Přehraj píseň na určité pozici");
            System.out.println("f. Přepni a přehraj na další song");
            System.out.println("g. Přepni a přehraj na předchozí song");
            System.out.println("h. Odeber první song");
            System.out.println("i. Odeber píseň z určité pozice");
            System.out.println("j. Přidej píseň na určitou pozici");
            System.out.println("k. Vymaž píseň s určitým názvem");
            System.out.println("--- BONUSY ---");
            System.out.println("l. Zahraj náhodnou píseň");
            System.out.println("m. Vypiš název nejhranější písně");
            System.out.println("n. Seřaď playlist");
            System.out.println("o. Exportuj seznam písní do souboru");
            System.out.println("p. Vyhledávání podle části názvu písně");
            System.out.println("0. Konec");
            System.out.print("Vaše volba: ");

            String volba = sc.nextLine().trim().toLowerCase();

            switch (volba) {
                case "a": case "1":
                    playlist.add(nactisong());
                    break;
                case "b": case "2":
                    playlist.printAll();
                    break;
                case "c": case "3":
                    playlist.addFirst(nactisong());
                    break;
                case "d": case "4":
                    playlist.playFirst();
                    break;
                case "e": case "5":
                    System.out.print("Zadejte index: ");
                    playlist.playOnIndex(nacitcislo());
                    break;
                case "f": case "6":
                    playlist.playNext();
                    break;
                case "g": case "7":
                    playlist.playPrevious();
                    break;
                case "h": case "8":
                    playlist.removeFirst();
                    break;
                case "i": case "9":
                    System.out.print("Zadejte index k odebrání: ");
                    playlist.removeOnIndex(nacitcislo());
                    break;
                case "j": case "10":
                    Song s = nactisong();
                    System.out.print("Zadejte index pro vložení: ");
                    playlist.addOnIndex(s, nacitcislo());
                    break;
                case "k": case "11":
                    System.out.print("Zadejte název písně k vymazání: ");
                    playlist.removeByName(sc.nextLine());
                    break;
                case "l": case "12":
                    playlist.playRandom();
                    break;
                case "m": case "13":
                    playlist.printMostPlayed();
                    break;
                case "n": case "14":
                    System.out.print("Podle čeho řadit? (1 - Název, 2 - Interpret, 3 - Stopáž): ");
                    playlist.sort(nacitcislo());
                    break;
                case "o": case "15":
                    System.out.print("Zadejte název souboru (např. playlist.csv / playlist.json / playlist.xml / playlist.txt): ");
                    String filename = sc.nextLine();
                    System.out.print("Zadejte formát (txt, csv, xml, json): ");
                    String format = sc.nextLine();
                    playlist.exportToFile(filename, format);
                    break;
                case "p": case "16":
                    System.out.print("Zadejte část názvu písně: ");
                    playlist.searchByName(sc.nextLine());
                    break;
                case "0":
                    System.out.println("Ukončuji aplikaci. Na shledanou!");
                    return;
                default:
                    System.out.println("Neplatná volba. Zkuste to znovu.");
            }
        }
    }

    private static Song nactisong() {
        System.out.print("Název písničky: ");
        String nazev = sc.nextLine();
        System.out.print("Jméno interpreta: ");
        String interpret = sc.nextLine();
        System.out.print("Délka v sekundách: ");
        int delka = nacitcislo();
        return new Song(nazev, interpret, delka);
    }

    private static int nacitcislo() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Neplatný vstup, zadejte celé číslo: ");
            }
        }
    }
}
