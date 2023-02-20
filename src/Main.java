import java.util.Arrays;
import java.util.Iterator;

public class Main {

    //리스트 전체를 콘솔에 출력하는 인쇄하는 메소드 작성
    public static void printList(MyLinkedList myLinkedList, MyArrayList myArrayList){
        System.out.println("------------MyLinkedList-----------");
        for(int i = 0; i < myLinkedList.size() ; i++){
            System.out.println(i + " : " + myLinkedList.get(i));
        }

        System.out.println("\n------------MyArrayList-----------");
        Iterator iterator = myArrayList.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            System.out.println(index + " : " + iterator.next());
            index++;
        }

        System.out.println("\n********* List Finished *********\n");
    }

    public static void main(String[] args) {

        //리스트에 원소들 추가
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
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

        MyArrayList<String> myArrayList= new MyArrayList<>();
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

        System.out.println("\n\t1. 기본 리스트 출력 ");
                        printList(myLinkedList, myArrayList);


        //리스트에 원소 삽입 2번째
        myLinkedList.add(0, "The First SLAMDUNK");
        myLinkedList.add(5,"KJH");

        myArrayList.add(0, "BasketBall");
        myArrayList.add(1, "BS");
        myArrayList.add(2, "SW");

        System.out.println("\n\t2. add(index) 리스트 출력 ");
                    printList(myLinkedList, myArrayList);


        //리스트에 원소 삭제
        myLinkedList.remove("LHN");
        myLinkedList.remove(7);


        myArrayList.remove("KEJ");
        myArrayList.remove(11);


        System.out.println("\n\t3. 삭제 후 리스트 출력 ");
                    printList(myLinkedList, myArrayList);
                    System.out.println("방금 지운 LHN의 값이 있나요?: " + myLinkedList.contains("LHN"));
                    System.out.println("방금 지운 index 7(CSY)의 값이 있나요?: " + myLinkedList.contains("CSY"));
                    System.out.println("방금 지운 KEJ의 값이 있나요?: " + myArrayList.contains("KEJ"));
                    System.out.println("방금 지운 index 11의 값(AHS)이 있나요?: " + myArrayList.contains("AHS") +"\n");



        //AddAll method test
        myLinkedList.addAll(myArrayList);
        myArrayList.addAll(myLinkedList);

        System.out.println("\n\t4. addAll 후 리스트 출력 ");
                    printList(myLinkedList, myArrayList);



        //ToArray method test
        String[] arrArrayList = new String[myArrayList.size()];
        arrArrayList = myArrayList.toArray(arrArrayList);

        String[] arrLinkedList = new String[3];
        arrLinkedList = myLinkedList.toArray(arrLinkedList);

        System.out.println("\n\t5. toArray로 만든 배열 출력 ");
                    System.out.println("~~~~~~~~~ arrArrayList ~~~~~~~~~");
                    Arrays.stream(arrArrayList).iterator().forEachRemaining(System.out :: println);

                    System.out.println("\n~~~~~~~~~ arrLinkedList ~~~~~~~~~");
                    Arrays.stream(arrLinkedList).iterator().forEachRemaining(System.out :: println);



        //List clearing, empty method test
        System.out.println("\n\nmyLinkedList가 비어 있나요? empty() : " + myLinkedList.empty());
        System.out.println("myArrayList가 비어 있나요? empty() : " + myArrayList.empty());

        myLinkedList.clear();
        myArrayList.clear();

        System.out.println("\n\t6. 리스트 클리어 후 리스트 출력 ");
                    printList(myLinkedList, myArrayList);

        System.out.println("myLinkedList가 비어 있나요? empty() : " + myLinkedList.empty());
        System.out.println("myArrayList가 비어 있나요? empty() : " + myArrayList.empty());
        System.out.println("\n\n\n");



    }
}