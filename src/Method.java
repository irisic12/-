import java.util.Vector;

public class Method {
    public static VectorOperations solve(VectorOperations a, VectorOperations b, VectorOperations c, VectorOperations d) {
        int n = d.size();
        VectorOperations l = new VectorOperations(n);
        VectorOperations m = new VectorOperations(n);
        VectorOperations x = new VectorOperations(n);

        l.set(1, c.get(1) / b.get(1));
        m.set(1, d.get(1) / b.get(1));

        for (int i = 2; i <= n; i++) {
            l.set(i, c.get(i) / (b.get(i) - a.get(i) * l.get(i - 1)));
            m.set(i, (d.get(i) - a.get(i) * m.get(i - 1)) / (b.get(i) - a.get(i) * l.get(i - 1)));
        }

        x.set(n, m.get(n));
        for (int i = n - 1; i >= 1; i--) {
            x.set(i, m.get(i) - l.get(i) * x.get(i + 1));
        }

        return x;
    }
}
