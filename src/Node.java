public class Node<E> { //Node<E>로 하면 제네릭 타입을 선언할 수 있다!
    private Node<E> next = null; //싱글 링크드리스트는 헤드를 가질 필요가 없다!
    private E content = null;

    public Node() {
    }

    public Node(Node<E> next, E content) {
        this.next = next;
        this.content = content;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }
}

