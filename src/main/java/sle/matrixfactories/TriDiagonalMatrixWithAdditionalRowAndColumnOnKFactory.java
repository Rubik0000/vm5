package sle.matrixfactories;

import sle.matrix.SquareMatrix;
import sle.matrix.TriDiagonalMatrixWithAdditionalRowAndColumnOnK;
import sle.vector.Vector;
import sle.vectorfactories.VectorFactory;

import java.util.Random;
import java.util.Scanner;

public class TriDiagonalMatrixWithAdditionalRowAndColumnOnKFactory implements SquareMatrixFactory {
    private Random random;

    private VectorFactory _vectorFactory;

    public TriDiagonalMatrixWithAdditionalRowAndColumnOnKFactory(VectorFactory factory) {
        random = new Random();
        _vectorFactory = factory;
    }

    @Override
    public SquareMatrix readFromConsole(int size) throws Exception {
        if (size < 3) {
            throw new Exception("Некорректная длина матрицы");
        }
        System.out.println("Введите вектор a, начиная с a2, a = (a1, a2, ..., an), a1 = 0 ");
        Vector a = _vectorFactory.readFromConsole(size - 1);
        System.out.println("Введите вектор b, начиная b = (b1, b2, ..., bn)");
        Vector b = _vectorFactory.readFromConsole(size);
        System.out.println("Введите вектор a, до cn, c = (c1, c2, ..., cn), cn = 0 ");
        Vector c = _vectorFactory.readFromConsole(size - 1);
        System.out.println("Введите позицию векторов p и q, отсчет начинается с 0");
        int k = 0;
        try (Scanner scanner = new Scanner(System.in)){
            k = scanner.nextInt();
        }
        System.out.println("Введите вектор p, начиная p = (p1, p2, ..., pn)");
        Vector p = _vectorFactory.readFromConsole(size);
        System.out.println("Введите вектор q, начиная q = (q1, q2, ..., qn)");
        Vector q = _vectorFactory.readFromConsole(size);
        return new TriDiagonalMatrixWithAdditionalRowAndColumnOnK(a, b, c, p, q, k);
    }

    @Override
    public SquareMatrix readFromFile(String fileName) throws Exception {
        return null;
    }

    @Override
    public SquareMatrix getRandomMatrix(int size, float min, float max) throws Exception {
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
