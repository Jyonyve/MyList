import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyIterator<E> implements Iterator<E> {
    //
    private MyList<E> list;
    private int index;
    private int length;

    MyIterator(MyList<E> list) {
        //
        this.list = list;
        this.index = 0;
        this.length = list.size();
    }

    @Override
    public boolean hasNext() {
        //
        return index < length;
    }

    @Override
    public E next() {
        //
        //System.arraycopy();     //list를 COPY
        return list.get(index++);
    }
}