package programming;

import java.util.List;

public class FP01Functional {
    public static void main(String[] args) {
        printAllNumbersInListFunctional(List.of(12, 9, 13, 4, 6, 2, 4, 12, 15));
    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

    private static void print(int number) {
        System.out.println(number);
    }

    private static void printAllNumbersInListFunctional(List<Integer> numbers) {
        // ¿Qué debemos hacer?
        numbers.stream()
                // Filter - Only Allow Even Numbers
                //.filter(FP01Functional::isEven) // Filter only allow even numbers
                .filter(number -> number % 2 == 0) // Lambda es una forma simple de definir un método
                .forEach(System.out::println); // Method reference
    }
}
