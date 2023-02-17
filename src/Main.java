public class Main {
    public static void main(String[] args) {

        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        myLinkedList.add("JDM");
        myLinkedList.add("STS");
        myLinkedList.add("KBH");
        myLinkedList.add("STW");
        myLinkedList.add("CCS");
        myLinkedList.add("KHJ");
        myLinkedList.add("CSY");
        myLinkedList.add("LHN");
        myLinkedList.add("YHY");
        myLinkedList.add("YDH");
        myLinkedList.add("JHJ");
        myLinkedList.add("SJS");
        myLinkedList.add("LDJ");
        myLinkedList.add("PC");
        myLinkedList.add("LYG");
        myLinkedList.add("LJH");
        myLinkedList.add("KSK");

        for(int i = 0; i < myLinkedList.size() ; i++){
            System.out.println(i + " : " + myLinkedList.get(i));
        }
        myLinkedList.add(5,"KJH");

        System.out.println("-------------------");
        myLinkedList.remove("LHN");
        myLinkedList.remove(7);

        myLinkedList.add("KEJ");

        MyArrayList<String> myArrayList= new MyArrayList<>();
        myArrayList.add("JWS");
        myArrayList.add("LMH");
        myArrayList.add("SHC");
        myArrayList.add("SHP");
        myArrayList.add("CDO");

        myLinkedList.addAll(myArrayList);

        for(int i = 0; i < myLinkedList.size() ; i++){
            System.out.println(i + " : " + myLinkedList.get(i));
        }
        myArrayList.addAll(myLinkedList);

        String[] strings = new String[myArrayList.size()];
        strings = myArrayList.toArray(strings);
        for(int i = 0 ; i < strings.length ; i++){
            System.out.println(strings[i]);
        }

        myLinkedList.iterator().forEachRemaining(System.out::println);
        System.out.println("**");
        myArrayList.iterator().forEachRemaining(System.out::println);

        myLinkedList.clear();
        myArrayList.clear();
        System.out.println("----");
        myLinkedList.iterator().forEachRemaining(System.out::println);
        myArrayList.iterator().forEachRemaining(System.out::println);

    }
}