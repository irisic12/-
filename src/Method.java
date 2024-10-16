import java.util.Vector;

public class Method {

    private static VectorOperations l;
    private static VectorOperations m;

    public static VectorOperations solve(VectorOperations a, VectorOperations b, VectorOperations c, VectorOperations d) {
        int n = d.size();
        l = new VectorOperations(n+1);
        m = new VectorOperations(n+1);
        VectorOperations x = new VectorOperations(n);

        l.set(2, c.get(1) / b.get(1));
        m.set(2, d.get(1) / b.get(1));

        for (int i = 3; i <= n + 1; i++) {
            l.set(i, c.get(i-1) / (b.get(i-1) - a.get(i-1) * l.get(i - 1)));
            m.set(i, (d.get(i-1) - a.get(i-1) * m.get(i - 1)) / (b.get(i-1) - a.get(i-1) * l.get(i - 1)));

            //System.out.printf("L_%s = %s\n", i, l.get(i));
            //System.out.printf("M_%s = %s\n\n", i, m.get(i));
        }

        //m.set(n, (d.get(n) - a.get(a.size())*m.get(n - 1))/ (b.get(n) - a.get(a.size())*l.get(n - 1)));

        x.set(n, m.get(n + 1));
        for (int i = n - 1; i >= 1; i--) {
            x.set(i, m.get(i+1) - l.get(i+1) * x.get(i+1));
        }

        return x;
    }

    public static VectorOperations getL() {
        return l;
    }

    public static VectorOperations getM() {
        return m;
    }
}
