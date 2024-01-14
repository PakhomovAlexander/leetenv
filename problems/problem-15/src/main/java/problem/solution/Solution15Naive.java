package problem.solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//Benchmark                                  Mode  Cnt  Score   Error  Units
//Bench.oneBenchmark                       sample    2  8.145           s/op
//Bench.oneBenchmark:oneBenchmark·p0.00    sample       8.020           s/op
//Bench.oneBenchmark:oneBenchmark·p0.50    sample       8.145           s/op
//Bench.oneBenchmark:oneBenchmark·p0.90    sample       8.271           s/op
//Bench.oneBenchmark:oneBenchmark·p0.95    sample       8.271           s/op
//Bench.oneBenchmark:oneBenchmark·p0.99    sample       8.271           s/op
//Bench.oneBenchmark:oneBenchmark·p0.999   sample       8.271           s/op
//Bench.oneBenchmark:oneBenchmark·p0.9999  sample       8.271           s/op
//Bench.oneBenchmark:oneBenchmark·p1.00    sample       8.271           s/op
//
public class Solution15Naive implements Solution15 {
    @Override
    public List<List<Integer>> threeSum(int[] nums) {
        HashSet<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        ArrayList<Integer> triple = new ArrayList<>();
                        triple.add(nums[i]);
                        triple.add(nums[j]);
                        triple.add(nums[k]);
                        triple.sort(Integer::compareTo);
                        set.add(triple);
                    }
                }
            }
        }

        return set.stream().toList();
    }
}
