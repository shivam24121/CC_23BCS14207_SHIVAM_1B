class Solution {
    public int triangularSum(int[] nums) {
        int n = nums.length;
        long c = 1;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans = (ans + (int)((c % 10) * nums[i] % 10)) % 10;
            c = c * (n - 1 - i) / (i + 1);
        }

        return ans;
    }
}
