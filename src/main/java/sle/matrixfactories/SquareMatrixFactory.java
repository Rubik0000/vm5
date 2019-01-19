package sle.matrixfactories;

import sle.matrix.SquareMatrix;

/**
 * Интерфейс фабрики квадратных матриц
 */
public interface SquareMatrixFactory {
    /**
     * Считывает матрицу с консоли
     * @param size размер матрицы
     * @return считанную матрицу
     * @throws Exception
     */
    SquareMatrix readFromConsole(int size) throws Exception;

    /**
     * Считывает матрицу из файла
     * @param fileName имя файла
     * @return считанную матрицу
     * @throws Exception
     */
    SquareMatrix readFromFile(String fileName) throws Exception;

    /**
     * Создает матрицу, заполненную случайнми числами
     * @param size размер матрицы
     * @param min минимальное значение элемента
     * @param max максимальное значение элемента
     * @return сгенерированную матрицу
     * @throws Exception
     */
    SquareMatrix getRandomMatrix(int size, float min, float max) throws Exception;
}
