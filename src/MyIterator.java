import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyIterator<E> implements Iterator<E> {
    //
    private final MyListInterface<E> list;
    private int pointer;

    MyIterator(MyListInterface<E> list) {
        this.list = list;
        this.pointer = -1;      //포인터가 -1이어야지만 인덱스가 0일때 가지고있는 값 1개가 도출됨.
    }

    @Override
    public boolean hasNext() {
        //
        return pointer < list.size()-1 ;
    }

    @Override
    public E next() {
        try {
            E e = list.get(++pointer);
            return e;
        } catch (NoSuchElementException exception){
            throw exception;
        }
    }

}
