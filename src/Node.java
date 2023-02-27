public class Node<E> { //Node<E>로 하면 제네릭 타입을 선언할 수 있다!
    //
    private Node<E> next;
    private E content;

    public Node() {
        //
        this.next = null;
        this.content = null;
    }

    public Node(Node<E> next, E content) {
        //
        this.next = next;
        this.content = content;
    }

    public Node<E> getNext() {
        //
        return next;
    }

    public void setNext(Node<E> next) {
        //
        this.next = next;
    }

    public E getContent() {
        //
        return content;
    }

    public void setContent(E content) {
        //
        this.content = content;
    }
}

