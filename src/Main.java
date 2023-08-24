public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}


class Solution {
    public String longestPalindrome(String s) {
        int L = 0;
        int R = 0;
        int[] range = {0,0};
        for(int i = 1; i< s.toCharArray().length; i++){ // this assumes odd length. for even length would need another
            // loop like this but L is i and R is i+1 and then compare and decrement/increment. but that would put me at like 40+ lines
            L = i-1;
            R = i+1;
            while(s.charAt(L) == s.charAt(R) && L >= 0 && R <= s.length()-1){
                R++;
                L--;
            }
            if((R-1) - (L+1) > (range[1] - range[0])){
                range[0] = L++;
                range[1] = R--;
            }
        }
        return s.substring(range[0],range[1]+1);
    }
}
