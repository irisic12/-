import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size of the matrix:");
        int size = scanner.nextInt();

        TridiagonalMatrix tridiagonalMatrix = new TridiagonalMatrix(size);
        for(int i = 1; i <= size; i++) {
            tridiagonalMatrix.getVectors().get(i).fillRandom(-10,10);
        }

        System.out.println("Matrix elements:");
        System.out.println(tridiagonalMatrix);

        VectorOperations d = new VectorOperations(size);
        d.fillRandom(-10,10);
        System.out.println("The elements of vector d:");
        System.out.println(d);

        VectorOperations x =
                Method.solve(tridiagonalMatrix.getBelowDiagonal(),
                        tridiagonalMatrix.getMainDiagonal(),
                        tridiagonalMatrix.getHighDiagonal(), d);

        System.out.println("Solution vector x: " + x.getVector());
    }
}
