package sle.matrixfactories;

import sle.matrix.SquareMatrix;
import sle.matrix.TriDiagonalMatrixWithAdditionalRowAndColumnOnK;
import sle.vector.Vector;
import sle.vectorfactories.VectorFactory;

import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

/**
 * Фабрика трехдиагональных матриц с вектором p на k-м столбце и вектором q на k-й строке
 */
public class TriDiagonalMatrixWithAdditionalRowAndColumnOnKFactory implements SquareMatrixFactory {
    /**
     * Генератор случайных чисел
     */
    private Random random;

    /**
     * Фабрика векторов
     */
    private VectorFactory _vectorFactory;

    /**
     * Конструктор
     * @param factory фабрика векторов
     */
    public TriDiagonalMatrixWithAdditionalRowAndColumnOnKFactory(VectorFactory factory) {
        random = new Random();
        _vectorFactory = factory;
    }

    @Override
    public SquareMatrix readFromConsole(int size) throws Exception {
        if (size < 2) {
            throw new Exception("Размер матрицы должен быть не менее 2");
        }
        System.out.println("Введите вектор a, начиная с a1, a = (a0, a1, ..., an-1), a0 = 0 ");
        Vector a = _vectorFactory.readFromConsole(size - 1);
        System.out.println("Введите вектор b, начиная b = (b0, b1, ..., bn-1)");
        Vector b = _vectorFactory.readFromConsole(size);
        System.out.println("Введите вектор a, до cn, c = (c0, c1, ..., cn-1), cn-1 = 0 ");
        Vector c = _vectorFactory.readFromConsole(size - 1);
        System.out.println("Введите позицию векторов p и q, отсчет начинается с 0");
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        System.out.println("Введите вектор p, начиная p = (p0, p1, ..., pn-1)");
        Vector p = _vectorFactory.readFromConsole(size);
        System.out.println("Введите вектор q, начиная q = (q0, q1, ..., qn-1)");
        Vector q = _vectorFactory.readFromConsole(size);
        return new TriDiagonalMatrixWithAdditionalRowAndColumnOnK(a, b, c, p, q, k);
    }

    @Override
    public SquareMatrix readFromFile(String fileName) throws Exception {
        if (fileName == null || fileName.equals("")) {
            throw new Exception("Имя файла не может быть пустым");
        }
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            if (!scanner.hasNextInt()) {
                throw new Exception("Неверный формат файла");
            }
            int size = scanner.nextInt();
            float[][] matrix = new float[size][size];
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    if (!scanner.hasNextFloat()) {
                        throw new Exception("Невозможно считать " + i + "-й " + j + "-й компонент матрицы");
                    }
                    matrix[i][j] = scanner.nextFloat();
                }
            }
            return null;
        }
    }

    @Override
    public SquareMatrix getRandomMatrix(int size, float min, float max) throws Exception {
        if (size < 2) {
            throw new Exception("Размер матрицы должен быть не менее 2");
        }
        if (min > max) {
            throw new Exception("Переданное минимальное значение больше максимального");
        }
        int k = random.nextInt(size);
        Vector a = _vectorFactory.getRandomVector(size - 1, min, max);
        Vector b = _vectorFactory.getRandomVector(size, min, max, true);
        Vector c = _vectorFactory.getRandomVector(size - 1, min, max);
        float[] pVec = new float[size];
        float[] qVec = new float[size];
        for (int i = 0; i < size; ++i) {
            if (i < k - 1 || i > k + 1) {
                pVec[i] = min + random.nextFloat() * (max - min);
                qVec[i] = min + random.nextFloat() * (max - min);
            }
        }
        if (k > 0) {
            pVec[k - 1] = c.get(k - 1);
        }
        pVec[k] = b.get(k);
        if (k < size - 1) {
            pVec[k + 1] = a.get(k);
        }

        if (k > 0) {
            qVec[k - 1] = a.get(k - 1);
        }
        qVec[k] = b.get(k);
        if (k < size - 1) {
            qVec[k + 1] = c.get(k);
        }

        Vector p = _vectorFactory.createVector(pVec);
        Vector q = _vectorFactory.createVector(qVec);
        return new TriDiagonalMatrixWithAdditionalRowAndColumnOnK(a, b, c, p, q, k);
    }
}
