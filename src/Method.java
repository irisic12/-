public class Method {
    public VectorOperations multiply(VectorOperations vector) {
        if (vector.size() != diagonals.size()) {
            throw new IllegalArgumentException("Vector size must match matrix dimensions");
        }
        CustomVector result = new CustomVector(vector.size());
        for (int i = 1; i <= vector.size(); i++) {
            if (i > 1) {
                result.elements.set(i - 1, result.get(i) + diagonals.get(i).get(i - 1) * vector.get(i - 1)); // Нижняя диагональ
            }
            result.elements.set(i - 1, result.get(i) + diagonals.get(i + 1).get(i - 1) * vector.get(i - 1)); // Главная диагональ
            if (i < vector.size()) {
                result.elements.set(i - 1, result.get(i) + diagonals.get(i + 2).get(i - 1) * vector.get(i)); // Верхняя диагональ
            }
        }
        return result;
    }
}
