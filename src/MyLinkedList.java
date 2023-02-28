import java.util.Arrays;

public class MyLinkedList<E> implements MyListInterface<E>{
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

    private Node<E> getLastNode(){
        //
        Node<E> current = node;
        while (current !=null && current.getNext() != null){
            current = current.getNext();
        }
        return current;
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


    public E set(int index, E element) {
        //
        Node<E> beforeNode = getIndexNode(index-1);
        Node<E> newTailNode = beforeNode.getNext().getNext();
        Node<E> newNode = new Node<>(newTailNode, element);
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
        currentListSize--;
    }
//    public void remove(E element) {
//        Node<E> prev = null;
//        Node<E> current = node;
//        while (current != null) {
//            if (current.getContent().equals(element)) {
//                if (prev == null) {
//                    node = current.getNext();
//                } else {
//                    prev.setNext(current.getNext());
//                }
//                currentListSize--;
//                return;
//            }
//            prev = current;
//            current = current.getNext();
//        }
//    }


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
    public void addAll(MyListInterface<? extends E> myList) {
        //
        for (int i = 0; i < myList.size(); i++) {
            add(myList.get(i));
        }
        currentListSize = currentListSize + myList.size();
    }

    @Override
    public void clear() {
        //
        node = null;
        currentListSize = 0;
    }

    private <T> T[] increaseArrayCapacity(T[] some){
        //
        if (some.length < currentListSize) {
            some = Arrays.copyOf(some, currentListSize);
        }
        return some;
    }

    private <T> T[] setRemainArrayNull(T[] some){
        //
        if(some.length > currentListSize){
            some[currentListSize] = null;
        }
        return some;
    }

    @Override
    public <T> T[] toArray(T[] some) {
        //
        some = Arrays.copyOf(some, currentListSize);
        int i = 0;
        for (Node<E> current = node; current != null; current = current.getNext()) {
            some[i++] = (T) current.getContent();
        }
        some = setRemainArrayNull(some);
        return some;
    }

    @Override
    public E get(int index) {
        //
        return getIndexNode(index).getContent();
    }
}
