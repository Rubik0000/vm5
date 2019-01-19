package sle.vector;

public interface Vector {
    int length();

    float get(int i) throws Exception;

    Vector add(Vector vec) throws Exception;

    Vector subtract(Vector vec) throws Exception;

    float scalarMultiply(Vector vec) throws Exception;

    float getNorm();

    void printToConsole();

    void writeToFile(String fileName) throws Exception;
}
