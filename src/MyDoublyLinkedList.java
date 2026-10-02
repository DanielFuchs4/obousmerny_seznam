import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MyDoublyLinkedList {
    private Node head;
    private Node tail;
    private Node currentlyPlaying;

    // a. Přidej song na konec seznamu
    public void add(Song song) {
        Node newNode = new Node(song);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
    }

    // b. Vypiš názvy všech písní v tvém playlistu
    public void printAll() {
        if (head == null) {
            System.out.println("Playlist je prázdný.");
            return;
        }
        Node current = head;
        int index = 0;
        while (current != null) {
            System.out.println(index + ". " + current.getData());
            current = current.getNext();
            index++;
        }
    }

    // c. Přidej song na začátek playlistu
    public void addFirst(Song song) {
        Node newNode = new Node(song);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
    }

    // d. Přehraj první song playlistu - vypiš název a stopáž
    public void playFirst() {
        if (head == null) {
            System.out.println("Playlist je prázdný.");
            return;
        }
        currentlyPlaying = head;
        currentlyPlaying.getData().incrementPocetPrehrani();
        System.out.println("Přehrávám první: " + currentlyPlaying.getData());
    }

    // e. Přehraj píseň na určité pozici - vypiš název a stopáž
    public void playOnIndex(int index) {
        Node node = getNodeAtIndex(index);
        if (node == null) {
            System.out.println("Neplatný index.");
            return;
        }
        currentlyPlaying = node;
        currentlyPlaying.getData().incrementPocetPrehrani();
        System.out.println("Přehrávám [" + index + "]: " + currentlyPlaying.getData());
    }

    // f. Přepni a přehraj na další song - vypiš název a stopáž
    public void playNext() {
        if (currentlyPlaying == null) {
            if (head != null) {
                playFirst();
            } else {
                System.out.println("Playlist je prázdný.");
            }
            return;
        }
        if (currentlyPlaying.getNext() != null) {
            currentlyPlaying = currentlyPlaying.getNext();
            currentlyPlaying.getData().incrementPocetPrehrani();
            System.out.println("Přehrávám další: " + currentlyPlaying.getData());
        } else {
            System.out.println("Jste na konci playlistu.");
        }
    }

    // g. Přepni a přehraj na předchozí song - vypiš název a stopáž
    public void playPrevious() {
        if (currentlyPlaying == null) {
            if (head != null) {
                playFirst();
            } else {
                System.out.println("Playlist je prázdný.");
            }
            return;
        }
        if (currentlyPlaying.getPrev() != null) {
            currentlyPlaying = currentlyPlaying.getPrev();
            currentlyPlaying.getData().incrementPocetPrehrani();
            System.out.println("Přehrávám předchozí: " + currentlyPlaying.getData());
        } else {
            System.out.println("Jste na začátku playlistu.");
        }
    }

    // h. Odeber první song
    public void removeFirst() {
        if (head == null) {
            System.out.println("Playlist je prázdný.");
            return;
        }
        if (currentlyPlaying == head) {
            currentlyPlaying = head.getNext();
        }
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrev(null);
        }
        System.out.println("První písnička byla odebrána.");
    }

    // i. Odeber píseň z určité pozice
    public void removeOnIndex(int index) {
        Node toRemove = getNodeAtIndex(index);
        if (toRemove == null) {
            System.out.println("Neplatný index.");
            return;
        }
        removeNode(toRemove);
        System.out.println("Písnička na pozici " + index + " byla odebrána.");
    }

    // j. Přidej píseň na určitou pozici
    public void addOnIndex(Song song, int index) {
        if (index <= 0) {
            addFirst(song);
            return;
        }
        Node current = getNodeAtIndex(index);
        if (current == null) {
            add(song);
            return;
        }
        Node newNode = new Node(song);
        Node prevNode = current.getPrev();

        newNode.setNext(current);
        newNode.setPrev(prevNode);
        current.setPrev(newNode);
        if (prevNode != null) {
            prevNode.setNext(newNode);
        } else {
            head = newNode;
        }
    }

    // k. Vymaž píseň s určitým názvem
    public void removeByName(String name) {
        Node current = head;
        boolean found = false;
        while (current != null) {
            Node next = current.getNext();
            if (current.getData().getNazev().equalsIgnoreCase(name)) {
                removeNode(current);
                found = true;
            }
            current = next;
        }
        if (found) {
            System.out.println("Písnička/písničky s názvem '" + name + "' byly vymazány.");
        } else {
            System.out.println("Písnička s názvem '" + name + "' nebyla nalezena.");
        }
    }

    // BONUS l. Zahraj náhodnou píseň
    public void playRandom() {
        int size = getSize();
        if (size == 0) {
            System.out.println("Playlist je prázdný.");
            return;
        }
        Random rand = new Random();
        playOnIndex(rand.nextInt(size));
    }

    // BONUS m. Vypiš název nejhranější písně
    public void printMostPlayed() {
        if (head == null) {
            System.out.println("Playlist je prázdný.");
            return;
        }
        Node current = head;
        Song maxSong = head.getData();
        while (current != null) {
            if (current.getData().getPocetPrehrani() > maxSong.getPocetPrehrani()) {
                maxSong = current.getData();
            }
            current = current.getNext();
        }
        if (maxSong.getPocetPrehrani() == 0) {
            System.out.println("Zatím nebyla přehrána žádná píseň.");
        } else {
            System.out.println("Nejhranější píseň: " + maxSong + " (přehrána " + maxSong.getPocetPrehrani() + "x)");
        }
    }

    // BONUS n. Seřaď playlist (1 = název, 2 = interpret, 3 = stopáž)
    public void sort(int criteria) {
        if (head == null || head.getNext() == null) {
            System.out.println("Playlist je příliš krátký na řazení.");
            return;
        }
        List<Song> songs = new ArrayList<>();
        Node current = head;
        while (current != null) {
            songs.add(current.getData());
            current = current.getNext();
        }

        switch (criteria) {
            case 1:
                songs.sort(Comparator.comparing(Song::getNazev, String.CASE_INSENSITIVE_ORDER));
                break;
            case 2:
                songs.sort(Comparator.comparing(Song::getInterpret, String.CASE_INSENSITIVE_ORDER));
                break;
            case 3:
                songs.sort(Comparator.comparingInt(Song::getDelkaS));
                break;
            default:
                System.out.println("Neplatná volba kritéria řazení.");
                return;
        }

        head = null;
        tail = null;
        currentlyPlaying = null;
        for (Song s : songs) {
            add(s);
        }
        System.out.println("Playlist byl úspěšně seřazen.");
    }

    // BONUS o. Exportuj seznam písní do souboru (txt, csv, xml, json)
    public void exportToFile(String fileName, String format) {
        if (head == null) {
            System.out.println("Playlist je prázdný, nelze exportovat.");
            return;
        }
        try (FileWriter writer = new FileWriter(fileName)) {
            Node current = head;
            format = format.toLowerCase().trim();
            if (format.equals("csv")) {
                writer.write("Nazev;Interpret;DelkaSekund\n");
                while (current != null) {
                    Song s = current.getData();
                    writer.write(s.getNazev() + ";" + s.getInterpret() + ";" + s.getDelkaS() + "\n");
                    current = current.getNext();
                }
            } else if (format.equals("json")) {
                writer.write("[\n");
                while (current != null) {
                    Song s = current.getData();
                    writer.write("  {\n");
                    writer.write("    \"nazev\": \"" + s.getNazev() + "\",\n");
                    writer.write("    \"interpret\": \"" + s.getInterpret() + "\",\n");
                    writer.write("    \"delkaS\": " + s.getDelkaS() + "\n");
                    writer.write("  }" + (current.getNext() != null ? "," : "") + "\n");
                    current = current.getNext();
                }
                writer.write("]\n");
            } else if (format.equals("xml")) {
                writer.write("<playlist>\n");
                while (current != null) {
                    Song s = current.getData();
                    writer.write("  <song>\n");
                    writer.write("    <nazev>" + s.getNazev() + "</nazev>\n");
                    writer.write("    <interpret>" + s.getInterpret() + "</interpret>\n");
                    writer.write("    <delkaS>" + s.getDelkaS() + "</delkaS>\n");
                    writer.write("  </song>\n");
                    current = current.getNext();
                }
                writer.write("</playlist>\n");
            } else { // txt / výchozí
                while (current != null) {
                    writer.write(current.getData().toString() + "\n");
                    current = current.getNext();
                }
            }
            System.out.println("Playlist byl exportován do souboru: " + fileName);
        } catch (IOException e) {
            System.out.println("Chyba při zápisu do souboru: " + e.getMessage());
        }
    }

    // BONUS p. Vyhledávání podle části názvu písně
    public void searchByName(String keyword) {
        if (head == null) {
            System.out.println("Playlist je prázdný.");
            return;
        }
        Node current = head;
        boolean found = false;
        int index = 0;
        System.out.println("Výsledky vyhledávání pro '" + keyword + "':");
        while (current != null) {
            if (current.getData().getNazev().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("[" + index + "] " + current.getData());
                found = true;
            }
            current = current.getNext();
            index++;
        }
        if (!found) {
            System.out.println("Žádná píseň neodpovídá zadanému hledání.");
        }
    }

    // Pomocná metoda pro vyhledání uzlu na pozici
    private Node getNodeAtIndex(int index) {
        if (index < 0 || head == null) return null;
        Node current = head;
        int i = 0;
        while (current != null && i < index) {
            current = current.getNext();
            i++;
        }
        return current;
    }

    // Pomocná metoda pro bezpečné odstranění uzlu
    private void removeNode(Node node) {
        if (node == currentlyPlaying) {
            currentlyPlaying = node.getNext();
        }
        if (node == head) {
            head = node.getNext();
        }
        if (node == tail) {
            tail = node.getPrev();
        }
        if (node.getPrev() != null) {
            node.getPrev().setNext(node.getNext());
        }
        if (node.getNext() != null) {
            node.getNext().setPrev(node.getPrev());
        }
    }

    public int getSize() {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }
}
