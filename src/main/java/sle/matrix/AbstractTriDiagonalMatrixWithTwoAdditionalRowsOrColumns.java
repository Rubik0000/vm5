package sle.matrix;

import sle.vector.Vector;

public abstract class AbstractTriDiagonalMatrixWithTwoAdditionalRowsOrColumns implements SquareMatrix{
    private Vector _aVec;
    private Vector _bVec;
    private Vector _cVec;
    private Vector _pVec;
    private Vector _qVec;

    protected Vector getA() {
        return _aVec;
    }

    protected Vector getB() {
        return _bVec;
    }

    protected Vector getC() {
        return _cVec;
    }

    protected Vector getP() {
        return _pVec;
    }

    protected Vector getQ() {
        return _qVec;
    }

    public AbstractTriDiagonalMatrixWithTwoAdditionalRowsOrColumns(
            Vector a, Vector b, Vector c, Vector p, Vector q) throws Exception {
        if (a.length() != b.length() - 1 || c.length() != b.length() - 1 ||
                b.length() != p.length() || b.length() != q.length()) {
            throw new Exception("Невозможно составить матрицу из векторов разных размеров");
        }
        _aVec = a;
        _bVec = b;
        _cVec = c;
        _pVec = p;
        _qVec = q;
    }

    @Override
    public int getSize() {
        return _bVec.length();
    }
}
