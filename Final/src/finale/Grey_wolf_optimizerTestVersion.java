package finale;

import java.util.*;
import java.io.*;
public class Grey_wolf_optimizerTestVersion {
	double r1;
	double r2;
	int N;
	int D;
	int maxiter;
	sequence alfa;
	sequence beta;
	sequence delta;
	double Lower[];
	double Upper[];
	calculateFitness ff;
	double XX[][];
	double X1[][];
	double X2[][];
	double X3[][];
	double fitness[];
	double BESTVAL[];
	double iterdep[];
	double a;
	double A1[];
	double C1[];
	double A2[];
	double C2[];
	double A3[];
	double C3[];
	ArrayList<sequence> solutions = new ArrayList<sequence>();

	public Grey_wolf_optimizerTestVersion(calculateFitness iff, double iLower[], double iUpper[], int imaxiter,
			int iN) {
		maxiter = imaxiter;
		ff = iff;
		Lower = iLower;
		Upper = iUpper;
		N = iN;
		D = Upper.length;

		XX = new double[N][D];
		alfa = new sequence();
		beta = new sequence();
		delta = new sequence();
		A1 = new double[D];
		C1 = new double[D];
		A2 = new double[D];
		C2 = new double[D];
		A3 = new double[D];
		C3 = new double[D];
		BESTVAL = new double[maxiter];
		iterdep = new double[maxiter];
		X1 = new double[N][D];
		X2 = new double[N][D];
		X3 = new double[N][D];

	}

	ArrayList<sequence> sort_and_index(ArrayList<sequence> XXX) {
		double[] yval = new double[N];
		for (int i = 0; i < N; i++) {
			yval[i] = ff.calcFit(XXX.get(i).input);
		}
		ArrayList<Double> nfit = new ArrayList<Double>();
		for (int i = 0; i < N; i++) {
			nfit.add(yval[i]);
		}
		ArrayList<Double> nstore = new ArrayList<Double>(nfit);
		Collections.sort(nfit);
		int[] indexes = new int[nfit.size()];
		for (int n = 0; n < nfit.size(); n++) {
			indexes[n] = nstore.indexOf(nfit.get(n));
			nstore.set(indexes[n], (double) 100);
		}
		ArrayList<sequence> B = new ArrayList<sequence>();
		for (int i = 0; i < N; i++) {
			B.add(XXX.get(indexes[i]));
		}

		return B;
	}

	void init() {
		for (int i = 0; i < N; i++) {
			double x;
			sequence newSol = new sequence();
			x = Lower[0] + (Upper[0] - Lower[0]) * Math.random();
			newSol.seqSwapInt(x);
			solutions.add(newSol);
		}

		solutions = sort_and_index(solutions);

		for (int i = 0; i < N; i++) {

		}

		alfa = solutions.get(0);
		beta = solutions.get(1);
		delta = solutions.get(2);

	}

	sequence solution() {
		init();
		int iter = 1;
		while (iter < maxiter) {

			a = 2.0 - ((double) iter * (2.0 / (double) maxiter));

			for (int i = 3; i < 6; i++) // Generate 5 new solutions based on Alpha Solutions
			{
				sequence temp = new sequence(alfa);
				temp.GenNewSol(a);
				solutions.set(i, temp);

			}

			for (int i = 6; i < 8; i++) // Generate 3 new solutions based on Beta Solutions
			{

				sequence temp2 = new sequence(beta);
				temp2.GenNewSol(a);
				solutions.set(i, temp2);
			}

			for (int i = 8; i < 10; i++) // Generate 2 new solutions based on Delta Solutions
			{
				sequence temp3 = new sequence(delta);
				temp3.GenNewSol(a);
				solutions.set(i, temp3);
			}
			solutions = sort_and_index(solutions);
			alfa = solutions.get(0);
			beta = solutions.get(1);
			delta = solutions.get(2);
			BESTVAL[iter] = ff.calcFit(solutions.get(0).input);
			
			iter++;
		}
		sequence out = solutions.get(0);
		return out;

	}

	void toStringnew() {
		sequence best = solution();
		System.out.println("Optimized value(Fitness) = " + (100 - ff.calcFit(best.getSequence())));
		System.out.println(Arrays.deepToString(best.getSequence()));
	}
	
}