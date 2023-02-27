import java.util.Arrays;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        //
        runTestMyLists();
    }

    private static void printList(MyLinkedList myLinkedList, MyArrayList myArrayList){
        //
        System.out.println("------------MyLinkedList-----------");
        Iterator iterator = myLinkedList.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            System.out.println(index + " : " + iterator.next());
            index++;
        }
        System.out.println("\nMyLinkedList size : " + myLinkedList.size());

        System.out.println("\n------------MyArrayList-----------");
        for(int i = 0; i < myArrayList.size() ; i++){
            System.out.println(i + " : " + myArrayList.get(i));
        }
        System.out.println("\nMyArrayList size : " + myArrayList.size());
        System.out.println("\n********* List Finished *********\n");
    }

    private static void addByElement(MyLinkedList myLinkedList, MyArrayList myArrayList){
        //
        myLinkedList.add("JDM");
        myLinkedList.add("STS");
        myLinkedList.add("KBH");
        myLinkedList.add("STW");
        myLinkedList.add("CCS");
        myLinkedList.add("CSY");
        myLinkedList.add("LHN");
        myLinkedList.add("YHY");
        myLinkedList.add("YDH");
        myLinkedList.add("JHJ");
        myLinkedList.add("SJS");
        myLinkedList.add("LDJ");
        myLinkedList.add("PC");
        myLinkedList.add("LYG");

        myArrayList.add("JWS");
        myArrayList.add("LMH");
        myArrayList.add("SHC");
        myArrayList.add("SHP");
        myArrayList.add("CDO");
        myArrayList.add("LJH");
        myArrayList.add("JHJ");
        myArrayList.add("KSK");
        myArrayList.add("AHS");
        myArrayList.add("KEJ");
        myArrayList.add("KKH");
    }

    private static void addByIndex(MyLinkedList myLinkedList, MyArrayList myArrayList){
        //
        myLinkedList.add(0, "The First SLAMDUNK");
        myLinkedList.add(5,"KJH");

        myArrayList.add(0, "BasketBall");
        myArrayList.add(1, "BS");
        myArrayList.add(2, "SW");
    }
    private static void removeByElementOrIndex(MyLinkedList myLinkedList, MyArrayList myArrayList){
        //
        myLinkedList.remove("LHN");
        myLinkedList.remove(7);

        myArrayList.remove("KEJ");
        myArrayList.remove(11);
    }
    private static void containsCheck(MyLinkedList myLinkedList, MyArrayList myArrayList){
        //
        System.out.println("방금 지운 LHN의 값이 있나요?: " + myLinkedList.contains("LHN"));
        System.out.println("방금 지운 index 7(CSY)의 값이 있나요?: " + myLinkedList.contains("CSY"));
        System.out.println("지우지 않은 JDM의 값이 있나요?: " + myLinkedList.contains("JDM"));

        System.out.println("방금 지운 KEJ의 값이 있나요?: " + myArrayList.contains("KEJ"));
        System.out.println("방금 지운 index 11의 값(AHS)이 있나요?: " + myArrayList.contains("AHS") );
        System.out.println("지우지 않은 KKH의 값이 있나요?: " + myArrayList.contains("KKH")+"\n");
    }
    private static void addAllByInterface(MyLinkedList myLinkedList, MyArrayList myArrayList){
        //
        myLinkedList.addAll(myArrayList);
        myArrayList.addAll(myLinkedList);
    }

    private static <E> E[] myArrayListToArray(MyArrayList myArrayList){
        //
        Object[] arrArrayList = new Object[myArrayList.size()];
        arrArrayList = (E[]) myArrayList.toArray(arrArrayList);
        return (E[])arrArrayList;
    }

    private static <E> E[] myLinkedListToArray(MyLinkedList myLinkedList){
        //
        Object[] arrLinkedList = new Object[3];
        arrLinkedList = (E[]) myLinkedList.toArray(arrLinkedList);
        return (E[])arrLinkedList;
    }

    private static <E> void printMyListToArray(E[] arrArrayList, E[] arrLinkedList){
        //
        System.out.println("~~~~~~~~~ arrArrayList ~~~~~~~~~");
        Arrays.stream(arrArrayList).iterator().forEachRemaining(System.out :: println);
        System.out.println("arrArrayList length : " + arrArrayList.length);

        System.out.println("\n~~~~~~~~~ arrLinkedList ~~~~~~~~~");
        Arrays.stream(arrLinkedList).iterator().forEachRemaining(System.out :: println);
        System.out.println("arrLinkedList length : " + arrLinkedList.length);
    }

    private static void isMyListEmpty(MyLinkedList myLinkedList, MyArrayList myArrayList){
        //
        System.out.println("myLinkedList가 비어 있나요? empty() : " + myLinkedList.empty());
        System.out.println("myArrayList가 비어 있나요? empty() : " + myArrayList.empty());
    }

    private static <E> void runTestMyLists(){
        //
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        MyArrayList<String> myArrayList= new MyArrayList<>();

        System.out.println("\n\t1. 기본 리스트 출력 ");
        addByElement(myLinkedList, myArrayList);
        printList(myLinkedList, myArrayList);

        System.out.println("\n\t2. add(index) 리스트 출력 ");
        addByIndex(myLinkedList, myArrayList);
        printList(myLinkedList, myArrayList);

        System.out.println("\n\t3. 삭제 후 리스트 출력 ");
        removeByElementOrIndex(myLinkedList, myArrayList);
        printList(myLinkedList, myArrayList);
        containsCheck(myLinkedList, myArrayList);

        System.out.println("\n\t4. addAll 후 리스트 출력 ");
        addAllByInterface(myLinkedList, myArrayList);
        printList(myLinkedList, myArrayList);

        System.out.println("\n\t5. toArray로 만든 배열 출력 ");
        E[] arrLinkedList = myLinkedListToArray(myLinkedList);
        E[] arrArrayList = myArrayListToArray(myArrayList);
        printMyListToArray(arrArrayList, arrLinkedList);

        System.out.println("\n\t6. 리스트 클리어 후 리스트 출력 ");
        isMyListEmpty(myLinkedList, myArrayList);
        myLinkedList.clear();
        myArrayList.clear();
        printList(myLinkedList, myArrayList);
        isMyListEmpty(myLinkedList, myArrayList);

        System.out.println("\n\n\n");
    }
}