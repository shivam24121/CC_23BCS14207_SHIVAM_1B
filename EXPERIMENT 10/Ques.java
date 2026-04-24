import java.util.*;

public class Main {

    static class Pair {
        int val;
        int index;

        Pair(int v, int i) {
            val = v;
            index = i;
        }
    }

    static int[] ans;

    public static int[] countSmaller(int[] nums) {
        int n = nums.length;

        ans = new int[n];
        Pair[] arr = new Pair[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }

        mergeSort(arr, 0, n - 1);
        return ans;
    }

    static void mergeSort(Pair[] arr, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    static void merge(Pair[] arr, int left, int mid, int right) {

        List<Pair> temp = new ArrayList<>();

        int i = left;
        int j = mid + 1;
        int rightCount = 0;

        while (i <= mid && j <= right) {

            if (arr[j].val < arr[i].val) {
                rightCount++;
                temp.add(arr[j]);
                j++;
            } else {
                ans[arr[i].index] += rightCount;
                temp.add(arr[i]);
                i++;
            }
        }

        while (i <= mid) {
            ans[arr[i].index] += rightCount;
            temp.add(arr[i]);
            i++;
        }

        while (j <= right) {
            temp.add(arr[j]);
            j++;
        }

        for (int k = 0; k < temp.size(); k++) {
            arr[left + k] = temp.get(k);
        }
    }

    public static void main(String[] args) {

        int[] nums = {5,2,6,1};

        int[] result = countSmaller(nums);

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
