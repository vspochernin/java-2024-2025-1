import java.util.Locale;
import java.util.Scanner;

public class EasyCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US); // Enable "." double separator.

        double firstOperand = readFirstOperand(scanner);
        String operation = operation(scanner);
        double secondOperand = readSecondOperand(scanner);

        calculateAndPrintResult(firstOperand, operation, secondOperand);
    }

    private static double readFirstOperand(Scanner scanner) {
        System.out.print("Enter first operand: ");
        return scanner.nextDouble();
    }

    private static String operation(Scanner scanner) {
        System.out.print("Enter operation (+, -, *, /): ");
        return scanner.next();
    }

    private static double readSecondOperand(Scanner scanner) {
        System.out.print("Enter second operand: ");
        return scanner.nextDouble();
    }

    private static void calculateAndPrintResult(double firstOperand, String operation, double secondOperand) {
        switch (operation) {
            case "+" -> System.out.println("Result: " + (firstOperand + secondOperand));
            case "-" -> System.out.println("Result: " + (firstOperand - secondOperand));
            case "*" -> System.out.println("Result: " + (firstOperand * secondOperand));
            case "/" -> System.out.println("Result: " + (firstOperand / secondOperand));
            default -> System.out.println("There is invalid operation.");
        }
    }
}
