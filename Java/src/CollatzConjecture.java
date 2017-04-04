import java.util.ArrayList;
import java.util.List;

/**
 * Created on 4 Apr 2017.
 *
 * @author kmostafa
 */
public class CollatzConjecture {
	
	public long next(long n) {
		return (n & 1) == 0
		       ? n / 2
		       : 3 * n + 1;
	}
	
	private int minimumSteps = 3;
	
	public CollatzConjecture(int minimumSteps) {
		this.minimumSteps = minimumSteps;
	}
	
	public CollatzConjecture() { }
	
	public SequenceInfo getSequenceInfo(long n, int minStep) {
		SequenceInfo info = new SequenceInfo(n);
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
	
	public SequenceInfo getSequenceInfo(long n) {
		return this.getSequenceInfo(n, minimumSteps);
	}
	
	public List<Long> getSequenceToOne(long n, int minStep) {
		return getSequenceInfo(n, minStep).sequence;
	}
	
	public List<Long> getSequenceToOne(long n) {
		List<Long> list = new ArrayList<>();
		while (n != 1) {
			list.add(n);
			n = next(n);
		}
		list.add(1L);
		return list;
	}
	
	public static class SequenceInfo {
		long initial;
		long steps;
		long stepsThroughOddNumber;
		long peak;
		List<Long> sequence = new ArrayList<>();
		
		public SequenceInfo(long initial) {
			this.initial = initial;
		}
		
		@Override
		public String toString() {
			return "CollatzConjecture.SequenceInfo{" +
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
		CollatzConjecture collatzConjecture = new CollatzConjecture();
		System.out.println(collatzConjecture.getSequenceInfo(27, 3));
		for (int i = -10; i < 1; i++) {
			System.out.println(collatzConjecture.getSequenceInfo(i));
		}
		
	}
}
