public class Node {
    private Song data;
    private Node next;
    private Node previous;

    public Node(Song data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public Song getData() { return data; }
    public void setData(Song data) { this.data = data; }

    public Node getNext() { return next; }
    public void setNext(Node next) { this.next = next; }

    public Node getPrev() { return previous; }
    public void setPrev(Node previous) { this.previous = previous; }
}
