import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Arrays;

public class SpecialMatrix {
    private VectorOperations a;
    private VectorOperations b;
    private VectorOperations c;
    private VectorOperations d;
    private VectorOperations p;
    private VectorOperations q;
    public int size;

    public SpecialMatrix( VectorOperations a, VectorOperations b, VectorOperations c,
                         VectorOperations xGen, VectorOperations p, VectorOperations q) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.p = p;
        this.q = q;
        this.size = b.size();
        //this.specialMatrix = matrix;
        this.d = multiplicationReserve(xGen);
    }

    public SpecialMatrix(TridiagonalMatrix matrix, VectorOperations xGen) {
        this(
                matrix.getExtendedSideHighDiagonal(),
                matrix.getSideMainDiagonal(),
                matrix.getExtendedSideBelowDiagonal(),
                xGen,
                matrix.getVectors().get(0),
                matrix.getVectors().get(1));

        //VectorOperations a = matrix.getExtendedSideHighDiagonal();
        //VectorOperations b = matrix.getSideMainDiagonal();
        //VectorOperations c = matrix.getExtendedSideBelowDiagonal();
        //VectorOperations p = matrix.getVectors().get(0);
        //VectorOperations q = matrix.getVectors().get(1);

    }

    public void redefinition() {
        a.set(1, p.get(2));
        b.set(1, p.get(1));
        a.set(2, q.get(3));
        b.set(2, q.get(2));
        c.set(2, q.get(1));
    }

    public void changeMatrix(VectorOperations a, VectorOperations b, VectorOperations c,
                             VectorOperations p, VectorOperations q) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.p = p;
        this.q = q;

        redefinition();
    }

    private void printVector(VectorOperations vector, int mantisa, PrintStream stream) {
        for (int i = 1; i <= vector.size(); i++) {
            stream.printf("%." + mantisa + "ft", vector.get(i));
        }
        stream.println();
    }



    /*public void print(int mantisa, PrintStream stream) {
        printVector(p, mantisa, stream);
        printVector(q, mantisa, stream);

        // Печать элементов a, b и c
        for (int i = 3; i <= size - 1; i++) {
            for (int j = 1; j < size - i; j++) {
                stream.print("0t");
            }
            stream.printf("%." + mantisa + "ft", a.get(i));
            stream.printf("%." + mantisa + "ft", b.get(i));
            stream.printf("%." + mantisa + "f", c.get(i));

            for (int j = size - i + 3; j <= size; j++) {
                stream.print("t0");
            }
            stream.println();
        }

        // Печать последних элементов b и c
        stream.printf("%." + mantisa + "ft", b.get(size));
        stream.printf("%." + mantisa + "f", c.get(size));

        for (int i = 3; i <= size; i++) {
            stream.print("t0");
        }
        stream.println();
    }*/

    void check(VectorOperations fRes, VectorOperations xGen) {
        System.out.println("Новый вектор D: ");
        System.out.println(fRes.toString());
        System.out.println("Проверка A*X_точн:");
        VectorOperations dRes = this.multiplicationReserve(xGen);
        dRes.reverse();
        System.out.println("Res: " + dRes);
        System.out.printf("Погрешность: %.16f%n ", dRes.subtraction(d).getMaxElem());
    }

    public static VectorOperations multiplicationReserve(SpecialMatrix specMatrix, VectorOperations xGen) {
        int size = xGen.size();
        VectorOperations res = new VectorOperations(size);

        for (int i = 1; i <= size; i++) {
            res.set(1, res.get(1) + specMatrix.p.get(i)*xGen.get(i));
            res.set(2, res.get(2) + specMatrix.q.get(i)*xGen.get(i));
        }

        for (int i = 3; i <= size; i++) {
            int j = size - i + 1;
            double tmp = 0;
            if (j - 1 >= 1) tmp += specMatrix.a.get(j) * xGen.get(j - 1);
            tmp += specMatrix.b.get(j) * xGen.get(j);
            tmp += specMatrix.c.get(j) * xGen.get(j + 1);
            res.set(i, tmp);
        }

        return  res;
    }

    public VectorOperations multiplicationReserve(VectorOperations xGen){
        return multiplicationReserve(this, xGen);
    }
    public VectorOperations solveForSpecMatrix(VectorOperations xGen) {
        // Шаг 1: Приведение уравнений с n-го по 1 к двучленному виду
        d.reverse();
        double R;
        for (int i = 1; i <= size - 2; i++) {
            R = b.get(i);
            b.set(i, 1);
            c.set(i, c.get(i) / R);
            d.set(i, d.get(i) / R);

            if (i == size - 2) break;

            R = a.get(i + 1);
            a.set(i + 1, 0);
            b.set(i + 1, b.get(i + 1) - R * c.get(i));
            d.set(i + 1, d.get(i + 1) - R * d.get(i));

            R = p.get(i);
            p.set(i, 0);
            p.set(i + 1, p.get(i + 1) - c.get(i) * R);
            d.set(size, d.get(size) - d.get(i) * R);

            R = q.get(i);
            q.set(i, 0);
            q.set(i + 1, q.get(i + 1) - c.get(i) * R);
            d.set(size - 1, d.get(size - 1) - d.get(i) * R);

            System.out.println(this);
            check(d, xGen);
        }

        System.out.println(this);
        // changeMatrix(a, b, c, p, q);

        // R = b.get(3);
        // b.set(3, 1);
        // c.set(3, c.get(3) / R);
        // d.set(3, d.get(3) / R);

        R = p.get(size - 2);
        p.set(size - 2, 0);
        p.set(size - 1, p.get(size - 1) - c.get(size - 2) * R);
        d.set(size, d.get(size) - d.get(size - 2) * R);
       // System.out.println(this + "\nStep 1");

        R = q.get(size - 2);
        q.set(size - 2, 0);
        q.set(size - 1, q.get(size - 1) - c.get(size - 2) * R);
        d.set(size - 1, d.get(size - 1) - d.get(size - 2) * R);
        //System.out.println(this + "\nStep 2");

        //changeMatrix(a, b, c, p, q);

        R = q.get(size - 1);
        q.set(size - 1, 1);
        q.set(size, q.get(size) / R);
        d.set(size - 1, d.get(size - 1) / R);
        //System.out.println(this + "\nStep 3");

        R = p.get(size - 1);
        p.set(size - 1, 0);
        p.set(size, p.get(size) - q.get(size) * R);
        d.set(size, d.get(size) - d.get(size - 1) * R);
        //System.out.println(this + "\nStep 4");

        R = p.get(size);
        p.set(size, 1);
        d.set(size, d.get(size) / R);
        //System.out.println(this + "\nStep 5");
        // changeMatrix(a, b, c, p, q);

        System.out.println(this);
        check(d, xGen);

        VectorOperations x = new VectorOperations(d.size());
        x.set(size, d.get(size));
        x.set(size - 1, d.get(size - 1) - q.get(size) * x.get(size));
        //x.set(size - 2, d.get(size - 2) - c.get(size-2) * x.get(size-1));
        for (int i = size - 3; i >= 0; i--) {
            double c_ = c.get(i + 1);
            double x_ = x.get(i + 2);
            double d_1 = d.get(i + 1);

            x.set(i + 1, d_1 - c_ * x_);
        }
        return x;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\nSpecialMatrix\n");
        sb.append(p).append("\n");
        sb.append(q).append("\n");

        for (int i = 1; i <= size - 2; i++) {
            double[] row = new double[size];
            if (size - i - 3 >= 0) row[size - i - 3] = a.get(size - i - 1);
            row[size - i - 2] = b.get(size - i - 1);
            if (size - i - 1 >= 0) row[size - i - 1] = c.get(size - i - 1);
            sb.append(Arrays.toString(row)).append("\n");
        }

        sb.append("\nD = ").append(d);
        return sb.toString();
    }
}
