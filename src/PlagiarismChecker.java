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
        int[][] tabArr = new int[doc1.length() + 1][doc2.length() + 1];

        for (int i = 0; i < tabArr.length; i++) {
            for (int j = 0; j < tabArr[0].length; j++) {
                tabArr[i][j] = tabulation(i, j, doc1, doc2, tabArr, matchedStrings);
            }
        }
        printLongestSubString(matchedStrings,true, tabArr[doc1.length()][doc2.length()],tabArr);
        return tabArr[doc1.length()][doc2.length()];
    }

    public static int LCS(int i, int j, String doc1, String doc2, int[][] memArr, ArrayList<MatchedStr> matchedStrings){
        if(i < 0 || i > doc1.length() || j < 0 || j > doc2.length()) {
            return 0;
        }
        if((i == 0) || (j == 0)) {
            return memArr[i][j];
        }
        if(doc1.charAt(i - 1) == doc2.charAt(j - 1)){
            addCharToLongestSubString(i,j, doc1,matchedStrings);
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

    public static int tabulation(int i, int j, String doc1, String doc2, int[][] tabArr, ArrayList<MatchedStr> matchedStrings){
        if((i == 0) || (j == 0)){
            return 0;
        }
        if(doc1.charAt(i - 1) == doc2.charAt(j - 1)){
            addCharToLongestSubString(i, j, doc1, matchedStrings);
            return tabArr[i - 1][j - 1] + 1;
        }
        else{
            return Math.max(tabArr[i - 1][j], tabArr[i][j - 1]);
        }
    }
    public static void addCharToLongestSubString(int i, int j, String doc1, ArrayList<MatchedStr> matchedStrings) {
            MatchedStr tStr = new MatchedStr();
            tStr.doc1Index = i;
            tStr.doc2Index = j;
            tStr.matchedChar = doc1.charAt(i-1);
            matchedStrings.add(tStr);
            return;
    }
    public static void printLongestSubString(ArrayList<MatchedStr> resultsArr, boolean allFlag, int count, int[][] valuesArr) {
        if(count <= 0){
            return;
        }
        if(allFlag) {
            System.out.println("Printing All Matched Strings:");
        }
        else {
            System.out.println("Printing One Matched Strings:");
        }
        char[] resultString = new char[count+1];
        MatchedStr pStr;
        int k = resultsArr.size() - 1;
        for(int i = valuesArr.length - 1; i >= 0; i--){
            for(int j = valuesArr[0].length - 1; j >= 0; j--){
                if(valuesArr[i][j] == count){
                    for(int m = i, n = j, l=1; m > 0 && n > 0 && count -l >= 0; m--, n--, k--, l++){
                        resultString[count - l] = getMatchedChar(resultsArr, m, n, k);
                    }
                    resultString[count]='\0';
                    System.out.println(resultString);
                    if(!allFlag) {
                        return;
                    }
                }
                else{
                    break;
                }
            }
        }
    }

    public static char getMatchedChar(ArrayList<MatchedStr> resultsArr, int doc1Index, int doc2Index, int matchedStrPos){
        MatchedStr pStr = null;
        for(int k = matchedStrPos; k >= 0; k--){
            pStr = resultsArr.get(k);
            if((pStr.doc1Index == doc1Index) && (pStr.doc2Index == doc2Index)){
               return pStr.matchedChar;
            }
            else{
                if((pStr.doc1Index == doc1Index - 1) && (pStr.doc2Index == doc2Index)){
                    return pStr.matchedChar;
                }
                if((pStr.doc1Index == doc1Index) && (pStr.doc2Index == doc2Index - 1)){
                    return pStr.matchedChar;
                }
            }
        }
        return ' ';
    }

}