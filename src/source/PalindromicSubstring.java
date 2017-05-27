package source;

import java.util.ArrayList;

public class PalindromicSubstring {

    public static String algorithmicSearch(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1]; //массив длин
        String[][] L = new String[n + 1][n + 1]; //массив палиндромов

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                L[i][j] = "";
            }
        }

        dp[0][0] = 1;
        L[0][0] = String.valueOf(s.charAt(0));
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                dp[i][i] = 1;
                L[i][i] = String.valueOf(s.charAt(i));
                int k = i;
                while (s.charAt(k) != s.charAt(j))
                    k--;
                if (s.charAt(i) == s.charAt(k)) {
                    L[i][j] = String.valueOf(s.charAt(i)) + L[i - 1][j + 1] + String.valueOf(s.charAt(i));
                    dp[i][j] = dp[i - 1][j + 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j + 1]);
                    if (dp[i - 1][j] > dp[i][j + 1]) {
                        L[i][j] = L[i - 1][j];
                    } else {
                        L[i][j] = L[i][j + 1];
                    }
                }
            }
        }
        return L[n - 1][0];
    }

    public static String longSearch(String s) {
        int n = s.length();
        ArrayList<Boolean> mask = new ArrayList<>();
        for(int i = 0; i < n; i++){
            mask.add(false);
        }

        String str = "";
        maskGenerate(mask);
        while(!allZero(mask)){
            String temp = generateString(s,mask);
            if(isPalindrome(temp) && temp.length() > str.length()){
                str = temp;
            }
            maskGenerate(mask);
        }
        return str;
    }

    private static void maskGenerate(ArrayList<Boolean> mask){
        for(int i = mask.size() - 1; i >= 0; i--){
            if(mask.get(i) == false){
                mask.set(i, true);
                break;
            } else {
                mask.set(i, false);
            }
        }
    }

    private static boolean allZero(ArrayList<Boolean> mask){
        for (Boolean b:mask) {
            if(b == true)
                return false;
        }
        return true;
    }

    private static String generateString(String s, ArrayList<Boolean> mask){
        String str = "";
        for(int i = 0; i < mask.size(); i++){
            if(mask.get(i) == true)
                str += s.charAt(i);
        }
        return str;
    }

    private static boolean isPalindrome(String s) {
        String sub1 = s.substring(0, s.length() % 2 != 0 ? s.length() / 2 + 1: s.length() / 2);
        String sub2 = s.substring(s.length() / 2, s.length());

        for (int i = 0; i < sub1.length(); i++)
            if (sub1.charAt(i) != sub2.charAt(sub2.length() - i - 1))
                return false;

        return true;
    }
}
