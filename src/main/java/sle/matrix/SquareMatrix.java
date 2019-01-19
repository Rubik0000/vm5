package sle.matrix;

import sle.vector.Vector;

public interface SquareMatrix {
    int getSize();

    SquareMatrix add(SquareMatrix matr) throws Exception;

    SquareMatrix subtract(SquareMatrix matr) throws Exception;

    Vector multiply(Vector vec) throws Exception;

    Vector resolveLinearEquationSystem(Vector right) throws Exception;

    void printToConsole();

    void writeToFile(String fileName) throws Exception;
}
