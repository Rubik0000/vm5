package sle.vectorfactories;

import sle.vector.Vector;

public interface VectorFactory {
    Vector createVector(float ...values) throws Exception;

    Vector readFromConsole(int len) throws Exception;

    Vector readFromFile(String fileName) throws Exception;

    Vector getRandomVector(int len, float min, float max) throws Exception;

    Vector getRandomVector(int len, float min, float max, boolean noZeros) throws Exception;
}
