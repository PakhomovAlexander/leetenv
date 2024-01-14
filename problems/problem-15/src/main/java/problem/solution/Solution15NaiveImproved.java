package problem.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//Benchmark                                  Mode  Cnt  Score   Error  Units
//Bench.oneBenchmark                       sample   69  0.146 ± 0.001   s/op
//Bench.oneBenchmark:oneBenchmark·p0.00    sample       0.142           s/op
//Bench.oneBenchmark:oneBenchmark·p0.50    sample       0.146           s/op
//Bench.oneBenchmark:oneBenchmark·p0.90    sample       0.149           s/op
//Bench.oneBenchmark:oneBenchmark·p0.95    sample       0.150           s/op
//Bench.oneBenchmark:oneBenchmark·p0.99    sample       0.155           s/op
//Bench.oneBenchmark:oneBenchmark·p0.999   sample       0.155           s/op
//Bench.oneBenchmark:oneBenchmark·p0.9999  sample       0.155           s/op
public class Solution15NaiveImproved implements Solution15 {
    @Override
    public List<List<Integer>> threeSum(int[] nums) {
        HashSet<List<Integer>> set = new HashSet<>();
        HashMap<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length ; j++) {
                int lookupValue = -(nums[i] + nums[j]);
                if (seen.get(lookupValue) != null) {
                    if (seen.get(lookupValue) == i || seen.get(lookupValue) == j) {
                        continue;
                    }
                    ArrayList<Integer> triplet = new ArrayList<>();
                    triplet.add(nums[i]);
                    triplet.add(nums[j]);
                    triplet.add(-(nums[i] + nums[j]));
                    triplet.sort(Integer::compareTo);
                    set.add(triplet);
                }

                seen.put(nums[j], j);
            }
            seen.put(nums[i], i);
        }

        return set.stream().toList();
    }
}
