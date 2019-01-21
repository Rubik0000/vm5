package sle.test;

import sle.matrix.SquareMatrix;
import sle.matrixfactories.SquareMatrixFactory;
import sle.vector.Vector;
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

    /**
     * Конструктор
     * @param vecFac фабрика векторов
     * @param sqmf фабрика матриц
     */
    public TestManager(VectorFactory vecFac, SquareMatrixFactory sqmf) {
        random = new Random();
        vectorFactory = vecFac;
        squareMatrixFactory = sqmf;
    }

    /**
     * Возвращает погрешность между векторами
     * @param a 1-й вектор
     * @param b 2-й вектор
     * @param q некоторое неотрицательное число, выбираемое с учетом особенностей
     * решаемой системы уравнений
     * @return максимальную разницу компонент
     * @throws Exception если векторы разной длины или q меньше 0
     */
    private float getError(Vector a, Vector b, float q) throws Exception {
        if (a.length() != b.length()) {
            throw new Exception("Ошибка в вычислении погрешности, переданы векторы разной длины");
        }
        if (q < 0) {
            throw new Exception("Число q должно быть неотрицательным");
        }
        int len = a.length();
        float[] errorVec = new float[len];
        for (int i = 0; i < len; ++i) {
            if (Math.abs(b.get(i)) > q) {
                errorVec[i] = Math.abs((a.get(i) - b.get(i)) / b.get(i));
            }
            else {
                errorVec[i] = Math.abs(a.get(i) - b.get(i));
            }
        }
        return vectorFactory.createVector(errorVec).getNorm();
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

                float q = Float.MAX_VALUE;
                avgError += getError(result, x, q);
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
