import java.util.Arrays;

public class MyArrayList<E> implements MyListInterface<E>{

    //임의의 내부 배열 선언
    private final int ARRAY_START_NUMBER = 10;
    private Object[] arr;

    public MyArrayList(){
        arr = new Object[ARRAY_START_NUMBER];
    }
    public MyArrayList(int arrayStartNumber) {
        arr = new Object[arrayStartNumber];
    }

    @Override
    public int size() {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) { //리스트는 중간에 빈 원소가 없으므로, 첫번째 null 이 끝이 된다
                return i;
            }
        }
        return arr.length;
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

    @Override
    public <E> void add(E element) {
        if (size() >= arr.length) {
            Object[] newArr = resizeArray(arr);
            arr = newArr;
        }
        arr[size()] = element;
    }

    private Object[] resizeArray(Object[] arr){
        //ArrayList 안에서 사용하는 원시 배열의 길이를 늘리는 메소드.
        //기존 배열을 복사하여 더 긴 배열에 담는다. (여기서는 단순히 길이를 2배로 늘리는 로직 사용)
        Object[] newArr = new Object[arr.length *2];
        int i = 0;
        //반복자 안에서는 배열에 [i]를 이용해서 다이렉트하게 접근하는 게 아니라
        //next()메소드를 사용해서 반복자가 생성한 배열의 원소를 0번부터 가리킬 수 있다.
        MyIterator<E> iterator = iterator();
        while(iterator.hasNext()){
            newArr[i] = iterator.next();
            i++;
        }
//      System.arraycopy(arr, 0, newArr, 0, arr.length);
//      while 문을 사용하여 배열에 하나하나 담는 대신 arraycopy 를 이용해 한번에 복사할 수도 있다.
//      ar r의 0번부터, arr.length 만큼 복사해서, newArr 의 0번부터 담는다 의 의미.
        return newArr;
    }

    private void indexBoundChecker(int index){
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void add(int index, E element) {
        indexBoundChecker(index);
        if (size() >= arr.length) {
            Object[] newArr = resizeArray(arr);
            arr = newArr;
        }
        moveRight(size(), arr, index);
        arr[index] = element;
    }

    private void moveRight(int originalSize, Object[] arr, int index) {
        for (; originalSize > index ; originalSize--) {
            arr[originalSize] = arr[originalSize - 1];
            //-- 개념은 가장 오른쪽 끝에 있는 원소부터(제일 큰 것) 그 다음 오른쪽 빈칸으로 이동하는 것을 말함.
            //index에 도달할 때까지 가장 오른쪽 원소부터 그 옆 오른쪽으로 하나하나 옮겨가며 그 옆칸으로 이동시킨다.(그래야 덮어씌워지지 않는다.)
        }
    }

    @Override
    public E get(int index) {
        indexBoundChecker(index);
        return (E)(arr[index]);
    }

    @Override
    public void remove(Object object) {
        int index = 0;
        //지우기 위해 null 로 세팅하는 순간 size()값이 변한다. 미리 size()를 픽스해 내려준다.
        int originalSize = size();
        MyIterator<E> iterator = iterator();
        //moveLeft 가 무조건 호출되지 않도록 값을 찾았는지 여부 알려주는 boolean 값을 넣는다.
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
            arr[index] = null; // set the last element to null
        }
    }

    @Override
    public void addAll(MyListInterface<? extends E> collection) {
        int requiredArraySize = size() + collection.size();
        E[] newArr = (E[]) new Object[requiredArraySize];

        System.arraycopy(arr, 0, newArr, 0, arr.length);
        System.arraycopy(collection.toArray((E[]) new Object[collection.size()]), 0, newArr, size(), collection.size());
//      = System.arraycopy(collection.toArray(newArr), 0, newArr, size(), collection.size());
//        collection.toArray(***) : *** 안에 들어가는 배열은 E[]타입이기만 하면 길이 상관 없지만,
//        이해를 돕기 위해서 collection.size()의 새 E[] 타입 배열을 생성해서 넣었다.

        arr = newArr;
    }

    @Override
    public void clear() {
        arr = new Object[ARRAY_START_NUMBER];
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
            some[index] = (T)get(index); //완벽하게 타입 안정성을 보장하지는 않지만, 런타임 에러로 해결한다.
            //만약 클래스의 E와 메소드의 T를 정확하게 일치시키고 싶다면, 메소드의 타입 <T>를 <T extends E>로 주면 된다.
            //하지만 toArray로 생성되는 배열과 List는 별개의 자료구조이므로, 배열은 상위 타입을 타입으로 가질 수 있기 때문에 <T>를 사용.
            //공변-불공변을 참조할 것.
        }
        return some;
    }


}