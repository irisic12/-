import java.util.Vector;

public class TridiagonalMatrix {
    private Vector<VectorOperations> vectors;

    public TridiagonalMatrix(int size) {
        vectors = new Vector<>();
        for (int i = 0; i < size; i++) {
            vectors.add(new VectorOperations(size));
        }
    }

    public VectorOperations multiplication(VectorOperations vector) {
        if (vector.size() != vectors.size()) {
            throw new IllegalArgumentException("Vector size must match matrix size");
        }
        VectorOperations result = new VectorOperations(vector.size());
        for (int i = 1; i <= vector.size(); i++) {
            if (i == 1) {
                result.getVector().set(i, result.get(i) + vectors.get(i).get(i) * vector.get(i));
                result.getVector().set(i, result.get(i) + vectors.get(i).get(i + 1) * vector.get(i + 1));
            } else if (i == vector.size()) {
                result.getVector().set(i, result.get(i) + vectors.get(i).get(i - 1) * vector.get(i - 1));
                result.getVector().set(i, result.get(i) + vectors.get(i).get(i) * vector.get(i));
            } else {
                for (int j = i - 1; j <= i + 1; j++) {
                    result.getVector().set(i, result.get(i) + vectors.get(i).get(j) * vector.get(i));
                }
            }
        }
        return result;
    }

    public Vector<VectorOperations> getVectors() {
        return vectors;
    }

    public VectorOperations getMainDiagonal() {
        VectorOperations mainDiag = new VectorOperations(vectors.size());
        for (int i = 1; i <= mainDiag.size(); i++) {
            mainDiag.set(i, vectors.get(i-1).get(i));
        }
        return mainDiag;
    }

    public VectorOperations getHighDiagonal() {
        VectorOperations highDiag = new VectorOperations(vectors.size() - 1);
        for (int i = 1; i <= highDiag.size(); i++) {
            highDiag.set(i, vectors.get(i).get(i + 1));
        }
        return highDiag;
    }

    public VectorOperations getBelowDiagonal() {
        VectorOperations belowDiag = new VectorOperations(vectors.size() - 1);
        for (int i = 1; i <= belowDiag.size(); i++) {
            belowDiag.set(i, vectors.get(i-1).get(i));
        }
        return belowDiag;
    }

}
