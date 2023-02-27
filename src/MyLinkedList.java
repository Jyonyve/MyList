import java.util.Arrays;

public class MyLinkedList<E> implements MyListInterface<E>{
    //
    private Node<E> node;

    public MyLinkedList(){
        //
        node = new Node<>();
    }

    @Override
    public int size() {
        //
        int i = 0;
        Node<E> current = node;
        while (current != null){
            current = current.getNext();
            i++;
        }
        return i;
    }

    @Override
    public boolean contains(E element) {
        //
        MyIterator<E> iterator = iterator();
        while (iterator.hasNext()){
            if(iterator.next().equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public MyIterator<E> iterator() {
        //
        return new MyIterator<>(this);
    }

    @Override
    public boolean empty() {
        //
        return node == null;
    }

    @Override
    public void add(E element) {
        //
        Node<E> newNode = new Node<>(null, element);
        if(node == null){
            node = newNode;
        } else {
        Node current = getLastNode();
            current.setNext(newNode);
        }
    }

    private Node<E> getLastNode(){
        //
        Node<E> current = node;
        while (current !=null && current.getNext() != null){
            current = current.getNext();
        }
        return current;
    }

    private Node<E> getIndexingNode(int index){
        //
        indexBoundChecker(index);
        Node<E> indexNode = node;
        for (int i = 0; i < index; i++) {
            indexNode = indexNode.getNext();
        }
        return indexNode;
    }

    private void indexBoundChecker(int index){
        //
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void add(int index, E element) {
        //
        indexBoundChecker(index);
        if(index == 0){
            node = new Node<>(node, element); // 현재의 node(첫번째 노드)를 다음 노드로 하는 새 노드를 만든다.
        } else {
            Node<E> currentNode = getIndexingNode(index);
            currentNode.setNext(new Node<>(currentNode.getNext(), element));
        }
    }

    public E set(int index, E element) {
        //
        Node<E> beforeNode = getIndexingNode(index-1);
        Node<E> newTailNode = beforeNode.getNext().getNext();
        Node<E> newNode = new Node<E>(newTailNode, element); //newTailNode를 꼬리로 하는 새 노드를 만들고, 이걸 연결함
        beforeNode.setNext(newNode);
        return newNode.getContent();
    }

    @Override
    public void remove(E element) {
        //
        Node<E> current = node;
        while (current.getNext() != null) {
            if (current.getNext().getContent().equals(element)) {
                current.setNext(current.getNext().getNext());
                break;
            }
            current = current.getNext();
        }
    }

    @Override
    public void remove(int index) {
        //
        indexBoundChecker(index);
        Node<E> removeNode = getIndexingNode(index);
        if (index == 0) {
            node = node.getNext(); //다음(두번째) 노드를 첫번째로 바꿔버림
        } else{
            Node<E> beforeNode = getIndexingNode(index -1);
            beforeNode.setNext(removeNode.getNext());
        }
    }

    @Override
    public void addAll(MyListInterface<? extends E> myList) {
        //
        for (int i = 0; i < myList.size(); i++) {
            add(myList.get(i));
        }
    }

    @Override
    public void clear() {
        //
        node = null;
    }

    @Override
    public <T> T[] toArray(T[] some) {
        //
        int size = size();
        if (some.length < size) {
            some = Arrays.copyOf(some, size);
        }
        int i = 0;
        for (Node<E> firstNode = node; firstNode != null ; firstNode = firstNode.getNext()) {
            some[i++] = (T) firstNode.getContent();
        }
        return some;
    }

    @Override
    public E get(int index) {
        //
        return getIndexingNode(index).getContent();
    }
}
