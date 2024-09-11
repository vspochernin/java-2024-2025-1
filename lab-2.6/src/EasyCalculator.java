import java.util.Scanner;

/**
 * @author pochernin-vla
 */
public class EasyCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int firstOperand = readFirstOperand(scanner);
        int secondOperand = readSecondOperand(scanner);
        String command = readCommand(scanner);

        calculateAndPrintResult(firstOperand, secondOperand, command);
    }

    private static int readFirstOperand(Scanner scanner) {
        System.out.print("Введите первый операнд: ");
        return scanner.nextInt();
    }

    private static int readSecondOperand(Scanner scanner) {
        System.out.print("Введите второй операнд: ");
        return scanner.nextInt();
    }

    private static String readCommand(Scanner scanner) {
        System.out.print("Введите команду (+, -, *, /): ");
        return scanner.next();
    }

    private static void calculateAndPrintResult(int firstOperand, int secondOperand, String command) {
        switch (command) {
            case "+" -> System.out.println(firstOperand + secondOperand);
            case "-" -> System.out.println(firstOperand - secondOperand);
            case "*" -> System.out.println(firstOperand * secondOperand);
            case "/" -> System.out.println(firstOperand / secondOperand);
            default -> System.out.println("Введена некорректная команда.");
        }
    }
}
