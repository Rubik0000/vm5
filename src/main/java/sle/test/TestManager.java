package sle.test;

import sle.matrix.SquareMatrix;
import sle.matrixfactories.SquareMatrixFactory;
import sle.vector.Vector;
import sle.vector.VectorDefaultImp;
import sle.vectorfactories.VectorFactory;

public class TestManager {
    private VectorFactory vectorFactory;
    private SquareMatrixFactory squareMatrixFactory;

    public TestManager(VectorFactory vecFac, SquareMatrixFactory sqmf) {
        vectorFactory = vecFac;
        squareMatrixFactory = sqmf;
    }

    private float getError(Vector a, Vector b) throws Exception {
        return a.subtract(b).getNorm();
    }

    public TestResult makeTest(int systemSize, float min, float max, int countExp) {
        try {
            float[] one = new float[systemSize];
            for (int i = 0; i < systemSize; ++i) {
                one[i] = 1;
            }
            Vector oneVec = vectorFactory.createVector(one);
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
            return new TestResult(systemSize, min, max, avgError, avgAccuracyRating, countExp);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
