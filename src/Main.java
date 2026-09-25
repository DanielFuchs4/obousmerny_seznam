void main() {

    MyDoublyLinkedList list = new MyDoublyLinkedList();

    list.add(new Song("song1", "petr", 3));
    list.add(new Song("song2", "josef", 4));

    list.add(new Song("song3", "daniel", 5));
    list.addfirst(new Song("song4", "daniel", 6));

    list.printAll();


}
