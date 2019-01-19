package sle;

import sle.matrix.SquareMatrix;
import sle.matrixfactories.SquareMatrixFactory;
import sle.matrixfactories.TriDiagonalMatrixWithAdditionalRowAndColumnOnKFactory;
import sle.test.TestManager;
import sle.test.TestResult;
import sle.vector.Vector;
import sle.vectorfactories.VectorFactory;
import sle.vectorfactories.VectorFactoryDefaultImp;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static public void main(String args[]) {
        try {
            mainMenu();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Запрос ответа у пользователя
     * @param msg сообщение пользователю
     * @return истину если ответ "да"
     */
    static private boolean inputQuery(String msg) {
        System.out.println(msg);
        Scanner scanner = new Scanner(System.in);
         String ans;
        do {
            System.out.println("(N/Y)?");
            ans = scanner.next();
        } while (!ans.equals("N") && !ans.equals("n") && !ans.equals("Y") && !ans.equals("y"));
        return !ans.equals("N") && !ans.equals("n");
    }

    /**
     * Главное меню
     */
    static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        for (;;) {
            int ans;
            do {
                System.out.println("Выберите режим работы:");
                System.out.println("1 - обычный режим");
                System.out.println("2 - режим тестрирования");
                System.out.println("0 - выход");
                ans = scanner.nextInt();
            } while (ans < 0 || ans > 2);
            VectorFactory vectorFactory = new VectorFactoryDefaultImp();
            SquareMatrixFactory squareMatrixFactory = new TriDiagonalMatrixWithAdditionalRowAndColumnOnKFactory(vectorFactory);
            switch (ans) {
                case 0:
                    return;

                case 1:
                    commonMode(vectorFactory, squareMatrixFactory);
                    break;

                case 2:
                    TestManager testManager = new TestManager(vectorFactory, squareMatrixFactory);
                    testMode(testManager);
                    break;
            }
        }
    }

    /**
     * Печатает результаты тестирования в виду таблицы
     * @param list списко результатов
     */
    static void printTestResultList(List<TestResult> list) {
        System.out.printf("|%-15s|%-15s|%-15s|%-15s|", "Порядок", "Диапазон", "Погрешность", "Оценка точности");
        System.out.println();
        for (TestResult test : list) {
            System.out.printf(
                    "|%15s|%15s|%15f|%15f|",
                    "10^" + test.getSystemSizePow(),
                    "(-10^" + test.getValuesSizePow() + "; 10^" + test.getValuesSizePow() + ")",
                    test.getAvgError(),
                    test.getAvgAccuracyRating());
            System.out.println();
        }
    }

    /**
     * Печатает результат тестирования
     * @param testResult результат тестирования
     */
    static void printTestResult(TestResult testResult) {
        System.out.printf("%-25s %s" ,"Порядок системы:", "10^" + testResult.getSystemSizePow());
        System.out.println();
        System.out.printf("%-25s %s" ,"Диапазон:", "(-10^" + testResult.getValuesSizePow() + "; 10^" + testResult.getValuesSizePow() + ")");
        System.out.println();
        System.out.printf("%-25s %-10f" ,"Оценка точности:", testResult.getAvgAccuracyRating());
        System.out.println();
        System.out.printf("%-25s %-10f" ,"Погрешнеость:", testResult.getAvgError());
        System.out.println();
        System.out.printf("%-25s %-6d" ,"Кол-во опытов:", testResult.getCountExperiments());
        System.out.println();
    }

    /**
     * Режим тестрирования
     * @param testManager менеджер тестов
     */
    static private void testMode(TestManager testManager) {
        List<TestResult> list = new LinkedList<>();
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите порядок размерности системы (1, 2, 3, ...):");
            int systemPowSize = scanner.nextInt();
            System.out.println("Введите порядок диапазона значений (1, 2, 3, ...):");
            int valuesPowSize = scanner.nextInt();
            System.out.println("Введите кол-во экспериментов:");
            int countExp = scanner.nextInt();
            TestResult testResult = testManager.makeTest(systemPowSize, valuesPowSize, countExp);
            printTestResult(testResult);
            list.add(testResult);
        } while (inputQuery("Продолжить тестирование?"));
        printTestResultList(list);
    }

    /**
     * Обычный режим
     * @param vectorFactory фабрика векторов
     * @param squareMatrixFactory фабрика квадратных матриц
     */
    static private void commonMode(VectorFactory vectorFactory, SquareMatrixFactory squareMatrixFactory) {
        try {
            Scanner scanner = new Scanner(System.in);
            int size = 0;
            int ans = 0;
            System.out.println("Введите размер системы (не менее 2):");
            size = scanner.nextInt();
            if (size < 2) {
                System.out.println("Введен некорректный размер");
                return;
            }
            do {
                System.out.println("Выберите способ задания матрицы:");
                System.out.println("1 - ввести с консоли");
                System.out.println("2 - считать из файла");
                System.out.println("3 - заполнить случайными числами");
                System.out.println("0 - отмена");
                ans = scanner.nextInt();
            } while (ans < 0 || ans > 3);
            SquareMatrix matr = null;
            switch (ans) {
                case 0:
                    return;

                case 1:
                    matr = squareMatrixFactory.readFromConsole(size);
                    break;

                case 2:
                    String fileName;
                    System.out.println("Введите имя файла:");
                    fileName = scanner.next();
                    matr = squareMatrixFactory.readFromFile(fileName);
                    break;

                case 3:
                    float min;
                    float max;
                    System.out.println("Введите минимальное значение:");
                    min = scanner.nextFloat();
                    System.out.println("Введите максимальное значение:");
                    max = scanner.nextFloat();
                    if (min > max) {
                        System.out.println("Минимальное значение больше максимального");
                    }
                    matr = squareMatrixFactory.getRandomMatrix(size, min, max);
                    break;
            }
            do {
                System.out.println("Выберите способ задания вектора правой части:");
                System.out.println("1 - ввести с консоли");
                System.out.println("2 - считать из файла");
                System.out.println("3 - заполнить случайными числами");
                System.out.println("0 - отмена");
                ans = scanner.nextInt();
            } while (ans < 0 || ans > 3);
            Vector f = null;
            switch (ans) {
                case 0:
                    return;

                case 1:
                    System.out.println("Введите вектор f = (f0, f1, ..., fn-1)");
                    f = vectorFactory.readFromConsole(size);
                    break;

                case 2:
                    String fileName;
                    System.out.println("Введите имя файла:");
                    fileName = scanner.next();
                    f = vectorFactory.readFromFile(fileName);
                    break;

                case 3:
                    float min;
                    float max;
                    System.out.println("Введите минимальное значение:");
                    min = scanner.nextFloat();
                    System.out.println("Введите максимальное значение:");
                    max = scanner.nextFloat();
                    if (min > max) {
                        System.out.println("Минимальное значение больше максимального");
                    }
                    f = vectorFactory.getRandomVector(size, min, max);
                    break;
            }
            Vector result = matr.resolveLinearEquationSystem(f);
            System.out.println("Расширенная матрица:");
            matr.printToConsole(f);
            System.out.println("Ответ:");
            result.printToConsole();
            if (inputQuery("Записать ответ в файл?")) {
                String fileName;
                System.out.println("Введите имя файла:");
                fileName = scanner.next();
                result.writeToFile(fileName);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
