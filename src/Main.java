public class Main {
    public static void main(String[] args) {
        String test = "zabbbaq";
        System.out.println(new Solution().longestPalindrome(test));
    }
}


class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) { // char index identified to pass to helper method
            int len1 = expandFromMiddle(s, i, i); // for odd length palindrome (which could be of an odd or even string)
            int len2 = expandFromMiddle(s, i, i + 1); // for even palindrome, identifying 2 center chars
            int len = Math.max(len1, len2); // return greater
            if (len > end - start) { // if length greater than current longest substring length, replace
                start = i - ((len - 1) / 2); // utilize knowledge of center/start index and length where (len-1)
                // suppose i = 3 for len of 5.  5-1 -> 4/2 = 2.  start = 3-2 = 1. the '-1' is len-1 is there to prevent OOB
                end = i + (len / 2); // same for R
            }
        }
        return s.substring(start,end+1); // +1 since non-inclusive
    }

    public int expandFromMiddle(String s, int left, int right) {
        if (s == null || left > right) return 0;

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) { // expand out from middle
            left--;
            right++;
        }
        return right - left - 1; // say len of 7 for 'racecar' R = 7 L = -1 since we incremented OOB of while loop to terminate
        // so 7 - -1 -1 = 7+1-1 = 7
        // lets suppose its like this though 'zracecarq' so 'e' is index 4. while looped terminated
        // return 8-0-1 = 7.
        // the math is like this since  R increases while a lesser amount substracted since decrement L
        // so subtract -1. If actual indexes like R = 6, L = 0 in racecar, 6-0 = 6 actually requires +1
        // to account for non-inclusive, but when spread increased by 2, must subtract 1.
    }
}


// doesn't work
//class Solution {
//    public String longestPalindrome(String s) {
//        int L = 0;
//        int R = 0;
//        int[] range = {0,0};
//        for(int i = 1; i< s.toCharArray().length-1; i++){
//            if (s.length()%2 != 0){
//                L = i-1;
//                R = i+1;
//            } else {
//                L = i;
//                R = i+1;
//            }
//
//            while(s.charAt(L) == s.charAt(R) && L >= 0 && R <= s.length()-1){
//                R++;
//                L--;
//            }
//            if((R-1) - (L+1) > (range[1] - range[0])){
//                range[0] = L++;
//                range[1] = R--;
//            }
//        }
//        return s.substring(range[0],range[1]+1);
//    }
//}
