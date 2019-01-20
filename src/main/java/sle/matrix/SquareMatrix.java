package sle.matrix;

import sle.vector.Vector;

/**
 * Интерфейс квадратной матрицы
 */
public interface SquareMatrix {
    /**
     * Возвращает размер матрицы
     * @return размер квадратной матрицы
     */
    int getSize();

    SquareMatrix add(SquareMatrix matr) throws Exception;

    SquareMatrix subtract(SquareMatrix matr) throws Exception;

    /**
     * Умножает матрицу на вектор
     * @param vec вектор
     * @return результут умножения (вектор)
     * @throws Exception если длина вектора не равна размеру матрицы
     */
    Vector multiply(Vector vec) throws Exception;

    /**
     * Решает СЛУ, представленную матрицей
     * @param right вектор правой части
     * @return решение СЛУ
     * @throws Exception если длина вектора правой части не равна размеру матрицы
     */
    Vector resolveLinearEquationSystem(Vector right) throws Exception;

    /**
     * Печатает матрицу на консоль
     */
    void printToConsole();

    /**
     * Печатает расширенную матрицу на консоль с вектором правой части
     * @param f вектор правой части
     */
    void printToConsole(Vector f);

    /**
     * Записывает матицу в файл
     * @param fileName имя файла
     * @throws Exception
     */
    void writeToFile(String fileName) throws Exception;
}
