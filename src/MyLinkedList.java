import java.util.Arrays;

public class MyLinkedList<E> implements MyList<E> {
    //
    private Node<E> node;
    private int currentListSize;

    public MyLinkedList(){
        //
        this.node = null;
        this.currentListSize = 0;
    }

    @Override
    public int size() {
        //
        return currentListSize;
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
    public boolean isEmpty() {
        //
        return currentListSize == 0;
    }

    @Override
    public void add(E element) {
        //
        add(currentListSize, element);
    }

    private Node<E> getIndexNode(int index){
        //
        checkIndexBound(index);
        Node<E> indexNode = node;
        for (int i = 0; i < index; i++) {
            indexNode = indexNode.getNext();
        }
        return indexNode;
    }

    private void checkIndexBound(int index){
        //
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void add(int index, E element) {
        checkIndexBound(index);
        if (index == 0) {
            node = new Node<>(node, element);
        } else {
            Node<E> prev = getIndexNode(index - 1);
            prev.setNext(new Node<>(prev.getNext(), element));
        }
        currentListSize++;
    }

    @Override
    public void remove(E element) {
        //
        Node<E> current = node;
        while (!isEmpty()) {
            if (current.getNext().getContent().equals(element)) {
                current.setNext(current.getNext().getNext());
                break;
            }
            current = current.getNext();
        }
        currentListSize--;
    }

    @Override
    public void remove(int index) {
        //
        checkIndexBound(index);
        Node<E> removeNode = getIndexNode(index);
        if (index == 0) {
            node = node.getNext(); //다음(두번째) 노드를 첫번째로 바꿔버림
        } else{
            Node<E> beforeNode = getIndexNode(index -1);
            beforeNode.setNext(removeNode.getNext());
        }
        currentListSize--;
    }

    @Override
    public void addAll(MyList<? extends E> myList) {
        //
        myList.iterator().forEachRemaining(this::add);
    }

    @Override
    public void clear() {
        //
        node = null;
        currentListSize = 0;
    }

    @Override
    public <T> T[] toArray(T[] some) {
        //
        some = Arrays.copyOf(some, currentListSize);
        int i = 0;
        for (Node<E> current = node; current != null; current = current.getNext()) {
            some[i++] = (T) current.getContent();
        }
        return some;
    }

    @Override
    public E get(int index) {
        //
        return getIndexNode(index).getContent();
    }
}
