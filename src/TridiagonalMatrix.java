import java.util.Vector;

public class TridiagonalMatrix {
    private Vector<VectorOperations> vectors;

    public TridiagonalMatrix(int size) {
        vectors = new Vector<>();
        for (int i = 0; i < size; i++) {
            vectors.add(new VectorOperations(size));
        }
    }

    public TridiagonalMatrix(double[][] matrix) {
        vectors = new Vector<>();
        for (double[] doubles : matrix) {
            vectors.add(new VectorOperations(doubles));
        }
    }

    public void print(){
        for(int i = 1; i <= vectors.size(); i++) {
            System.out.println(vectors.get(i-1).getVector());
        }
    }
    public TridiagonalMatrix fillRandom(int a, int b){
        for(int i = 0; i < vectors.size(); i++) {
            vectors.get(i).fillRandom(a,b);
        }
        return this;
    }

    public VectorOperations multiplication(VectorOperations vector) {
        if (vector.size() != vectors.size()) {
            throw new IllegalArgumentException("Vector size must match matrix size");
        }
        VectorOperations result = new VectorOperations(vector.size());
        for (int i = 1; i <= vector.size(); i++) {
            if (i == 1) {
                result.set(i, result.get(i) + vectors.get(i - 1).get(i) * vector.get(i)
                        + vectors.get(i - 1).get(i + 1) * vector.get(i + 1));
            } else if (i == vector.size()) {
                result.set(i, result.get(i) + vectors.get(i - 1).get(i - 1) * vector.get(i - 1)
                        + vectors.get(i - 1).get(i) * vector.get(i));
            } else {
                result.set(i, result.get(i) + vectors.get(i - 1).get(i - 1) * vector.get(i - 1)
                        + vectors.get(i - 1).get(i) * vector.get(i)
                        + vectors.get(i - 1).get(i + 1) * vector.get(i + 1));
            }
        }
        return result;
    }

    public VectorOperations multiplicationReserve(VectorOperations vector) {
        if (vector.size() != vectors.size()) {
            throw new IllegalArgumentException("Vector size must match matrix size");
        }
        VectorOperations result = new VectorOperations(vector.size());
        for (int i = vector.size(); i >=1; i--) {
            if(i==vector.size()) {
                result.set(i, result.get(i) + vectors.get(i - 1).get(vector.size() - i + 1) * vector.get(i)
                + vectors.get(i - 1).get(vector.size() - i + 2) * vector.get(i));
            } else if (i==1 || i==2) {
                //result.set(i, result.get(i) + vectors.get(i - 1).get(vector.size() - i + 1) * vector.get(i)
                //+ vectors.get(i - 1).get(vector.size() - i) * vector.get(i));
                for(int j=1; j<=vector.size(); j++) {
                    result.set(i, result.get(i) + vectors.get(i-1).get(j) * vector.get(j));
                }
            } else {
                result.set(i, result.get(i) + vectors.get(i - 1).get(vector.size() - i) * vector.get(i)
                + vectors.get(i - 1).get(vector.size() - i + 1) * vector.get(i)
                + vectors.get(i-1).get(vector.size() - i + 2) * vector.get(i));
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
            mainDiag.set(i, vectors.get(i - 1).get(i));
        }
        return mainDiag;
    }

    public VectorOperations getSideMainDiagonal() {
        int size = vectors.size();
        VectorOperations sideDiag = new VectorOperations(size);
        for (int i = 1; i <= size; i++) {
            sideDiag.set(i, vectors.get(size - i).get(i));
        }
        return sideDiag;
    }

    public VectorOperations getHighDiagonal() {
        VectorOperations highDiag = new VectorOperations(vectors.size() - 1);
        for (int i = 1; i <= highDiag.size(); i++) {
            highDiag.set(i, vectors.get(i - 1).get(i + 1));
        }
        return highDiag;
    }

    public VectorOperations getExtendedHighDiagonal() {
        VectorOperations highDiag = new VectorOperations(vectors.size());
        for (int i = 1; i <= highDiag.size() - 1; i++) {
            highDiag.set(i, vectors.get(i - 1).get(i + 1));
        }
        highDiag.set(vectors.size(), 0);
        return highDiag;
    }

    public VectorOperations getSideHighDiagonal() {
        VectorOperations highDiag = new VectorOperations(vectors.size() - 1);
        for (int i = 1; i <= highDiag.size()-1; i++) {
            highDiag.set(i, vectors.get(i - 1).get(highDiag.size() - i));
        }
        return highDiag;
    }

    public VectorOperations getExtendedSideHighDiagonal() {
        int size = vectors.size();
        VectorOperations highDiag = new VectorOperations(size);
        highDiag.set(1, 0);
        for (int i = 2; i <= size; i++) {
            highDiag.set(i, vectors.get(size - i).get(i-1));
        }
        return highDiag;
    }

    public VectorOperations getBelowDiagonal() {
        VectorOperations belowDiag = new VectorOperations(vectors.size() - 1);
        for (int i = 1; i <= belowDiag.size(); i++) {
            belowDiag.set(i, vectors.get(i).get(i));
        }
        return belowDiag;
    }

    public VectorOperations getExtendedBelowDiagonal() {
        VectorOperations belowDiag = new VectorOperations(vectors.size());
        belowDiag.set(1,0);
        for (int i = 2; i <= vectors.size(); i++) {
            belowDiag.set(i, vectors.get(i-1).get(i-1));
        }
        return belowDiag;
    }


    //public VectorOperations getSideBelowDiagonal() {
    //    VectorOperations belowDiag = new VectorOperations(vectors.size() - 1);
    //    for (int i = 2; i <= belowDiag.size(); i++) {
    //        //belowDiag.set(i, vectors.get(i - 1).get(i + 2));
    //    }
    //    return belowDiag;
    //}

    public VectorOperations getExtendedSideBelowDiagonal() {
        int size = vectors.size();
        VectorOperations belowDiag = new VectorOperations(size);
        belowDiag.set(size, 0);
        for (int i = 1; i <= size - 1; i++) {
            belowDiag.set(i, vectors.get(size - i).get(i+1));
        }
        return belowDiag;
    }

}
