package calculator;

public class BasicOp {
	
	public BasicOp() {}
	
	public double add(double[] num) {
		return num[0] + num[1];
	}
	
	public double subtract(double[] num) {
		return num[0] - num[1];
	}
	
	public double multiply(double[] num) {
		return num[0] * num[1];
	}
	
	public double divide(double[] num) {
		if (num[1] == 0) {
			throw new IllegalArgumentException();
		}
		return num[0] / num[1];
	}
}