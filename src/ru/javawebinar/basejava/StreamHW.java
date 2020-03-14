package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

 /*
1) Реализовать метод через стрим int minValue(int[] values).
Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число,
составленное из этих уникальных цифр. Не использовать преобразование в строку и обратно.
Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89

2) Реализовать метод List<Integer> oddOrEven(List<Integer> integers)
если сумма всех чисел нечетная - удалить все нечетные, если четная - удалить все четные.
Сложность алгоритма должна быть O(N). Optional - решение в один стрим.
 */

public class StreamHW {
    public static void main(String[] args) {

        int[] array = new int[]{1, 2, 3, 4, 5, 6, 5, 4, 5, 6, 7, 8, 9};
        System.out.println(minValue(array));

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(oddOrEven(integers));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce((s1, s2) -> (s1 * 10 + s2))
                .orElse(0);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream().reduce(0, Integer::sum) % 2 == 0
                ? (integers.stream().filter(i -> i % 2 == 0).collect(Collectors.toList()))
                : (integers.stream().filter(i -> i % 2 != 0).collect(Collectors.toList()));
    }
}