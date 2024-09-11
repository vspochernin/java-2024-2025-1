import java.util.Scanner;

public class EasyCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int firstOperand = readFirstOperand(scanner);
        String command = readCommand(scanner);
        int secondOperand = readSecondOperand(scanner);

        calculateAndPrintResult(firstOperand, secondOperand, command);
    }

    private static int readFirstOperand(Scanner scanner) {
        System.out.print("Введите первый операнд (целое число): ");
        return scanner.nextInt();
    }

    private static int readSecondOperand(Scanner scanner) {
        System.out.print("Введите второй операнд (целое число): ");
        return scanner.nextInt();
    }

    private static String readCommand(Scanner scanner) {
        System.out.print("Введите команду (+, -, *, /): ");
        return scanner.next();
    }

    private static void calculateAndPrintResult(int firstOperand, int secondOperand, String command) {
        switch (command) {
            case "+" -> System.out.println("Результат: " + (firstOperand + secondOperand));
            case "-" -> System.out.println("Результат: " + (firstOperand - secondOperand));
            case "*" -> System.out.println("Результат: " + (firstOperand * secondOperand));
            case "/" -> System.out.println("Результат: " + (firstOperand / secondOperand));
            default -> System.out.println("Введена некорректная команда.");
        }
    }
}
