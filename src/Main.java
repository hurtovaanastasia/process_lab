import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<ProcessInfo> processes = new ArrayList<>();

        try {
            System.out.print("Хотите запустить процесс?? :) : ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("да")) {
                System.out.print("Сколько процессов запустить(от 1 до 3)? :) : ");
                int num = Integer.parseInt(scanner.nextLine());

                for (int i = 0; i < num; i++) {
                    System.out.print("Процесс №" + (i + 1) + ": Какой процесс вы хотите запустить(напишите название на английском)? :) : ");
                    String name = scanner.nextLine().trim();


                    if (name.isEmpty()) {
                        System.out.println("Ошибка: вы ничего не ввели :(");
                        return;
                    }
                        Process proc = new ProcessBuilder(name).start();
                        ProcessInfo processInfo = new ProcessInfo(name, proc);
                        processes.add(processInfo);
                        System.out.println("Процесс " + name + " запущен. PID: " + proc.pid());
                        System.out.print("Хотите завершить процесс? (д/н): ");
                        String choice = scanner.nextLine();

                        if (choice.equalsIgnoreCase("д")) {
                            proc.destroy();
                            System.out.println("Процесс " + name + " завершён.");
                            processes.remove(processInfo); // Удаляем из списка если завершили
                        } else if (choice.equalsIgnoreCase("н")) {
                        System.out.println("Процесс " + name + " продолжает работать.");
                        } else {
                        System.out.println("Ошибка: введите н или д!!!");
                    }
                }

                if (!processes.isEmpty()) {
                    boolean exit = false;

                    while (!exit) {
                        System.out.println("Процессы запущены! Как поступим дальше??:)");
                        System.out.println("1. Вывести PID всех процессов");
                        System.out.println("2. Завершить все процессы");
                        System.out.print("Выберите действие: ");

                        String choice = scanner.nextLine();

                        switch (choice) {
                            case "1":
                                printAllPids(processes);
                                break;

                            case "2":
                                terminateAllProcesses(processes);
                                exit = true;
                                break;

                            default:
                                System.out.println("Неверный выбор! Попробуйте снова.");
                        }
                    }
                } else {
                    System.out.println("Не было запущено ни одного процесса.");
                }

            } else if (answer.equalsIgnoreCase("нет")) {
                System.out.println("Ну нет, так нет");
            } else {
                System.out.println("Не знаем таких.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите корректное число!");
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void printAllPids(List<ProcessInfo> processes) {
        System.out.println("Список всех процессов:");
        for (int i = 0; i < processes.size(); i++) {
            ProcessInfo info = processes.get(i);
            System.out.println((i + 1) + ". " + info.getName() + " - PID: " + info.getProcess().pid());
        }
    }

    private static void terminateAllProcesses(List<ProcessInfo> processes) {
        System.out.println("Завершение всех процессов...");
        for (ProcessInfo info : processes) {
            try {
                info.getProcess().destroy();
                System.out.println("Процесс " + info.getName() + " (PID: " + info.getProcess().pid() + ") завершён.");
            } catch (Exception e) {
                System.out.println("Ошибка при завершении процесса " + info.getName() + ": " + e.getMessage());
            }
        }
        processes.clear();
    }

    static class ProcessInfo {
        private final String name;
        private final Process process;

        public ProcessInfo(String name, Process process) {
            this.name = name;
            this.process = process;
        }

        public String getName() {
            return name;
        }

        public Process getProcess() {
            return process;
        }
    }
}