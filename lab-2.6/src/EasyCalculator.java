import java.util.Scanner;

public class EasyCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int firstOperand = readFirstOperand(scanner);
        String operation = operation(scanner);
        int secondOperand = readSecondOperand(scanner);

        calculateAndPrintResult(firstOperand, operation, secondOperand);
    }

    private static int readFirstOperand(Scanner scanner) {
        System.out.print("Enter first operant (integer): ");
        return scanner.nextInt();
    }

    private static String operation(Scanner scanner) {
        System.out.print("Enter operation (+, -, *, /): ");
        return scanner.next();
    }

    private static int readSecondOperand(Scanner scanner) {
        System.out.print("Введите второй операнд (целое число): ");
        return scanner.nextInt();
    }

    private static void calculateAndPrintResult(int firstOperand, String operation, int secondOperand) {
        switch (operation) {
            case "+" -> System.out.println("Результат: " + (firstOperand + secondOperand));
            case "-" -> System.out.println("Результат: " + (firstOperand - secondOperand));
            case "*" -> System.out.println("Результат: " + (firstOperand * secondOperand));
            case "/" -> System.out.println("Результат: " + (firstOperand / secondOperand));
            default -> System.out.println("There is invalid operation.");
        }
    }
}
