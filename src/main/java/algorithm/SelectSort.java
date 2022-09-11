package algorithm;

public class SelectSort {


    // 选择最小或最大的放到第一个位置
    public static void main(String[] args) {

        int[] array = {3, 5, 1, 6, 65, 2, 55, 4, 0};
//        selectSortTest(array);
//        bubbleSortTest(array);

        insertSortTest(array);

    }

    private static void insertSortTest(int[] array) {
        print(array);
        insertSort(array);
        print(array);
    }

    /**
     * 1.将 0 的位置排序
     * 2.将 0-1 的位置排序
     * 3.将 0-2 的位置排序, 0-1 已经排序好，只需要将 2与0,1比较大小然后插入
     * 4.将 0-3 的位置排序, 0-2 已经排序好，只需要将 3与0,1,2比较大小然后插入
     *
     * @param array
     */
    private static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {

            for (int pre = i - 1; pre >= 0 && array[pre] > array[pre + 1]; pre--) {
                swap(array, pre + 1, pre);
            }
        }

    }

    private static void bubbleSortTest(int[] array) {
        print(array);
        bubbleSort(array);
        print(array);
    }

    /**
     * 第一位和第二位比较大小然后交换
     * 第二位和第三位比较大小然后交换
     * 直到n位，此时 n 已经是最大了，并且 n-1 已经部分排过序
     * 继续从第一位比较并交换到第 n-1 位。
     *
     * @param array
     */
    private static void bubbleSort(int[] array) {
        // i 1
        // j 0 ~ 1
        for (int end = array.length - 1; end >= 0; end--) {
            for (int j = 0; j < end; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j + 1, j);
                }
            }
        }
    }


    private static void selectSortTest(int[] array) {

        print(array);
        selectSort(array);
        print(array);
    }

    /**
     * 从所有的数中，先找出一个最小的把他放在第一位
     * 然后从 第二位到 n，继续找最小的，放在第二位
     * 重复直到 n为。先找再交换
     *
     * @param array
     */
    private static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                swap(array, i, minIndex);
            }
        }
    }

    private static void swap(int[] array, int pos1, int pos2) {
        int temp = array[pos2];
        array[pos2] = array[pos1];
        array[pos1] = temp;
    }

    public static void print(int[] array) {
        for (int e : array) {
            System.out.print(e + " ");
        }
        System.out.println();
    }
}
