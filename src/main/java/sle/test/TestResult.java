package sle.test;

public class TestResult {
    private int systemSize;
    private float minValue;
    private float maxValue;
    private float avgError;
    private float avgAccuracyRating;
    private int countExperiments;

    public TestResult(
            int systemSize,
            float minValue,
            float maxValue,
            float avgError,
            float avgAccuracyRating,
            int countExperiments) {
        this.systemSize = systemSize;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.avgError = avgError;
        this.avgAccuracyRating = avgAccuracyRating;
        this.countExperiments = countExperiments;
    }

    public int getSystemSize() {
        return systemSize;
    }

    public float getMinValue() {
        return minValue;
    }

    public float getMaxValue() {
        return maxValue;
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
}
