package sle.matrixfactories;

import sle.matrix.SquareMatrix;

public interface SquareMatrixFactory {
    SquareMatrix readFromConsole(int size) throws Exception;

    SquareMatrix readFromFile(String fileName) throws Exception;

    SquareMatrix getRandomMatrix(int size, float min, float max) throws Exception;
}
