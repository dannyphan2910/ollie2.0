package calculator;

public class ExpOp {
	
	public ExpOp() {}
	
	public double pow(double[] input) {
		double a = input[0];
		double b = input[1];
		double result = Math.pow(a,b);
		if (result == 0 && a != 0) {
			return -1;
		}
		return result;
	}

	public double squareRoot(double a) {
		if (a < 0) {
			return -1;
		}
		return Math.sqrt(a);
	}
	
	public double switchSign(double value) {
		if (value != 0) { // if the value isn't 0
			return value * (-1); // we multiply it by -1 to get it's opposite value
		} else {
			return 0;
		}
	}
}
