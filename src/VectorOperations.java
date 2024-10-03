import java.util.Collections;
import java.util.Vector;
import java.util.Random;

public class VectorOperations {
    private Vector<Double> elements;

    public VectorOperations(int size) {
        elements = new Vector<>(size);
        for (int i = 0; i < size; i++) {
            elements.add(i, 0.0);
        }
    }

    public void fillRandom(double start, double end) {
        Random rand = new Random();
        for (int i = 0; i < elements.size(); i++) {
            elements.set(i, rand.nextDouble() * (end - start) + start);
        }
    }

    public double maxElem = Collections.max(elements);

    public VectorOperations addition(VectorOperations other) {
        if (this.elements.size() != other.elements.size()) {
            throw new IllegalArgumentException("Vectors must be of the same size");
        }
        VectorOperations result = new VectorOperations(this.elements.size());
        for (int i = 0; i < elements.size(); i++) {
            result.elements.add(elements.get(i) + other.elements.get(i));
        }
        return result;
    }

    public VectorOperations subtraction(VectorOperations other) {
        if (this.elements.size() != other.elements.size()) {
            throw new IllegalArgumentException("Vectors must be of the same size");
        }
        VectorOperations result = new VectorOperations(this.elements.size());
        for (int i = 0; i < elements.size(); i++) {
            result.elements.add(elements.get(i) - other.elements.get(i));
        }
        return result;
    }

    public VectorOperations multiplication(VectorOperations other) {
        if (this.elements.size() != other.elements.size()) {
            throw new IllegalArgumentException("Vectors must be of the same size");
        }
        VectorOperations result = new VectorOperations(this.elements.size());
        for (int i = 0; i < elements.size(); i++) {
            result.elements.add(elements.get(i) * other.elements.get(i));
        }
        return result;
    }

    public double get(int index) {
        return elements.get(index - 1);
    }

    public double set(int index, double value) {
        return elements.set(index - 1, value);
    }

    public int size() {
        return elements.size();
    }

    public Vector<Double> getVector() {
        return elements;
    }
}
