import java.util.Arrays;

public class MyArrayList<E> implements MyListInterface<E>{
    //
    private final int ARRAY_START_NUMBER = 10;
    private Object[] arr;
    private int currentArrayLength;

    public MyArrayList(){
        //
        arr = new Object[ARRAY_START_NUMBER];
        this.currentArrayLength =arr.length;
    }
    public MyArrayList(int arrayStartNumber) {
        //
        if(arrayStartNumber <= 0){
            throw new IndexOutOfBoundsException("Array Length should be larger than 0.");
        }
        arr = new Object[arrayStartNumber];
        currentArrayLength = arr.length;
    }

    @Override
    public int size() {
        //
        for (int i = 0; i < currentArrayLength; i++) {
            if (arr[i] == null) {
                return i;
            }
        }
        return currentArrayLength;
    }

    @Override
    public boolean empty() {
        //
        //not thread-safe but this code is focused on simplifying.
        return arr[0] == null;
    }

    @Override
    public MyIterator<E> iterator() {
        //
        return new MyIterator<E>(this);
    }

    @Override
    public boolean contains(E element) {
        //
        MyIterator<E> iterator = iterator();
        while (iterator.hasNext()){
            E elementInList = iterator.next();
            if (elementInList.equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(E element) {
        //
        if (size() >= currentArrayLength) {
            resizeArray();
        }
        arr[size()] = element;
    }

    private void resizeArray(){
        //
        Object[] newArr = new Object[currentArrayLength *2];
        int i = 0;
        System.arraycopy(arr, 0, newArr, 0, currentArrayLength);
        //arr 의 0번부터 currentArrayLength 까지 복사해서, newArr 의 0번부터 담는다 의 의미.
        arr = newArr;
        currentArrayLength = arr.length;
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
        if (size() >= currentArrayLength) {
            resizeArray();
        }
        moveRight(size(), arr, index);
        arr[index] = element;
    }

    private void moveRight(int originalSize, Object[] arr, int index) {
        //
        for (; originalSize > index ; originalSize--) {
            arr[originalSize] = arr[originalSize - 1];
        }
    }

    @Override
    public E get(int index) {
        //
        indexBoundChecker(index);
        return (E)(arr[index]);
    }

    @Override
    public void remove(E element) {
        //
        int index = 0;        //지우기 위해 null 로 세팅하는 순간 size()값이 변한다. 미리 size()를 픽스해 내려준다.
        int originalSize = size();
        MyIterator<E> iterator = iterator();
        boolean found = false;

        while (iterator.hasNext()){
            if (arr[index].equals(element)){
                arr[index] = null;
                found = true;
                break;
            }
            index++;
        }
        if(found) {
            moveLeft(index, originalSize);
        }
    }
    private void moveLeft(int index, int originalSize) {
        //
        for (int i = index; i < originalSize-1; i++) {
            arr[i] = arr[i+1];
        }
        arr[originalSize - 1] = null; // set the last element to null
    }

    @Override
    public void remove(int index) {
        //
        indexBoundChecker(index);
        int originalSize = size();
        if(index < originalSize -1){
            arr[index] = null;
            moveLeft(index, originalSize);
        }
        else {
            arr[index] = null; // set the last element to null
        }
    }

    @Override
    public void addAll(MyListInterface<? extends E> myList) {
        //
        int requiredArraySize = size() + myList.size();
        E[] newArr = (E[]) new Object[requiredArraySize];

        System.arraycopy(arr, 0, newArr, 0, currentArrayLength);
        System.arraycopy(myList.toArray((E[]) new Object[myList.size()]),
                0, newArr, size(), myList.size());
        arr = newArr;
        currentArrayLength = arr.length;
    }

    @Override
    public void clear() {
        //
        arr = new Object[ARRAY_START_NUMBER];
        currentArrayLength = arr.length;
    }

    @Override
    public <T> T[] toArray(T[] some) {
        //
        int arrSize = size();
        if (some.length < arrSize) {
            some = Arrays.copyOf(some, arrSize);
        }
        System.arraycopy(arr, 0, some, 0, arrSize);
        return some;
    }
}