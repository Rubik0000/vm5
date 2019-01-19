package sle;

import sle.matrix.SquareMatrix;
import sle.matrix.TriDiagonalMatrixWithAdditionalRowAndColumnOnK;
import sle.matrixfactories.SquareMatrixFactory;
import sle.matrixfactories.TriDiagonalMatrixWithAdditionalRowAndColumnOnKFactory;
import sle.test.TestManager;
import sle.test.TestResult;
import sle.vector.Vector;
import sle.vector.VectorDefaultImp;
import sle.vectorfactories.VectorFactory;
import sle.vectorfactories.VectorFactoryDefaulImp;

import java.util.Scanner;

public class Main {
    static public void main(String args[]) {
        try {

            /*Vector a = new VectorDefaultImp(4, 7, 11, 15);
            Vector b = new VectorDefaultImp(1, 5, 8, 12, 16);
            Vector c = new VectorDefaultImp(2, 6, 9, 13);
            Vector p = new VectorDefaultImp(3, 6, 8, 11, 14);
            Vector q = new VectorDefaultImp(10, 7, 8, 9, 20);
            SquareMatrix matrix = new TriDiagonalMatrixWithAdditionalRowAndColumnOnK(a, b, c, p, q, 2);
            Vector x = new VectorDefaultImp(1, 2, 3, 4, 5);
            Vector f = matrix.multiply(x);
            f.printToConsole();
            System.out.println();
            Vector result = matrix.resolveLinearEquationSystem(f);
            result.printToConsole();
            matrix.printToConsole();*/

            VectorFactory vectorFactory = new VectorFactoryDefaulImp();
            SquareMatrixFactory squareMatrixFactory = new TriDiagonalMatrixWithAdditionalRowAndColumnOnKFactory(vectorFactory);

            /*TestManager testManager = new TestManager(vectorFactory, squareMatrixFactory);
            TestResult r = testManager.makeTest(100, -100, 100, 100);
            int rr = 0;*/

            CommonMode(vectorFactory, squareMatrixFactory);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    static private void CommonMode(VectorFactory vectorFactory, SquareMatrixFactory squareMatrixFactory) {
        try {

            int size = 0;
            int ans = 0;
            try (Scanner scanner = new Scanner(System.in)) {
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
            }
            SquareMatrix matr = null;
            switch (ans) {
                case 0:
                    return;

                case 1:
                    matr = squareMatrixFactory.readFromConsole(size);
                    break;

                case 2:
                    String fileName;
                    try (Scanner scanner = new Scanner(System.in)) {
                        System.out.println("Введите имя файла:");
                        fileName = scanner.nextLine();
                    }
                    matr = squareMatrixFactory.readFromFile(fileName);
                    break;

                case 3:
                    float min;
                    float max;
                    try (Scanner scanner = new Scanner(System.in)) {
                        System.out.println("Введите минимальное значение:");
                        min = scanner.nextFloat();
                        System.out.println("Введите максимальное значение:");
                        max = scanner.nextFloat();
                    }
                    if (min > max) {
                        System.out.println("Минимальное значение больше максимального");
                    }
                    matr = squareMatrixFactory.getRandomMatrix(size, min, max);
                    break;
            }
            try (Scanner scanner = new Scanner(System.in)) {
                do {
                    System.out.println("Выберите способ задания вектора правой части:");
                    System.out.println("1 - ввести с консоли");
                    System.out.println("2 - считать из файла");
                    System.out.println("3 - заполнить случайными числами");
                    System.out.println("0 - отмена");
                    ans = scanner.nextInt();
                } while (ans < 0 || ans > 3);
            }
            Vector f = null;
            switch (ans) {
                case 0:
                    return;

                case 1:
                    f = vectorFactory.readFromConsole(size);
                    break;

                case 2:
                    String fileName;
                    try (Scanner scanner = new Scanner(System.in)) {
                        System.out.println("Введите имя файла:");
                        fileName = scanner.nextLine();
                    }
                    f = vectorFactory.readFromFile(fileName);
                    break;

                case 3:
                    float min;
                    float max;
                    try (Scanner scanner = new Scanner(System.in)) {
                        System.out.println("Введите минимальное значение:");
                        min = scanner.nextFloat();
                        System.out.println("Введите максимальное значение:");
                        max = scanner.nextFloat();
                    }
                    if (min > max) {
                        System.out.println("Минимальное значение больше максимального");
                    }
                    f = vectorFactory.getRandomVector(size, min, max);
                    break;
            }
            Vector result = matr.resolveLinearEquationSystem(f);
            result.printToConsole();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
