import java.util.ArrayList;
import java.util.List;

/**
 * Created on 4 Apr 2017.
 *
 * @author kmostafa
 */
public class CollatzSequence {
	
	/**
	 * Computes the next number in Collatz sequence
	 * @param n current number
	 * @return next number in Collatz sequence
	 */
	public long next(long n) {
		return (n & 1) == 0
		       ? n / 2
		       : 3 * n + 1;
	}
	
	private int minimumSteps = 3;
	
	/**
	 * @param minimumSteps minimum steps to compute in the sequence, default is 3
	 */
	public CollatzSequence(int minimumSteps) {
		this.minimumSteps = minimumSteps;
	}
	
	/**
	 * Creates an object with minimumSteps defaulted to 3
	 */
	public CollatzSequence() { }
	
	/**
	 * Generates a sequence starting at n
	 * @param n initial value
	 * @param minStep minimum steps before sequence stops
	 * @return an object containing the generated sequence, steps, stepsThroughOddNumber, peak
	 * and initial value
	 */
	public Info getSequenceInfo(long n, int minStep) {
		Info info = new Info(n);
		minStep--;
		info.steps = 0;
		info.stepsThroughOddNumber = 1;
		info.peak = n;
		while (n != 1 || info.steps < minStep) {
			info.steps++;
			info.stepsThroughOddNumber += n & 1;
			info.sequence.add(n);
			info.updatePeak(n);
			n = next(n);
		}
		info.sequence.add(1L);
		return info;
	}
	
	/**
	 * Generates a sequence starting at n
	 * @param n initial value
	 * @return an object containing the generated sequence, steps, stepsThroughOddNumber, peak
	 * and initial valu
	 */
	public Info getSequenceInfo(long n) {
		return this.getSequenceInfo(n, minimumSteps);
	}
	
	/**
	 * Generates a sequence starting at n and ending at 1
	 * @param n initial value
	 * @param minStep minimum steps before sequence stops
	 * @return a list containing the sequence
	 */
	public List<Long> getSequenceToOne(long n, int minStep) {
		return getSequenceInfo(n, minStep).sequence;
	}
	
	/**
	 * Generates a sequence starting at n and ending at 1, no minimum steps
	 * @param n initial value
	 * @return a list containing the sequence
	 */
	public List<Long> getSequenceToOne(long n) {
		List<Long> list = new ArrayList<>();
		while (n != 1) {
			list.add(n);
			n = next(n);
		}
		list.add(1L);
		return list;
	}
	
	/**
	 * Contains info about a Collatz sequence
	 */
	public static class Info {
		long initial;
		long steps;
		long stepsThroughOddNumber;
		long peak;
		List<Long> sequence = new ArrayList<>();
		
		public Info(long initial) {
			this.initial = initial;
		}
		
		@Override
		public String toString() {
			return "CollatzSequence.Info{" +
					"initial =" + getInitial() +
					", steps=" + getSteps() +
					", stepsThroughOddNumber=" + getStepsThroughOddNumber() +
					", peak=" + getPeak() +
					"\nsequence=" + getSequence() +
					'}';
		}
		
		
		public long getInitial() {
			return initial;
		}
		
		public long getSteps() {
			return steps;
		}
		
		public long getStepsThroughOddNumber() {
			return stepsThroughOddNumber;
		}
		
		public long getPeak() {
			return peak;
		}
		
		public List<Long> getSequence() {
			return sequence;
		}
		
		private void updatePeak(long offer) {
			peak = offer > peak ? offer : peak;
		}
	}
	
	public static void main(String[] args) {
		CollatzSequence collatzSequence = new CollatzSequence();
		System.out.println(collatzSequence.getSequenceInfo(27, 3));
		for (int i = -10; i < 1; i++) {
			System.out.println(collatzSequence.getSequenceInfo(i));
		}
		
	}
}
