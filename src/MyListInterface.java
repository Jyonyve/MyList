
public interface MyListInterface<E> {
    int size();
    boolean isEmpty();
    boolean contains(E element);
    MyIterator<E> iterator();
    void add(E element);
    void add(int index, E element);
    E get(int index);
    void remove(E element);
    void remove(int index);
    void addAll(MyListInterface<? extends E> myList);
    void clear();
    <T> T[] toArray(T[] some);

}
