
public interface MyListInterface<E> {
    int size();
    boolean empty();
    boolean contains(Object object);
    MyIterator<E> iterator();
    <E> void add(E element);
    void add(int index, E element);
    E get(int index);
    void remove(Object object);
    void remove(int index);
    void addAll(MyListInterface<? extends E> collection);
    void clear();
    <T> T[] toArray(T[] some); //to not type-casting every time
    Object[] toArray();

}
