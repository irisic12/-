public class TestSpecialMatrix {
    public TridiagonalMatrix matr = new TridiagonalMatrix(14);
    public VectorOperations d;
    public VectorOperations x = new VectorOperations(14);

    public TestSpecialMatrix() {
        matr.getVectors().get(0).set(1,2);
        matr.getVectors().get(0).set(2,3);
        matr.getVectors().get(0).set(3,4);
        matr.getVectors().get(0).set(4,5);
        matr.getVectors().get(0).set(5,6);
        matr.getVectors().get(0).set(6,7);
        matr.getVectors().get(0).set(7,8);
        matr.getVectors().get(0).set(8,9);
        matr.getVectors().get(0).set(9,10);
        matr.getVectors().get(0).set(10,11);
        matr.getVectors().get(0).set(11,12);
        matr.getVectors().get(0).set(12,13);
        matr.getVectors().get(0).set(13,14);
        matr.getVectors().get(0).set(14,15);

        matr.getVectors().get(1).set(1,2);
        matr.getVectors().get(1).set(2,4);
        matr.getVectors().get(1).set(3,6);
        matr.getVectors().get(1).set(4,8);
        matr.getVectors().get(1).set(5,10);
        matr.getVectors().get(1).set(6,12);
        matr.getVectors().get(1).set(7,14);
        matr.getVectors().get(1).set(8,16);
        matr.getVectors().get(1).set(9,18);
        matr.getVectors().get(1).set(10,20);
        matr.getVectors().get(1).set(11,22);
        matr.getVectors().get(1).set(12,24);
        matr.getVectors().get(1).set(13,26);
        matr.getVectors().get(1).set(14,28);

        matr.getVectors().get(2).set(13,3);
        matr.getVectors().get(2).set(12,6);
        matr.getVectors().get(2).set(11,9);

        matr.getVectors().get(3).set(12,12);
        matr.getVectors().get(3).set(11,15);
        matr.getVectors().get(3).set(10,18);

        matr.getVectors().get(4).set(11,21);
        matr.getVectors().get(4).set(10,24);
        matr.getVectors().get(4).set(9,27);

        matr.getVectors().get(5).set(10,12);
        matr.getVectors().get(5).set(9,15);
        matr.getVectors().get(5).set(8,18);

        matr.getVectors().get(6).set(9,2);
        matr.getVectors().get(6).set(8,4);
        matr.getVectors().get(6).set(7,8);

        matr.getVectors().get(7).set(8,4);
        matr.getVectors().get(7).set(7,8);
        matr.getVectors().get(7).set(6,12);

        matr.getVectors().get(8).set(7,16);
        matr.getVectors().get(8).set(6,20);
        matr.getVectors().get(8).set(5,24);

        matr.getVectors().get(9).set(6,5);
        matr.getVectors().get(9).set(5,10);
        matr.getVectors().get(9).set(4,15);

        matr.getVectors().get(10).set(5,7);
        matr.getVectors().get(10).set(4,14);
        matr.getVectors().get(10).set(3,21);

        matr.getVectors().get(11).set(4,12);
        matr.getVectors().get(11).set(3,6);
        matr.getVectors().get(11).set(2,24);

        matr.getVectors().get(12).set(3,4);
        matr.getVectors().get(12).set(2,2);
        matr.getVectors().get(12).set(1,8);

        matr.getVectors().get(13).set(2,10);
        matr.getVectors().get(13).set(1,10);

        for (int i = 1; i <= 14; i++) {
            x.set(i,1);
        }

        d = matr.multiplicationReserve(x);
    }

    public static void main(String[] args) {
        TestSpecialMatrix test = new TestSpecialMatrix();
        test.printMatrix();
        VectorOperations xSolveSpec = test.getTestSolve();
        System.out.println("Result test vector x" + xSolveSpec.toString());
        System.out.println("Real test vector x" + test.x.toString());
    }

    public void printMatrix() {
        System.out.println("Test matrix:");
        for (int i = 1; i <= matr.getVectors().size(); i++) {
            System.out.println(matr.getVectors().get(i-1).getVector());
        }
        System.out.println();
        System.out.println("d vector " + d);
    }

    public VectorOperations getTestSolve() {
        SpecialMatrix specMatrix = new SpecialMatrix(matr, d);
        VectorOperations result = specMatrix.solveForSpecMatrix(x);
        return result;
    }

    public TridiagonalMatrix getMatrix() {
        return matr;
    }

    public VectorOperations getVector() {
        return d;
    }

}
