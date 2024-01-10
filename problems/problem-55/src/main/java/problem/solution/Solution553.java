package problem.solution;

/**
 * Optimal solution with O(n) time and O(1) space.
 *
 * Benchmark                                  Mode    Cnt   Score    Error  Units
 * Bench.oneBenchmark                       sample  37439  ≈ 10⁻⁴            s/op
 * Bench.oneBenchmark:oneBenchmark·p0.00    sample         ≈ 10⁻⁴            s/op
 * Bench.oneBenchmark:oneBenchmark·p0.50    sample         ≈ 10⁻⁴            s/op
 * Bench.oneBenchmark:oneBenchmark·p0.90    sample         ≈ 10⁻⁴            s/op
 * Bench.oneBenchmark:oneBenchmark·p0.95    sample         ≈ 10⁻⁴            s/op
 * Bench.oneBenchmark:oneBenchmark·p0.99    sample         ≈ 10⁻³            s/op
 * Bench.oneBenchmark:oneBenchmark·p0.999   sample          0.001            s/op
 * Bench.oneBenchmark:oneBenchmark·p0.9999  sample          0.001            s/op
 * Bench.oneBenchmark:oneBenchmark·p1.00    sample          0.002            s/op
 */
public class Solution553 implements Solution55 {
    @Override
    public boolean canJump(int[] nums) {
        int i = nums.length - 1; // start from the finish.
        int jumpedFrom = i; // we can jump to itself.

        while (i >= 0) {
           if (nums[i] + i >= jumpedFrom) { // can jump from current to last point.
              jumpedFrom = i;
           }
           i--;
        }

        return jumpedFrom == 0; // we found a path from the finish to the start.
    }
}
