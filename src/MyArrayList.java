import java.util.Arrays;

public class MyArrayList<E> implements MyListInterface<E>{
    //
    private final int ARRAY_START_LENGTH = 10;
    private Object[] arr;
    private int currentArrayLength;
    private int currentListSize;

    public MyArrayList(){
        //
        this.arr = new Object[ARRAY_START_LENGTH];
        this.currentArrayLength =arr.length;
        this.currentListSize = 0;
    }
    public MyArrayList(int arrayStartLength) {
        //
        if(arrayStartLength <= 0){
            throw new IndexOutOfBoundsException("Array Length should be larger than 0.");
        }
        this.arr = new Object[arrayStartLength];
        this.currentArrayLength = arr.length;
        this.currentListSize = 0;
    }

    @Override
    public int size() {
        //
        return currentListSize;
    }

    @Override
    public boolean isEmpty() {
        //
        return currentListSize == 0;
    }

    @Override
    public MyIterator<E> iterator() {
        //
        return new MyIterator<>(this);
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
        if (currentListSize >= currentArrayLength) {
            resizeArray();
        }
        arr[currentListSize++] = element;
    }

    private void resizeArray(){
        //
        Object[] newArr = new Object[currentArrayLength *2];
        System.arraycopy(arr, 0, newArr, 0, currentArrayLength);
        //arr 의 0번부터  복사해서, newArr 의 0번부터 currentArrayLength 까지 담는다 의 의미.
        arr = newArr;
        currentArrayLength = arr.length;
    }

    private void checkIndexBound(int index){
        //
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void add(int index, E element) {
        //
        checkIndexBound(index);
        if (currentListSize >= currentArrayLength) {
            resizeArray();
        }
        moveRight( index, currentListSize);
        arr[index] = element;
        currentListSize++;
    }

    private void moveRight(int index,  int originalSize) {
        //
        for (; originalSize > index ; originalSize--) {
            arr[originalSize] = arr[originalSize - 1];
        }
    }

    @Override
    public E get(int index) {
        //
        checkIndexBound(index);
        return (E)arr[index];
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
            currentListSize--;
        }
    }
    private void moveLeft(int index, int originalSize) {
        //
        for (int i = index; i < originalSize-1; i++) {
            arr[i] = arr[i+1];
        }
        arr[originalSize - 1] = null;
    }

    @Override
    public void remove(int index) {
        //
        checkIndexBound(index);
        int originalSize = size();
        if(index < originalSize -1){
            arr[index] = null;
            moveLeft(index, originalSize);
        } else {
            arr[index] = null;
        }
        currentListSize--;
    }

    @Override
    public void addAll(MyListInterface<? extends E> myList) {
        //
        int requiredArraySize = currentListSize + myList.size();
        Object[] newArr = new Object[requiredArraySize];

        System.arraycopy(arr, 0, newArr, 0, currentArrayLength);
        System.arraycopy(myList.toArray(newArr),0, newArr, size(), myList.size());
        arr = newArr;
        currentArrayLength = arr.length;
        currentListSize += myList.size();
    }

    @Override
    public void clear() {
        //
        arr = new Object[ARRAY_START_LENGTH];
        currentArrayLength = arr.length;
        currentListSize = 0;
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