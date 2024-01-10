package problem.solution;

public class Solution997Impl implements Solution997 {
    @Override
    public int findJudge(int n, int[][] trust) {
        // index + 1 th person trust to number of people
        int[] cntOut = new int[n];
        // index + 1 th person has number of people trust to him
        int[] cntIn = new int[n];

        for (int i = 0; i < trust.length; i++) {
            cntOut[trust[i][0] - 1]++;
            cntIn[trust[i][1] - 1]++;
        }

        for (int i = 0; i < cntOut.length; i++) {
            if (cntOut[i] == 0 && cntIn[i] == n - 1) {
                return i + 1;
            }
        }

        return -1;
    }
}
