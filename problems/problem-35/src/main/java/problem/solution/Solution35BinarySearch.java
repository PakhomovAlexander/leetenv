package problem.solution;

public class Solution35BinarySearch implements Solution35 {
    @Override
    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            int m = ((r - l) / 2) + l;
            if (target < nums[m]) {
                r = m - 1;
            } else if (target == nums[m]) {
                return m;
            } else {
                l = m + 1;
            }
        }

        if (r < 0) {
            return 0;
        }

        if (nums[r] == target) {
            return r;
        }
        if (nums[r] < target) {
            return r + 1;
        } else {
            return r;
        }
    }
}
