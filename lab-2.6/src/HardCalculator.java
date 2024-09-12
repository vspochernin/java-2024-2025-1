import java.util.Locale;
import java.util.Scanner;

public class HardCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US); // Enable "." double separator.

        double firstOperand = 0;
        String operation = "C";
        double secondOperand;

        while (true) {
            if (operation.equals("C")) {
                firstOperand = readFirstOperand(scanner);
            }

            operation = readOperation(scanner);
            if (operation.equals("C")) {
                continue;
            }
            if (operation.equals("s")) {
                System.out.println("Program executed successfully.");
                break;
            }

            secondOperand = readSecondOperand(scanner);
            firstOperand = calculate(firstOperand, operation, secondOperand);

            printResult(firstOperand);
        }
    }

    private static double readFirstOperand(Scanner scanner) {
        System.out.print("Enter first operand: ");
        return scanner.nextDouble();
    }

    private static String readOperation(Scanner scanner) {
        String operation;
        do {
            System.out.print("Enter operation (+, -, *, /, C, s): ");
            operation = scanner.next();
        } while (!isValidOperation(operation));
        return operation;
    }

    private static boolean isValidOperation(String operation) {
        return switch (operation) {
            case "+", "-", "*", "/", "C", "s" -> true;
            default -> {
                System.out.println("There is invalid operation.");
                yield false;
            }
        };
    }

    private static double readSecondOperand(Scanner scanner) {
        System.out.print("Enter second operand: ");
        return scanner.nextDouble();
    }

    private static double calculate(double firstOperand, String operation, double secondOperand) {
        return switch (operation) {
            case "+" -> firstOperand + secondOperand;
            case "-" -> firstOperand - secondOperand;
            case "*" -> firstOperand * secondOperand;
            case "/" -> firstOperand / secondOperand;
            default -> throw new IllegalStateException("Unexpected operation: " + operation);
        };
    }

    private static void printResult(double result) {
        System.out.println("Result: " + result);
    }
}
