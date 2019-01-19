package sle.test;

/**
 * Результат тестирования
 */
public class TestResult {
    /**
     * Порядок размера системы
     */
    private int systemSizePow;

    /**
     * Порядок диапазона значений
     */
    private int valuesSizePow;

    /**
     * Средняя погрешность
     */
    private float avgError;

    /**
     * Средняя оценка точности
     */
    private float avgAccuracyRating;

    /**
     * Кол-во опытов
     */
    private int countExperiments;

    /**
     * Конструктор
     * @param systemSize Порядок размера системы
     * @param valuesSize Порядок диапазона значений
     * @param avgError Средняя погрешность
     * @param avgAccuracyRating Средняя оценка точности
     * @param countExperiments Кол-во опытов
     */
    public TestResult(
            int systemSize,
            int valuesSize,
            float avgError,
            float avgAccuracyRating,
            int countExperiments) {
        this.systemSizePow = systemSize;
        this.valuesSizePow = valuesSize;
        this.avgError = avgError;
        this.avgAccuracyRating = avgAccuracyRating;
        this.countExperiments = countExperiments;
    }

    public int getSystemSizePow() {
        return systemSizePow;
    }

    public float getAvgError() {
        return avgError;
    }

    public float getAvgAccuracyRating() {
        return avgAccuracyRating;
    }

    public int getCountExperiments() {
        return countExperiments;
    }

    public int getValuesSizePow() {
        return valuesSizePow;
    }
}
