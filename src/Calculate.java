public class Calculate {
    private final int firstNumber;
    private final int secondNumber;

    public Calculate(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public int sum() {
        return firstNumber + secondNumber;
    }
}
