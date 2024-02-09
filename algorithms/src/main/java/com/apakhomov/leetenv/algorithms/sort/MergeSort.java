package com.apakhomov.leetenv.algorithms.sort;

/**
 * Naive implementation of mergesort. It does to match allocations.
 */
public class MergeSort implements Sort {
    @Override
    public int[] sort(int[] input) {
        if (input.length <= 1) {
            return input;
        }

        var split = new SplitArr(input, input.length / 2);
        int[] lSorted = sort(split.l);
        int[] rSorted = sort(split.r);

        return merge(lSorted, rSorted);
    }

    private static int[] merge(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        int i = 0, j = 0, k = 0;
        int[] c = new int[n + m];

        while (i < n || j < m) {
            if (j == m || (i < n && a[i] < b[j])) {
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
            }
        }

        return c;
    }

    private static class SplitArr {
        final int[] l;
        final int[] r;

        private SplitArr(int[] arr, int splitIndex) {
            this.l = new int[splitIndex];
            this.r = new int[arr.length - splitIndex];

            System.arraycopy(arr, 0, l, 0, splitIndex);
            System.arraycopy(arr, splitIndex, r, 0, arr.length - splitIndex);
        }
    }
}
