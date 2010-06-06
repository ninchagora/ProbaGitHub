package ninchagora.probability;

/**
 * Generates random integers with given distribution (specified by a probability density function). 
 */
public class IntegerDistribution {
	
	/**
	 * Probability function (unnormalized):<ul>
	 * <li>F[i] &lt;= F[i+1]</li>
	 * <li>F[i] == 0, for i &lt; 0</li>
	 * <li>F[i] == norm, for i &gt;= F.length</li>
	 * </ul>
	 *
	 */
	private double[] F;
	
	/**
	 * Sum of probability densities.
	 */
	private double norm;

	/**
	 * Integer distribution in range <code>0</code> to <code>(f.length - 1)</code>
	 * @param f - density function (doesn't have to be normalized)
	 * @throws IllegalArgumentException - if <code>f</code> contains negative values
	 */
	public IntegerDistribution(double f[]) {
		if(f==null) throw new IllegalArgumentException("f==null");
		if(f.length==0) throw new IllegalArgumentException("f.length==0");
		for(int i=0; i<f.length; i++)
			if(f[i]<0) throw new IllegalArgumentException("f[" + i + "]<0");
		
		this.F = new double[f.length];
		
		this.F[0] = f[0];
		this.norm = f[0];
		for(int i=1; i<f.length; i++) {
			this.F[i] = this.F[i-1] + f[i];
			this.norm += f[i];
		}
	}


	public int random() {
		double u = Math.random() * norm;
		//return inverseF_recursive(u, 0, F.length - 1);
		return inverseF_binary(u);
	}
	
	/**
	 * Iterative implementation of binary search.
	 * @param u - uniform random variable <code>0 &lt;= u &lt; 1 </code>
	 * @return <code>i</code> such that <code>F[i-1] &lt;= u &lt; F[i]</code>.
	 */
	private int inverseF_binary(double u) {
		int low = 0;
		int high = F.length - 1;
		while(low < high) {
			int mid = (low + high) / 2;
			if(u < F[mid]) {
				high = mid;
			}
			else if(u > F[mid]) {
				low = mid + 1;
			}
			else {
				return mid;
			}
		}
		return low;
	}
	
	
	/**
	 * Tail-recursive implementation of binary search. 
	 * 
	 * @param u - uniform random variable <code>0 &lt;= u &lt; 1 </code>
	 * @param low - lower bound for <code>i</code> (inclusive)
	 * @param high - upper bound for <code>i</code> (inclusive)
	 * @return <code>i</code> such that <code>F[i-1] &lt;= u &lt; F[i]</code>.
	 */
	@SuppressWarnings("unused")
	private int inverseF_recursive(final double u, final int low, final int high) {
		if(low==high) {
			return low;
		}
		else {
			int mid = (low + high) / 2;
			if(u < F[mid]) {
				return inverseF_recursive(u, low, mid);
			}
			else if(u > F[mid]) {
				return inverseF_recursive(u, mid+1, high);
			}
			else {
				return mid;
			}
		}
	}
	
	/**
	 * Linear search (slow).
	 * 
	 * @param u - uniform random variable <code>0 &lt;= u &lt; 1 </code>
	 * @return <code>i</code> such that <code>F[i-1] &lt;= u &lt; F[i]</code>.
	 */
	@SuppressWarnings("unused")
	private int inverseF_linear(double u) {
		for(int i=0; i<F.length; i++) {
			if(u<F[i]) return i;
		}
		return F.length - 1;
	}
	
}