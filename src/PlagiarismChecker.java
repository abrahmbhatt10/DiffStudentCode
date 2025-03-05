import java.util.ArrayList;

/**
 * Plagiarism Checker
 * A tool for finding the longest shared substring between two documents.
 *
 * @author Zach Blick
 * @author Agastya Brahmbhatt
 */
public class PlagiarismChecker {

    /**
     * This method finds the longest sequence of characters that appear in both texts in the same order,
     * although not necessarily contiguously.
     * @param doc1 the first document
     * @param doc2 the second
     * @return The length of the longest shared substring.
     */
    public static int longestSharedSubstring(String doc1, String doc2) {
        //This function returns the length of the longest shared substring.
        ArrayList<MatchedStr> matchedStrings = new ArrayList<MatchedStr>();
        /*
        //This uses memoisation
        int[][] memArr = new int[doc1.length() + 1][doc2.length() + 1];
        for(int i = 0; i < doc1.length()+1; i++){
            for(int j= 0; j < doc2.length()+1; j++) {
                if(i == 0 || j == 0) {
                    memArr[i][j] = 0;
                } else {
                    memArr[i][j] = -1;
                }
            }
        }
        int count =  LCS(doc1.length(), doc2.length(), doc1, doc2, memArr, matchedStrings);
        printLongestSubString(matchedStrings,true,count);
        return count;
        */




        //Below I have done tabulation.
        /*
            Below initializes the 2-d array in which tabulation is done
         */
        int[][] tabArr = new int[doc1.length() + 1][doc2.length() + 1];

        /*
            Below assigns each cell in the 2-d array to a value,
            which is calculated by the tabulation function.
         */
        for (int i = 0; i < tabArr.length; i++) {
            for (int j = 0; j < tabArr[0].length; j++) {
                tabArr[i][j] = tabulation(i, j, doc1, doc2, tabArr, matchedStrings);
            }
        }
        /*
            Below returns the final number representing the length of the longest substring.
         */
        return tabArr[doc1.length()][doc2.length()];
    }

    /*
        Below is the memoization function.
     */
    public static int LCS(int i, int j, String doc1, String doc2, int[][] memArr, ArrayList<MatchedStr> matchedStrings){
        if(i < 0 || i > doc1.length() || j < 0 || j > doc2.length()) {
            return 0;
        }
        if((i == 0) || (j == 0)) {
            return memArr[i][j];
        }
        if(doc1.charAt(i - 1) == doc2.charAt(j - 1)){
            if(memArr[i-1][j-1] != -1) {
                memArr[i][j] = memArr[i-1][j-1]+1;
            }
            else {
                memArr[i][j] = LCS(i-1, j-1, doc1, doc2, memArr, matchedStrings) + 1;
            }
        }
        else{
            int m1 = 0;
            int m2 = 0;
            if(memArr[i-1][j] != -1) {
                m1 = memArr[i-1][j];
            } else {
                m1 = LCS(i-1, j, doc1, doc2, memArr, matchedStrings);
            }
            if(memArr[i][j-1] != -1) {
                m2 = memArr[i][j-1];
            } else {
                m2 = LCS(i, j-1, doc1, doc2, memArr, matchedStrings);
            }
            memArr[i][j] = Math.max(m1, m2);
        }
        return memArr[i][j];
    }

    /*
        Below uses the pseudocode on Mr. Blick's slides.
        While comparing the two chars at each index, if they're the same, then add 1 to the number stored diagonally to the upper left.
        If at that index the two chars (on doc1 and doc2) are different, then the value at that cell is the max of the value above and to the left.
        The value of each cell is returned by this method.
     */
    public static int tabulation(int i, int j, String doc1, String doc2, int[][] tabArr, ArrayList<MatchedStr> matchedStrings){
        if((i == 0) || (j == 0)){
            return 0;
        }
        if(doc1.charAt(i - 1) == doc2.charAt(j - 1)){
            return tabArr[i - 1][j - 1] + 1;
        }
        else{
            return Math.max(tabArr[i - 1][j], tabArr[i][j - 1]);
        }
    }
}