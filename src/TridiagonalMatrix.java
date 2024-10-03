import java.util.Vector;

public class TridiagonalMatrix {
    private Vector<VectorOperations> diagonals;

    public TridiagonalMatrix(int size) {
        for (int i = 0; i < size; i++) {
            diagonals.add(new VectorOperations(size));
        }
    }

    public void setLowerDiagonal(int index, double value) {
        diagonals.get(index).getVector().set(index - 1, value);
    }

    public void setMainDiagonal(int index, double value) {
        diagonals.get(index + 1).getVector().set(index - 1, value);
    }

    public void setUpperDiagonal(int index, double value) {
        diagonals.get(index + 2).getVector().set(index - 1, value);
    }

    public VectorOperations multiplication(VectorOperations vector) {
        if (vector.size() != diagonals.size()) {
            throw new IllegalArgumentException("Vector size must match matrix size");
        }
        VectorOperations result = new VectorOperations(vector.size());
        for (int i = 1; i <= vector.size(); i++) {
            if (i > 1) {
                result.getVector().set(i - 1, result.get(i) + diagonals.get(i).get(i - 1) * vector.get(i - 1));
            }
            result.getVector().set(i - 1, result.get(i) + diagonals.get(i + 1).get(i - 1) * vector.get(i - 1));
            if (i < vector.size()) {
                result.getVector().set(i - 1, result.get(i) + diagonals.get(i + 2).get(i - 1) * vector.get(i));
            }
        }
        return result;
    }

    public Vector<VectorOperations> getDiagonals() {
        return diagonals;
    }
}
