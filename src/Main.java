import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //testExample();
        //testRandom();


        //------------Test1--------------
        //TestSpecialMatrix test = new TestSpecialMatrix();
        //test.printMatrix();
        //VectorOperations x = test.getTestSolve();
        //System.out.println("Result test vector x" + x);
        //System.out.println("Real test vector x" + test.x);
        //System.out.println("Result test vector d" + test.matr.multiplicationReserve(x));
        //Analysis analysis1 = new Analysis(test.getMatrix(), vectRes);
        //System.out.println("Finder test vector d" + analysis.getRightVector().getVector());

        randomSpecial();



    }

    public static void randomSpecial()
    {
        System.out.println("Enter matrix size: ");
        int size = new Scanner(System.in).nextInt();
        VectorOperations a = new VectorOperations(size);
        a.fillRandom(10,20);
        a.set(1,0);
        VectorOperations b = new VectorOperations(size);
        b.fillRandom(10,20);
        VectorOperations c = new VectorOperations(size);
        c.fillRandom(10,20);
        c.set(size,0);

        VectorOperations x = new VectorOperations(size);
        x.fillRandom(10,20);


        VectorOperations p = new VectorOperations(size);
        p.fillRandom(10,20);
        VectorOperations q = new VectorOperations(size);
        q.fillRandom(10,20);
        p.set(size-1, a.get(size));
        p.set(size, b.get(size));
        q.set(size-1, b.get(size-1));
        q.set(size, c.get(size-1));
        SpecialMatrix specMatr = new SpecialMatrix(a,b,c,x,p,q);
        System.out.println(specMatr);



        VectorOperations solve = specMatr.solveForSpecMatrix(x);
        System.out.println("Solve = " + solve);
        System.out.println("x Real = " + x);

    }

    public static void testExample() {
        TestExample example = new TestExample();
        runCheck(example.matr, example.x);
    }

    public static void testRandom(){
        System.out.println("Enter matrix size: ");
        int size = new Scanner(System.in).nextInt();
        TridiagonalMatrix tridiagonalMatrix = new TridiagonalMatrix(size).fillRandom(-10, 10);
        VectorOperations x = new VectorOperations(size).fillRandom(-10, 10);
        runCheck(tridiagonalMatrix, x);
    }


    public static void runCheck(TridiagonalMatrix tridiagonalMatrix, VectorOperations xGen) {
        System.out.println("\n### Matrix elements:");
        tridiagonalMatrix.print();

        VectorOperations d = tridiagonalMatrix.multiplication(xGen);
        System.out.println("\n### The elements of vector d:");
        System.out.println(d);

        System.out.println("\n### The elements of vector xGen:");
        System.out.println(xGen);

        VectorOperations a = tridiagonalMatrix.getExtendedBelowDiagonal();//new VectorOperations(size);
        VectorOperations b = tridiagonalMatrix.getMainDiagonal();
        VectorOperations c = tridiagonalMatrix.getExtendedHighDiagonal();

        VectorOperations xSolve = Method.solve(a, b, c, d);
        System.out.println("\n### Solution vector xSolve: ");
        System.out.println(xSolve);

        Analysis analysis = new Analysis(xGen, xSolve);

        VectorOperations vecErrorRate = analysis.errorRate();
        System.out.println("\n### Error rate: " + vecErrorRate.getVector());

        Double maxError = analysis.maxErrorRate(vecErrorRate);
        System.out.println("\n### Max error rate: " + maxError);

    }

}
