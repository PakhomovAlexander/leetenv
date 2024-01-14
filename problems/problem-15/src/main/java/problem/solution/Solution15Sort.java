package problem.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Benchmark                                  Mode  Cnt  Score    Error  Units
//Bench.oneBenchmark                       sample  446  0.022 ±  0.001   s/op
//Bench.oneBenchmark:oneBenchmark·p0.00    sample       0.021            s/op
//Bench.oneBenchmark:oneBenchmark·p0.50    sample       0.021            s/op
//Bench.oneBenchmark:oneBenchmark·p0.90    sample       0.025            s/op
//Bench.oneBenchmark:oneBenchmark·p0.95    sample       0.028            s/op
//Bench.oneBenchmark:oneBenchmark·p0.99    sample       0.035            s/op
//Bench.oneBenchmark:oneBenchmark·p0.999   sample       0.043            s/op
//Bench.oneBenchmark:oneBenchmark·p0.9999  sample       0.043            s/op
//Bench.oneBenchmark:oneBenchmark·p1.00    sample       0.043            s/op
public class Solution15Sort implements Solution15 {
    @Override
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }

            if (nums[i] > 0) {
                break;
            }

            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    res.add(List.of(nums[i], nums[j], nums[k]));

                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }

                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }

                    j++;
                    k--;
                } else if (sum < 0) {
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    j++;
                } else {
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    k--;
                }
            }
        }
        return res;
    }
}
