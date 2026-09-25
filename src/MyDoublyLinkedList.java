public class MyDoublyLinkedList {
    private Node head;
    private Node tail;

    public void add(Song song){
        Node newNode = new Node(song);
        if(tail == null){
            head = newNode;
            tail = newNode;
            return;
        }

        tail.setNext(newNode);
        newNode.setPrev(tail);

        tail = newNode;
    }
    public void addfirst(Song song){
        Node newNode = new Node(song);
        if(head == null){
            head = newNode;
            tail = newNode;
            return;
        }
        head.setNext(newNode);
        newNode.setPrev(head);
    }

    public void printAll() {
        Node current = head;
        while (current != null) {
            System.out.println(current.getData().getSongName());
            current = current.getNext();
        }
    }
}
