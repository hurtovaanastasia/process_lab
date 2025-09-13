import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner inputi = new Scanner(System.in);
        Scanner inputs = new Scanner(System.in);
        Scanner inputc = new Scanner(System.in);

        try {
            System.out.print("Хотите запустить процесс??:) : ");
            String answer = inputi.nextLine();
            if(answer.equalsIgnoreCase("да")) {
                System.out.print("Сколько процессов запустить?:) : ");
                int num = inputi.nextInt();
                for (int i = 0; i < num; i++) {
                    System.out.print("Процесс №" + (i + 1) + ": Какой процесс вы хотите запустить?:) : ");
                    String name = inputs.nextLine().trim();
                    if (name.isEmpty()) {
                        System.out.println("Ошибка: вы ничего не ввели:( ");
                        return;
                    }

                    Process proc = new ProcessBuilder(name).start();
                    System.out.println("Процесс " + name + " запущен. PID: " + proc.pid());

                    System.out.print("Хотите завершить процесс? (д/н): ");
                    String choice = inputc.nextLine();

                    if (choice.equalsIgnoreCase("д")) {
                        proc.destroy();
                        System.out.println("Процесс " + name + " завершён.");
                    } else if (choice.equalsIgnoreCase("н")) {
                        System.out.println("Процесс " + name + " продолжает работать.");
                    } else {
                        System.out.println("Ошибка: введите н или д!!!");
                    }
                }
            }else if(answer.equalsIgnoreCase("нет")){
                System.out.println("Ну нет, так нет");
            }else {
                System.out.println("Не знаем таких.");
            }
        } catch (IOException e) {
            System.out.println("Ошибка запуска: " + e.getMessage());
        } finally {
            inputi.close();
        }
    }
}