package problem.solution;



// @leetup=custom
// @leetup=info id=55 lang=java slug=jump-game

/*
 * You are given an integer array `nums`. You are initially positioned at the
 * array's first index, and each element in the array represents your maximum
 * jump length at that position.
 *
 * Return `true`* if you can reach the last index, or *`false`* otherwise*.
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 *
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum j
 * ump length is 0, which makes it impossible to reach the last index.
 *
 *
 * Constraints:
 *
 * * `1 <= nums.length <= 104`
 * * `0 <= nums[i] <= 105`
 *
 */
// @leetup=custom
// @leetup=code

/**
 * This is a first version of solution that uses pure recursion.
 * Does not pass load test.
 */
public class Solution551 implements Solution55 {

    @Override
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        return canJumpReq(0, nums);
    }

    boolean canJumpReq(int i, int[] nums) {
        if (i >= nums.length - 1) { // "jumped" to the end
            return true;
        }

        int jumpLength = nums[i];
        while (jumpLength != 0 && !canJumpReq(jumpLength + i, nums)) {
            jumpLength--;
        }

        return jumpLength != 0;
    }
}





