package sle.test;

import sle.matrix.SquareMatrix;
import sle.matrixfactories.SquareMatrixFactory;
import sle.vector.Vector;
import sle.vector.VectorDefaultImp;
import sle.vectorfactories.VectorFactory;

import java.util.Random;

/**
 * Менеджер тестов
 */
public class TestManager {
    /**
     * Генератор случайных чисел
     */
    private Random random;

    /**
     * Фабрика векторов
     */
    private VectorFactory vectorFactory;

    /**
     * Фабрика квадратных матриц
     */
    private SquareMatrixFactory squareMatrixFactory;

    public TestManager(VectorFactory vecFac, SquareMatrixFactory sqmf) {
        random = new Random();
        vectorFactory = vecFac;
        squareMatrixFactory = sqmf;
    }

    /**
     * Возвращает погрешность между векторами
     * @param a 1-й вектор
     * @param b 2-й вектор
     * @return максимальную разницу компонент
     * @throws Exception
     */
    private float getError(Vector a, Vector b) throws Exception {
        return a.subtract(b).getNorm();
    }

    /**
     * Проводит одно тестирование
     * @param systemPowSize порядок размера системы
     * @param valuesSizePow порядок диапазона значений
     * @param countExp кол-во опытов
     * @return результат тестрирования
     */
    public TestResult makeTest(int systemPowSize, int valuesSizePow, int countExp) {
        try {
            int minSize = (int) Math.pow(10, systemPowSize);
            int maxSize = (int) (Math.pow(10, systemPowSize + 1) - 1);
            int systemSize = minSize + random.nextInt(maxSize - minSize);
            float[] one = new float[systemSize];
            for (int i = 0; i < systemSize; ++i) {
                one[i] = 1;
            }
            Vector oneVec = vectorFactory.createVector(one);
            float max = (float) Math.pow(10, valuesSizePow);
            float min = -max;
            float avgError = 0;
            float avgAccuracyRating = 0;
            for (int i = 0; i < countExp; ++i) {
                SquareMatrix matrix = squareMatrixFactory.getRandomMatrix(systemSize, min, max);
                Vector x = vectorFactory.getRandomVector(systemSize, min, max);
                Vector f = matrix.multiply(x);
                Vector _f = matrix.multiply(oneVec);

                Vector result = matrix.resolveLinearEquationSystem(f);
                Vector _result = matrix.resolveLinearEquationSystem(_f);

                avgError += getError(result, x);
                avgAccuracyRating += _result.subtract(oneVec).getNorm();
            }
            avgAccuracyRating /= countExp;
            avgError /= countExp;
            return new TestResult(systemPowSize, valuesSizePow, avgError, avgAccuracyRating, countExp);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
