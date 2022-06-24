public class Main {

    static int sum_elems(int[] nums) {

        int sum = 0;
        for(var e : nums) {
            sum += e;
        }

        return sum;
    }

    static boolean check_is_equal(int[] nums, int n, int m, Boolean[][] dp) {
        boolean res = false;

        // Equal sums partition is possible
        if(n == 0) {
            return true;
        }

        // Out of bounds or currently guessed sums partition is not equal
        if(m < 0 || n < 0) {
            return false;
        }

        // Get results from memo table if available
        if(dp[m][n] != null) {
            return dp[m][n];
        }

        // Check if equal sums partition is possible
        if(m >= 0) {
            res = check_is_equal(nums, n - nums[m], m - 1, dp) || check_is_equal(nums, n, m - 1, dp);
        }

        // Store results in memo table
        dp[m][n] = res;

        return res;
    }

    static boolean can_partition(int[] nums) {

        int m = nums.length;

        // Compute sum of elements in input array nums
        int sum = sum_elems(nums);

        // Declare memo table
        Boolean[][] dp = new Boolean[m + 1][sum / 2 + 1];

        // Check if sum of elements is even
        if(sum % 2 != 0) {
            return false;
        }

        // Check if equal sums partition is possible
        return check_is_equal(nums, sum / 2, m - 1, dp);
    }

    public static void main(String[] args) {

        // Input nums array
        int[] nums = {1, 5, 11, 5};

        // Compute if equal sum partition is possible
        boolean can_part = can_partition(nums);

        // Print results
        System.out.println("can partition: " + can_part);
    }
}