import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyIterator<E> implements Iterator<E> {

    private final MyListInterface<E> list;
    int pointer = -1;

    MyIterator(MyListInterface<E> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        //포인터가 인덱스보다 작을 때 : 다음이 있다
        //포인터가 인덱스와 같아지면: 다음이 없다
        //포인터가 -1이어야지만 인덱스가 0일때 가지고있는 값 1개가 도출됨.
        return pointer < list.size()-1 ;
    }

    @Override
    public E next() {
        try {
            E e = list.get(++pointer);
            return e;
        }catch (NoSuchElementException exception){
            throw exception;
        }
    }

}
