public class TestExample {
    public TridiagonalMatrix matr = new TridiagonalMatrix(5);
    public VectorOperations d = new VectorOperations(5);
    public VectorOperations x = new VectorOperations(5) {{
        set(1, 1);
        set(2, -1);
        set(3, 2);
        set(4, -1);
        set(5, 1);
    }};

    public TestExample() {
        matr.getVectors().get(0).set(1, 4.0);
        matr.getVectors().get(0).set(2, 2.0);

        matr.getVectors().get(1).set(1, 2.0);
        matr.getVectors().get(1).set(2, 6.0);
        matr.getVectors().get(1).set(3, 1.0);

        matr.getVectors().get(2).set(2, 5.0);
        matr.getVectors().get(2).set(3, 5.0);
        matr.getVectors().get(2).set(4, 2.0);

        matr.getVectors().get(3).set(3, 2.0);
        matr.getVectors().get(3).set(4, 11.0);
        matr.getVectors().get(3).set(5, 2.0);

        matr.getVectors().get(4).set(4, 5.0);
        matr.getVectors().get(4).set(5, 8.0);

        d.set(1,2);
        d.set(2,-2);
        d.set(3,3);
        d.set(4,-5);
        d.set(5,3);
    }

    public void printMatrix() {
        System.out.println("Test matrix:");
        for (int i = 1; i <= matr.getVectors().size(); i++) {
            System.out.println(matr.getVectors().get(i-1).getVector());
        }
        System.out.println();
        System.out.println("Test vector " + d.getVector());
    }

    public VectorOperations getTestSolve() {
        int size = 5;
        VectorOperations testBelow = matr.getBelowDiagonal();
        VectorOperations aTest = new VectorOperations(size);
        aTest.set(1,0);
        for(int i = 2; i <= size; i++) {
            aTest.set(i, testBelow.get(i-1));
        }

        VectorOperations testMain = matr.getMainDiagonal();


        VectorOperations testHigh = matr.getHighDiagonal();
        VectorOperations cTest = new VectorOperations(size);
        for(int i = 1; i <= size - 1; i++) {
            cTest.set(i, testHigh.get(i));
        }
        cTest.set(size,0);

        VectorOperations result = Method.solve(aTest, testMain, cTest, d);
        return result;
    }

    public TridiagonalMatrix getMatrix() {
        return matr;
    }

    public VectorOperations getVector() {
        return d;
    }
}
