package finale;

public class sequence {
	double[][] input = { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, // sequence #
			{ 3, 1, 1, 5, 6, 6, 4, 2, 6, 4 }, // direction
			{ 1, 0, 0, 1, 0, 0, 1, 1, 0, 1 }, // method
			{ 2, 3, 3, 2, 3, 4, 2, 1, 3, 2 }, // diss time
			{ 0, 1, 1, 2, 1, 1, 2, 0, 1, 0 }, // demand
			{ 0, 1, 1, 1, 2, 2, 1, 2, 2, 2 } }; // material

	public sequence() {
	}

	public sequence(sequence toCopy) {
		for (int i = 0; i < 6; i++) {
			this.input[i] = toCopy.getSequence()[i].clone();
		}

	}

	public double[][] seqSwapInt(double x) // swap the columns
	{
		x = (int) x;
		double temp;
		for (int j = 0; j < x; j++) {
			int r1 = (int) (Math.random() * 10);
			int r2 = (int) (Math.random() * 10);
			for (int i = 0; i < 6; i++) {
				temp = input[i][r1];
				input[i][r1] = input[i][r2];
				input[i][r2] = temp;
			}
		}
		return input;
	}

	public void GenNewSol(double a) {
		int loops = (int) (10 * a);
		int prox = (int) ( 5 * a);
		double temp;
		for (int j = 0; j < loops; j++) {
			int r1 = (int) (Math.random() * 10); // procedure to be switched (index)
			int r2 = (int) (Math.random() * prox); // other thing to be switched

			int aOs = (int) (Math.random() * 10); // determine addition or subtraction
			if (aOs <= 5) {
				while ((r1 + r2) >= 10) {
					r2 = (int) (Math.random() * prox);
				}
				r2 = r1 + r2;
			}

			else if (aOs > 5) {
				while ((r1 - r2) < 0) {
					r2 = (int) (Math.random() * prox);
				}
				r2 = r1 - r2;
			}
			for (int i = 0; i < 6; i++) // columns swapped
			{
				temp = input[i][r1];
				input[i][r1] = input[i][r2];
				input[i][r2] = temp;
			}
		}
	}

	public double[][] getSequence() {
		return input;
	}

}