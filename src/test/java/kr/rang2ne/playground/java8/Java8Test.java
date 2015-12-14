package kr.rang2ne.playground.java8;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by gswon on 15. 12. 14.
 */
public class Java8Test {

    @Test
    public void lambdaExpr_1() {
        String[] strings1 = {"abc", "bc", "a", "aaaa", "12341234"};
        Arrays.sort(strings1, new LengthComparator());
        printArray(strings1);

        String[] strings2 = {"abc", "bc", "a", "aaaa", "12341234"};
        Arrays.sort(strings2, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
        printArray(strings2);

        String[] strings3 = {"abc", "bc", "a", "aaaa", "12341234"};
        Arrays.sort(strings3, (o1, o2) -> Integer.compare(o1.length(), o2.length()));
        printArray(strings3);
    }

    private void printArray(String[] arrays) {
        Lists.newArrayList(arrays).forEach(System.out::print);
        System.out.println("===========================");
    }
}

class LengthComparator implements Comparator<String> {

    @Override
    public int compare(String o, String b) {
        return Integer.compare(o.length(), b.length());
    }
}
