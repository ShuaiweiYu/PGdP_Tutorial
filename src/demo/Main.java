package demo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] b = Arrays.stream(a).map(x -> x * x).toArray();
        int[] c = {0, 3, 9, 2, 4, 9, 7, 1, 45, 33};

        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(Arrays.stream(a).map(x -> x * x).filter(i -> i % 2 == 0).toArray()));
        System.out.println(Arrays.stream(a).average().orElse(0));
        System.out.println(Arrays.stream(a).count());
        System.out.println(Arrays.toString(Arrays.stream(a).mapToDouble(x -> x * 1.0).map(x -> x * 1.5).mapToInt(x -> (int) x).toArray()));
        System.out.println(Arrays.toString(Arrays.stream(c).distinct().toArray()));
        System.out.println(Arrays.toString(Arrays.stream(a).limit(5).toArray()));
        System.out.println(Arrays.toString(Arrays.stream(a).skip(5).toArray()));
        System.out.println(Arrays.toString(Arrays.stream(c).sorted().toArray()));

        System.out.println(Arrays.stream(a).findFirst().getAsInt());
        System.out.println(Arrays.stream(a).allMatch(x -> x % 2 == 0));
        System.out.println(Arrays.stream(a).anyMatch(x -> x % 2 == 0));
        System.out.println(Arrays.stream(a).noneMatch(x -> x % 2 == 0));
    }

}
