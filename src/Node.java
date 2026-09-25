public class Node {
    private Song data;
    private Node next;
    private Node prev;

    public Node(Song data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }
    public void setNext(Node next) {
        this.next = next;
    }
    public void setPrev(Node prev) {
        this.prev = prev;
    }
    public Song getData() {
        return data;
    }
    public void setData(Song data) {
        this.data = data;
    }


}
