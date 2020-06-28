import deneme.leetcode.medium.MediumQuestions;

import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        /*ExecutorService service = Executors.newFixedThreadPool(3);
        SynchronizedTrial synchronizedTrial = new SynchronizedTrial(0);

        IntStream.range(0, 1000).forEach(count -> service.submit(synchronizedTrial::calculate));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        System.out.println(synchronizedTrial.getSum());*/


        //System.out.println(reverse(-123));

        int[] a = new int[]{1, 1, 3, 8, 1, 8, 8, 9, 3, 3};
        System.out.println(MediumQuestions.singleNumber(a));
    }

}
