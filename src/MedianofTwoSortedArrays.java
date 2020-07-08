//4.Median of Two Sorted Arrays

public class MedianofTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length){
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        //using "m + (n - m + 1) / 2" to prevent Integer overflow
        // since it is sorted, so the left part on the median equal to (m + n) / 2 for even
        //(m + n + 1) / 2 for odd
        int totalLeft = (m + n + 1) / 2;

        //find the parting line in [0, m]
        //goal is nums[i - 1] <= nums[j] && nums[i] >= nums[j - 1]
        //so the judegement condition will be each of the upper one that opposite
        //AKA "left + (right -left + 1) / 2" or "left + (right - left) / 2"
        // + 1 is using to prevent overflow
        int left = 0;
        int right = m;

        //binary search
        while(left < right){
            int i = left + (right - left) / 2;
            int j = totalLeft - i;
            if(nums1[i] < nums2[j - 1]){
                left = i + 1;
            }
            else{
                right = i;
            }
        }


        int i = left;
        int j = totalLeft - i;
        int nums1LeftMax = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
        int nums1RightMin = (i == m ? Integer.MAX_VALUE : nums1[i]);
        int nums2LeftMax = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
        int nums2RightMin = (j == n ? Integer.MAX_VALUE : nums2[j]);

        if((m + n) % 2 == 1){
            return Math.max(nums1LeftMax, nums2LeftMax);
        }
        else{
            return (double) (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2;
        }
    }
}
