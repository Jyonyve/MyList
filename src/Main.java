import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //
        runTestMyLists();
    }

    private static void printList(MyLinkedList<String> myLinkedList, MyArrayList<String> myArrayList){
        //
        System.out.println("------------MyLinkedList-----------");
        MyIterator<String> iterator = myLinkedList.iterator();
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

    private static void addByElement(MyLinkedList<String> myLinkedList, MyArrayList<String> myArrayList){
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

    private static void addByIndex(MyLinkedList<String> myLinkedList, MyArrayList<String> myArrayList){
        //
        myLinkedList.add(0, "The First SLAM DUNK");
        myLinkedList.add(5,"KJH");

        myArrayList.add(0, "BasketBall");
        myArrayList.add(1, "BS");
        myArrayList.add(2, "SW");
    }
    private static void removeByElementOrIndex(MyLinkedList<String> myLinkedList, MyArrayList<String> myArrayList){
        //
        myLinkedList.remove("LHN");
        myLinkedList.remove(7);

        myArrayList.remove("KEJ");
        myArrayList.remove(11);
    }
    private static void containsCheck(MyLinkedList<String> myLinkedList, MyArrayList<String> myArrayList){
        //
        System.out.println("?????? ?????? LHN??? ?????? ??????????: " + myLinkedList.contains("LHN"));
        System.out.println("?????? ?????? index 7(CSY)??? ?????? ??????????: " + myLinkedList.contains("CSY") + "\n");
        System.out.println("????????? ?????? JDM??? ?????? ??????????: " + myLinkedList.contains("JDM")+ "\n");

        System.out.println("?????? ?????? KEJ??? ?????? ??????????: " + myArrayList.contains("KEJ"));
        System.out.println("?????? ?????? index 11??? ???(AHS)??? ??????????: " + myArrayList.contains("AHS")+ "\n" );
        System.out.println("????????? ?????? KKH??? ?????? ??????????: " + myArrayList.contains("KKH")+"\n");
    }
    private static void addAllByInterface(MyLinkedList<String> myLinkedList, MyArrayList<String> myArrayList){
        //
        myLinkedList.addAll(myArrayList);
        myArrayList.addAll(myLinkedList);
    }

    private static <E> E[] myArrayListToArray(MyArrayList<String> myArrayList){
        //
        Object[] arrArrayList = new Object[myArrayList.size()];
        arrArrayList = myArrayList.toArray(arrArrayList);
        return (E[])arrArrayList;
    }

    private static <E> E[] myLinkedListToArray(MyLinkedList<String> myLinkedList){
        //
        String[] arrLinkedList = new String[3];
        arrLinkedList = myLinkedList.toArray(arrLinkedList);
        return (E[]) arrLinkedList;
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

    private static void isMyListEmpty(MyLinkedList<String> myLinkedList, MyArrayList<String> myArrayList){
        //
        System.out.println("myLinkedList??? ?????? ?????????? : " + myLinkedList.isEmpty());
        System.out.println("myArrayList??? ?????? ?????????? : " + myArrayList.isEmpty());
    }

    private static <E> void runTestMyLists(){
        //
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        MyArrayList<String> myArrayList= new MyArrayList<>(20);

        System.out.println("\n\t1. ?????? ????????? ?????? ");
        addByElement(myLinkedList, myArrayList);
        printList(myLinkedList, myArrayList);

        System.out.println("\n\t2. add(index) ????????? ?????? ");
        addByIndex(myLinkedList, myArrayList);
        printList(myLinkedList, myArrayList);

        System.out.println("\n\t3. ?????? ??? ????????? ?????? ");
        removeByElementOrIndex(myLinkedList, myArrayList);
        printList(myLinkedList, myArrayList);
        containsCheck(myLinkedList, myArrayList);

        System.out.println("\n\t4. addAll ??? ????????? ?????? ");
        addAllByInterface(myLinkedList, myArrayList);
        printList(myLinkedList, myArrayList);

        System.out.println("\n\t5. toArray??? ?????? ?????? ?????? ");
        printMyListToArray(myArrayListToArray(myArrayList),
                            myLinkedListToArray(myLinkedList));

        System.out.println("\n\t6. ????????? ????????? ??? ????????? ?????? ");
        isMyListEmpty(myLinkedList, myArrayList);
        myLinkedList.clear();
        myArrayList.clear();
        printList(myLinkedList, myArrayList);
        isMyListEmpty(myLinkedList, myArrayList);

        System.out.println("\n\n\n");
    }
}