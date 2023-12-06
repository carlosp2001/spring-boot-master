package programming;

import java.util.List;

public class FP01Exercises {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
        printOddNumbersInListFunctional(numbers);

        List<String> courses =
                List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");

        courses.stream().forEach(System.out::println);

        courses.stream().filter(course -> course.contains("Spring")).forEach(System.out::println);

        courses.stream().filter(course -> course.length() >= 4).forEach(System.out::println);
    }

    private static void printOddNumbersInListFunctional(List<Integer> numbers) {
        // ¿Qué debemos hacer?
        numbers.stream()
                // Filter - Only Allow Even Numbers
                //.filter(FP01Functional::isEven) // Filter only allow even numbers
                .filter(number -> number % 2 != 0) // Lambda es una forma simple de definir un método
                .forEach(System.out::println); // Method reference
    }
}
