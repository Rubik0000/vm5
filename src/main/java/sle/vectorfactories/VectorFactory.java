package sle.vectorfactories;

import sle.vector.Vector;

/**
 * Интерфейс фабрики вектор
 */
public interface VectorFactory {
    /**
     * Создает вектор с заданными значениями
     * @param values значения вектора
     * @return новый вектор
     * @throws Exception если значения не переданы
     */
    Vector createVector(float ...values) throws Exception;

    /**
     * Читает вектор с консоли
     * @param len длина вектора
     * @return считанный вектор
     * @throws Exception если длина вектора меньше 1
     */
    Vector readFromConsole(int len) throws Exception;

    /**
     * Читает вектор из файла
     * @param fileName имя файла
     * @return считанный вектор
     * @throws Exception если имя файла пустое или произошла ошибка при чтении
     */
    Vector readFromFile(String fileName) throws Exception;

    /**
     * Генерирует случайный вектор
     * @param len длина вектора
     * @param min минимальное значение компоненты
     * @param max максимальное значение компоненты
     * @return случайный вектор
     * @throws Exception если длина вектора меньше 1 или переданное минимальное значение больше максимального
     */
    Vector getRandomVector(int len, float min, float max) throws Exception;

    /**
     * Генерирует случайный вектор
     * @param len длина вектора
     * @param min минимальное значение компоненты
     * @param max максимальное значение компоненты
     * @param noZeros если флаг - истина, то вектор не будет содержать нулей
     * @return случайный вектор
     * @throws Exception если длина вектора меньше 1 или переданное минимальное значение больше максимального
     */
    Vector getRandomVector(int len, float min, float max, boolean noZeros) throws Exception;
}
