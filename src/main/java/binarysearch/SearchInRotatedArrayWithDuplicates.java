package binarysearch;

import static org.junit.Assert.assertEquals;

/**
 * Question:
 * Given an array of numbers which is sorted in ascending order and also rotated by some arbitrary number,
 * find if a given ‘key’ is present in it.
 * <p>
 * Write a function to return the index of the ‘key’ in the rotated array. If the ‘key’ is not present, return -1.
 * ---
 * Time Complexity: O(log(n))
 * Space Complexity: O(1)
 */
public class SearchInRotatedArrayWithDuplicates {

    public static void main(String[] args) {
        assertEquals(1, binarySearch(new int[]{3, 7, 3, 3, 3}, 7));
    }

    private static int binarySearch(int[] arr, int key) {
        if (arr[0] < arr[arr.length - 1]) {
            // array is increasing
            return binarySearch(arr, 0, arr.length - 1, key);
        }

        // find rotation index
        int left = 0, right = arr.length - 1;

        while (left < right) {
            while (arr[left] == arr[right] && left < right) left++;
            int mid = (right - left) / 2 + left;

            if (arr[mid] <= arr[right]) right = mid;
            else left = mid + 1;
        }

        int rotationIndex = left;

        // try left side of rotation index
        int index = binarySearch(arr, 0, rotationIndex - 1, key);

        if (index != -1) return index;

        // try right side of rotation index (rotation index inclusive)
        return binarySearch(arr, rotationIndex, arr.length - 1, key);
    }

    private static int binarySearch(int[] arr, int leftIndex, int rightIndex, int key) {
        int left = leftIndex, right = rightIndex;

        while (left < right) {
            int mid = (right - left) / 2 + left;

            if (key <= arr[mid]) right = mid;
            else left = mid + 1;
        }

        return (key == arr[left]) ? left : -1;
    }
}
