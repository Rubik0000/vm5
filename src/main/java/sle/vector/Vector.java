package sle.vector;

/**
 * Интерфейс вектора
 */
public interface Vector {
    /**
     * Возвращает длину вектора
     * @return длину вектора
     */
    int length();

    /**
     * Возвращает i-й компонент вектора
     * @param i индекс
     * @return i-й компонент вектора
     * @throws Exception если i меньще 0 или больше длины вектора
     */
    float get(int i) throws Exception;

    /**
     * Складывает с вектором
     * @param vec вектор с которым складывать
     * @return новый вектор, полученнный в результате сложения
     * @throws Exception если длины векторов рызные
     */
    Vector add(Vector vec) throws Exception;

    /**
     * Вычитает вектор
     * @param vec вектор который вычитать
     * @return новый вектор, полученнный в результате вычитания
     * @throws Exception если длины векторов рызные
     */
    Vector subtract(Vector vec) throws Exception;

    /**
     * Скалярное умножения векторов
     * @param vec вектор который умножать
     * @return скальярное произведение
     * @throws Exception если длины векторов рызные
     */
    float scalarMultiply(Vector vec) throws Exception;

    /**
     * Возвращает норму вектора как максимальную по модулю компоненту
     * @return
     */
    float getNorm();

    /**
     * Печатает вектор на консоль
     */
    void printToConsole();

    /**
     * Записывает вектор в файл
     * @param fileName имя файла
     * @throws Exception
     */
    void writeToFile(String fileName) throws Exception;
}
