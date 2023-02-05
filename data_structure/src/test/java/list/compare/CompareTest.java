package list.compare;

import list.arraylist.implement.ArrayList;
import list.linkedlist.implementation.LinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

public class CompareTest {

    ArrayList arrayList = new ArrayList();
    LinkedList linkedList = new LinkedList();

    @DisplayName("arrayList와 linkedList addFirst() 수행속도 비교 : stopWatch")
    @Test
    void test() throws Exception {
        for (int i = 0; i < 99; i++) {
            arrayList.addLast(i);
            linkedList.addFirst(i);
        }
        StopWatch stopWatch = new StopWatch();

        stopWatch.start("ArrayList");
        arrayList.addFirst(0);
        stopWatch.stop();

        stopWatch.start("LinkedList");
        linkedList.addFirst(0);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());

        /*
            ---------------------------------------------
            ns         %     Task name
            ---------------------------------------------
            000003959  089%  ArrayList
            000000500  011%  LinkedList
        */
    }

    @DisplayName("arrayList와 linkedList addFirst() 수행속도 비교 : nanoTime")
    @Test
    void test2() throws Exception {
        for (int i = 0; i < 99; i++) {
            arrayList.addLast(i);
            linkedList.addFirst(i);
        }

        long start = System.nanoTime();
        arrayList.addFirst(0);
        long end = System.nanoTime();
        long time1 = end - start;

        long start2 = System.nanoTime();
        linkedList.addFirst(0);
        long end2 = System.nanoTime();
        long time2 = end2 - start2;

        System.out.println("ArrayList  = " + time1 + "\n" + "LinkedList = " + time2);
        /*
            ArrayList  = 3292
            LinkedList = 250
        */
    }

}
