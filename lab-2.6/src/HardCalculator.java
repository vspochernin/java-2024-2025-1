import java.util.Scanner;

public class HardCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int firstOperand = 0;
        String operation = "C";
        int secondOperand;

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

    private static int readFirstOperand(Scanner scanner) {
        System.out.print("Enter first operand (integer): ");
        return scanner.nextInt();
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

    private static int readSecondOperand(Scanner scanner) {
        System.out.print("Enter second operand (integer): ");
        return scanner.nextInt();
    }

    private static int calculate(int firstOperand, String operation, int secondOperand) {
        return switch (operation) {
            case "+" -> firstOperand + secondOperand;
            case "-" -> firstOperand - secondOperand;
            case "*" -> firstOperand * secondOperand;
            case "/" -> firstOperand / secondOperand;
            default -> throw new IllegalStateException("Unexpected operation: " + operation);
        };
    }

    private static void printResult(int result) {
        System.out.println("Result: " + result);
    }
}
