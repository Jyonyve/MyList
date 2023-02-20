import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MyArrayList<E> implements MyListInterface<E>{

    //임의의 내부 배열 선언
    private final int ARRAY_START_NUMBER = 10;
    private Object[] arr = new Object[ARRAY_START_NUMBER];
    public MyArrayList(){};

    @Override
    public int size() {
//        int i = 0; //index == 0
//        while (arr[i] != null && i < arr.length-1){ //리스트는 중간에 빈 원소가 없으므로, 첫번째 null이 끝이 된다
//            i++;
//        }
//        return i;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                return i;
            }
        }
        return arr.length;
//        chatGPT code : asked to simplify the code.
    }

    @Override
    public boolean empty() {
        return arr[0] == null;
    }

    @Override
    public MyIterator<E> iterator() {
        return new MyIterator<E>(this);
    }

    @Override
    public boolean contains(Object object) {
        MyIterator<E> iterator = iterator();
        while (iterator.hasNext()){
            E element = iterator.next();
            if (element.equals(object)) {
                return true;
            }
        }
        return false;
    }

    private void indexBoundChecker(int index){
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Object[] resizeArray(Object[] arr){
        Object[] newArr = new Object[arr.length *2];
        int i = 0;
        //반복자 안에서는 반복자가 새로 배열을 생성해서 검색을 하기 때문에, 배열에 다이렉트하게 접근하는 게 아니라
        //next()메소드를 사용해서 반복자가 생성한 배열의 원소를 가리킬 수 있다.
        //하지만 바로 현재의 원소를 포인팅하려면 이터레이터를 선언해주어서 this를 만들어주어야 함.
        MyIterator<E> iterator = iterator();
        while(iterator.hasNext()){
            newArr[i] = iterator.next();
            i++;
        }
        //My code...

//        System.arraycopy(arr,0, newArr, 0, arr.length);
        //ChatGPT Generated code... new Method for me...! : System.arraycopy
        return newArr;
    }

    @Override
    public <E> void add(E element) {
//        while (true) {
//            if (size() < arr.length-1) {
//                arr[size()] = element;
//                break;
//            } else {
//                Object[] newArr = resizeArray(arr);
////                newArr[size()] = element;
//                arr = newArr;
//            }
//        }
        if (size() >= arr.length) {
            Object[] newArr = resizeArray(arr);
            arr = newArr;
        }
        arr[size()] = element; //revised by chatGPT
    }

    @Override
    public void add(int index, E element) {
        try {
            if (size() >= arr.length) {
                Object[] newArr = resizeArray(arr);
                arr = newArr;
            }
            moveRight(size(), arr, index);
            arr[index] = element;
        } catch (IndexOutOfBoundsException exception){
            exception.printStackTrace();
            throw exception;
        }
    }
    private void moveRight(int originalSize, Object[] arr, int index) {
        for (; originalSize > index ; originalSize--) {
            arr[originalSize] = arr[originalSize - 1];
            //-- 개념은 가장 오른쪽 끝에 있는 원소부터(제일 큰 것) 그 다음 오른쪽 빈칸으로 이동하는 것을 말함.
            //index에 도달할 때까지 가장 오른쪽 원소부터 왼쪽으로 하나하나 옮겨가며 그 옆칸으로 이동시킨다.
        }
    }

    @Override
    public E get(int index) {
        try{
            return (E)(arr[index]);
        }catch (IndexOutOfBoundsException indexOutOfBoundsException){
            indexOutOfBoundsException.printStackTrace();
            throw indexOutOfBoundsException;
        }
    }

    @Override
    public void remove(Object object) {
        int index = 0;
        int originalSize = size();
        //지우기 위해 null로 세팅하는 순간 size()값이 변한다. 미리 size()를 픽스해 내려준다.
        MyIterator<E> iterator = iterator();
        //moveleft가 무조건 호출되지 않도록 값을 찾았는지 여부 알려주는 boolean값을 넣는다.
        boolean found = false;
        while (iterator.hasNext()){
            if (arr[index].equals(object)){
                arr[index] =null;
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
        for (int i = index; i < originalSize-1; i++) {
            arr[i] = arr[i+1];
        }
        arr[originalSize - 1] = null; // set the last element to null
    }

    @Override
    public void remove(int index) {
        indexBoundChecker(index);
        int originalSize = size();
        if(index < originalSize -1){
            arr[index] = null;
            moveLeft(index, originalSize);
        }
        else {
            arr[index] = null;
        }
    }

    @Override
    public void addAll(MyListInterface<? extends E> collection) {
            int requiredArraySize = size() + collection.size();
            Object[] newArr = new Object[requiredArraySize];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            System.arraycopy(collection.toArray(), 0, newArr, size(), collection.size());
            arr = newArr;
    }

    @Override
    public void clear() {
        arr=new Object[ARRAY_START_NUMBER];
    }

    @Override
    public <T> T[] toArray(T[] some) {
        //<T>[]에 리스트의 원소를 담아서 리턴하는 메소드.
        int arrSize = size();
        if (some.length < arrSize) {
            some = Arrays.copyOf(some, arrSize); //만약 리스트의 길이보다 작은 배열이 인자로 들어왔을 경우, 길이를 늘린다.
        }
        int index = 0;
        for(; index < arrSize; index++){
            some[index] = (T)get(index);
        }
        return some;
    }

    @Override
    public Object[] toArray() {
        int size = size();
        Object[] arr = new Object[size];
        MyIterator<E> iterator = iterator();

        int index = 0;
        while (iterator.hasNext()) {
            arr[index++] = iterator.next();
        }
        return arr;
    }


}
