package programming;

import java.util.List;

public class FP01Structured {
    public static void main(String[] args) {
        printAllNumbersInListStructure(List.of(12, 9, 13, 4, 6, 2, 4, 12, 15));
    }

    private static void printAllNumbersInListStructure(List<Integer> numbers) {
        // ¿Como hacer loops en los números?
        for (int number: numbers) {
            System.out.println(number);
        }
    }
}
