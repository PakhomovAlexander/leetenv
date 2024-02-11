package com.apakhomov.leetenv.algorithms.sort;

public class HeapSort implements Sort {
    @Override
    public int[] sort(int[] input) {
        var heap = new MinHeap(input);

        heap.heapify();

        return heap.arr();
    }

    /**
     * Max Heap. Invariant:
     * <p>Structured as a binary three, where a[i]-th left child is located at 2i + 1 and left child is at 2i + 2
     * <p>=> a[i]-th parent is located at (i - 1) / 2. Every parent is less that its children
     * <p>=> a[0] is always the minimum element
     */
    private static class MinHeap {
        private final int[] a;

        public int[] arr() {
            return a;
        }

        private MinHeap(int[] arr) {
            this.a = arr;
        }

        private void siftDown(int i, int n) {
            while (true) {
                // find the max among tree nodes.
                int j = i;
                if (leftChileInd(i) < n && a[leftChileInd(i)] > a[j]) {
                    j = leftChileInd(i);
                }
                if (rightChileInd(i) < n && a[rightChileInd(i)] > a[j]) {
                    j = rightChileInd(i);
                }

                // parent (i-th) is the max => invariant is ok
                if (i == j) {
                    break;
                }

                swap(i, j);
                i = j;
            }
        }

        private void swap(int i, int j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }

        private static int parentInd(int i) {
            return (i - 1) / 2;
        }

        private static int leftChileInd(int i) {
            return 2 * i + 1;
        }

        private static int rightChileInd(int i) {
            return 2 * i + 2;
        }

        void heapify() {
            // fix the heap: sift down
            for (int i = a.length; i >= 0; i--) {
                siftDown(i, a.length);
            }

            int i = a.length - 1;
            while (i > 0) {
                swap(0, i);
                siftDown(0, i--);
            }
        }

        private void siftUp(int i) {
            while (i > 0 && a[i] > a[parentInd(i)]) {
                swap(i, parentInd(i));
                i = parentInd(i);
            }
        }
    }

}
