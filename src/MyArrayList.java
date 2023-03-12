import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements MyList<E> {
    //
    private final int INITIAL_CAPACITY = 10;

    private Object[] elements;     //elements
    private int length;
    private int capacity;

    public MyArrayList(){
        //
        initialize(INITIAL_CAPACITY);
    }

    public MyArrayList(int initialCapacity) {
        //
        if(initialCapacity <= 0){
            throw new IllegalArgumentException("Initial capacity should be larger than 0: " + initialCapacity);
        }
        initialize(initialCapacity);
    }

    @Override
    public int size() {
        //
        return length;
    }

    @Override
    public boolean isEmpty() {
        //
        return length == 0;
    }

    @Override
    public MyIterator<E> iterator() {
        //
        return new MyIterator<>(this);
    }

    @Override
    public boolean contains(E element) {
        //
        for(int index = 0 ; index < length ; index++){
            if (elements[index].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(E element) {
        //
        if (length >= capacity) {
            increaseCapacity();
        }

        elements[length++] = element;
    }

    @Override
    public void add(int index, E element) {
        //
        checkIndexBound(index);

        if (length >= capacity) {
            increaseCapacity();
        }

        shiftRight(index, length);
        elements[index] = element;
        length++;
    }

    @Override
    public E get(int index) {
        //
        checkIndexBound(index);
        return (E) elements[index];
    }

    @Override
    public void remove(E element) {
        //
        int targetIndex = indexOf(element);

        shiftLeft(targetIndex);
        capacity--;
    }

    private int indexOf(E element){
        //
        for(int index = 0 ; index < length ; index++){
            if(elements[index].equals(element)){
                return index;
            }
        }

        throw new NoSuchElementException("Element: " + element);
    }

    private void shiftLeft(int index) {
        //
        for (int i = index; i < length-1; i++) {
            elements[i] = elements[i+1];
        }
        elements[length - 1] = null;
    }

    @Override
    public void remove(int index) {
        //
        checkIndexBound(index);

        if(index < length -1){
            shiftLeft(index);
        } else {
            elements[index] = null;
        }
        capacity--;
    }

    @Override
    public void addAll(MyList<? extends E> myList) {
        //
        int requiredArraySize = length + myList.size();
        Object[] newArr = new Object[requiredArraySize];

        System.arraycopy(elements, 0, newArr, 0, length);
        System.arraycopy(myList.toArray(newArr),0, newArr, size(), myList.size());

        elements = newArr;
        length = elements.length;
        capacity += myList.size();
    }

    @Override
    public void clear() {
        //
        initialize(INITIAL_CAPACITY);
    }

    @Override
    public <T> T[] toArray(T[] target) {
        //
        int targetLength = length;

        if (target.length < targetLength) {
            target = Arrays.copyOf(target, targetLength);
        }

        System.arraycopy(elements, 0, target, 0, targetLength);
        return target;
    }

    private void initialize(int capacity){
        //
        this.elements = new Object[capacity];
        this.length = 0;
        this.capacity = capacity;
    }

    private void increaseCapacity(){
        //
        int newCapacity = capacity * 2;
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, length);

        elements = newElements;
        capacity = newCapacity;
    }

    private void checkIndexBound(int index){
        //
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void shiftRight(int index, int originalSize) {
        //
        for (; originalSize > index ; originalSize--) {
            elements[originalSize] = elements[originalSize - 1];
        }
    }
}