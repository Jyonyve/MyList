import java.util.Arrays;

public class MyLinkedList<E> implements MyListInterface<E>{

    //노드를 선언하여 노드끼리 연결한다. 노드 클래스 필요
    private Node<E> node;
    public MyLinkedList(){
        node = null; // node를 널값으로 초기화해준다.
    };


    @Override
    public int size() {
        int i = 0;
        Node<E> current = node;
        //node를 대신해서 동일하게 current를 선언해주면
        //첫 node가 null인 경우를 검사할 수 있어서
        //아무 값도 없을때 NullpointException이 뜨는 것을 방지해준다. => 바로 리턴 0 으로 넘어감.
        //바로 node를 사용할 경우는 이 메소드 내에서 검증을 할 수 없어서 예외발생.
        while (current !=null){
            current = current.getNext();
            i++;
        }
        return i;
    }

    @Override
    public boolean contains(Object object) {
        int i = 0;
        while (iterator().hasNext()){
            if(get(i).equals(object)){
                return true;
            }
            i++;
        }
        return false;
    }

    @Override
    public MyIterator<E> iterator() {
        return new MyIterator<>(this);
    }

    @Override
    public boolean empty() {
        return node == null;
    }

    @Override
    public <E> void add(E element) {
        Node newNode = new Node<>(null, element); //일단 값을 담은 빈 노드를 만든다.
        //이게 첫 add인 경우
        if(node == null){
            node = newNode;
        }
        else {//이미 있는 노드에 추가하는 경우
        Node current = getLastNode();
            current.setNext(newNode);
        }
    }
    private Node<E> getLastNode(){
        Node<E> current = node;
        while (current !=null && current.getNext() != null){
            current = current.getNext();
        }
        return current;
    }

    private Node<E> getIndexingNode(int index){
        Node<E> indexingNode = node;
        for(int i = 0; i <index ;i++){
            indexingNode = indexingNode.getNext();
        }
        return indexingNode;
    }
    private void indexBoundChecker(int index){
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void add(int index, E element) {
        //인덱스가 오류인 경우
        indexBoundChecker(index);
        //맨 첫번째 노드에 삽입하는 경우
        if(index == 0){
            node = new Node<>(node, element); // 현재의 node를 다음 노드로 하는 새 노드를 만든다.
            //챗gpt의 천재코드...
        }
        //2번째 이상의 노드에 삽입하는 경우
        else {
            Node<E> currentNode = getIndexingNode(index);
            currentNode.setNext(new Node<>(currentNode.getNext(), element));
        }

    }

    @Override
    public void remove(int index) {
        indexBoundChecker(index);
        Node<E> removeNode = getIndexingNode(index);
        if (index == 0) {
            node = node.getNext(); //다음 노드를 첫번째로 바꿔버림
        }
        else{
            Node<E> beforeNode = getIndexingNode(index -1);
            beforeNode.setNext(removeNode.getNext());
        }
    }

    @Override
    public void addAll(MyListInterface<? extends E> collection) {
        for (int i = 0; i < collection.size(); i++) {
            getLastNode().setNext(new Node<E>(null, collection.get(i)));
        }
    }

    @Override
    public void clear() {
        node = null;
    }

    @Override
    public <T> T[] toArray(T[] some) {
        //list를 T[]타입 배열로 변환해서 리턴 : 먼저 사이즈에 맞는 타입캐스팅된 배열을 메소드 외부에서 생성한 다음, 거기에 리스트 안의 원소를 담아서 리턴한다.
        int size = size();
        if (some.length < size) {
            some = Arrays.copyOf(some, size);
        }
        int i = 0;
        for (Node<E> firstNode = node; firstNode != null; firstNode = firstNode.getNext()) {
            some[i++] = (T) firstNode.getContent(); //not perfectly type-safe, but original JAVA remain it as runtime exception too. (I checked it!)
        }
//        if (some.length > size) {
//            some[size] = null;
//        } 마지막 원소를 정확하게 null로 세팅하는 if문이지만, 어차피 사이즈 이상의 길이는 null로 채워지기 때문에 생략하였다.
        return some;
    }

    @Override
    public Object[] toArray() {
        Node<E> currentNode = node;
        int i = 0;
        Object[] newArr = new Object[size()];

        for (; i <size(); i++){
            newArr[i] = currentNode.getContent();
            currentNode = currentNode.getNext();
        }
        return newArr;
    }

    @Override
    public void remove(Object object) {
        Node current = node;
            while (current.getNext() != null) { // make sure current's next node isn't null
            if (current.getNext().getContent().equals(object)) {
                current.setNext(current.getNext().getNext());
                break; // stop after removing the first matching element
            }
            current = current.getNext();
        }
    }

    @Override
    public E get(int index) {
       return getIndexingNode(index).getContent();
    }

    public E set(int index, E element) {
        Node<E> beforeNode = getIndexingNode(index-1);
        Node<E> newTailNode = beforeNode.getNext().getNext();
        Node<E> newNode = new Node<E>(newTailNode, element); //newTailNode를 꼬리로 하는 새 노드를 만들고, 이걸 연결함
        beforeNode.setNext(newNode);
        return newNode.getContent();
    }
}
