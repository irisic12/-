public class Analysis {

    private VectorOperations xGen;
    private VectorOperations xSolve;

    public Analysis(VectorOperations xGen, VectorOperations xSolve) {
        this.xGen = xGen;
        this.xSolve = xSolve;
    }

    public VectorOperations errorRate() {
        VectorOperations vectorErrorRate = xGen.subtraction(xSolve);
        return vectorErrorRate;
    }

    public double maxErrorRate(VectorOperations vect) {
        return vect.getMaxElem();
    }
}
