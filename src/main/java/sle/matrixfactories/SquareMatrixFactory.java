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
     * @throws Exception если размер матрицы менее 2
     */
    SquareMatrix readFromConsole(int size) throws Exception;

    /**
     * Считывает матрицу из файла
     * @param fileName имя файла
     * @return считанную матрицу
     * @throws Exception если имя файла пустое или произошла ошибка при чтении
     */
    SquareMatrix readFromFile(String fileName) throws Exception;

    /**
     * Создает матрицу, заполненную случайнми числами
     * @param size размер матрицы
     * @param min минимальное значение элемента
     * @param max максимальное значение элемента
     * @return сгенерированную матрицу
     * @throws Exception если размер матрицы менее 2 или минимальное значение больше максимального
     */
    SquareMatrix getRandomMatrix(int size, float min, float max) throws Exception;
}
