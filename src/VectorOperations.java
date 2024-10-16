import java.util.*;

public class VectorOperations{
    private Vector<Double> elements;

    public VectorOperations(int size) {
        elements = new Vector<>(size);
        for (int i = 0; i < size; i++) {
            elements.add(i, 0.0);
        }
    }
    public VectorOperations(double[] arr) {
        elements = new Vector<>(arr.length);
        for (double v : arr) {
            elements.add(v);
        }
    }

    public double getMaxElem() {
        return elements.stream().map(Math::abs).max(Double::compare).get();
    }

    public VectorOperations fillRandom(double start, double end) {
        Random rand = new Random();
        for (int i = 0; i < elements.size(); i++) {
            elements.set(i, rand.nextDouble() * (end - start) + start);
        }
        return this;
    }

    public VectorOperations addition(VectorOperations other) {
        if (this.elements.size() != other.elements.size()) {
            throw new IllegalArgumentException("Vectors must be of the same size");
        }
        VectorOperations result = new VectorOperations(this.elements.size());
        for (int i = 0; i < elements.size(); i++) {
            result.elements.set(i, elements.get(i) + other.elements.get(i));
        }
        return result;
    }

    public VectorOperations subtraction(VectorOperations other) {
        if (this.elements.size() != other.elements.size()) {
            throw new IllegalArgumentException("Vectors must be of the same size");
        }
        VectorOperations result = new VectorOperations(this.elements.size());
        for (int i = 0; i < elements.size(); i++) {
            result.elements.set(i, elements.get(i) - other.elements.get(i));
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

    @Override
    public String toString() {
        return elements.toString();
    }

    public VectorOperations getReversed() {
       VectorOperations rev =  new VectorOperations(size());
        for (int i = 1; i <= size() ; i++) {
            rev.set(i, elements.get(size() - i));
        }
        return rev;

    }

    public void reverse() {
        elements = new Vector<>(elements.reversed());
    }
}
