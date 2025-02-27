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
        //this function returns the length of the longest shared substring.
        /*
            The below code returns maximum substring length after going through all possible substrings.
         */
        String substring1 = "";
        String substring2 = "";
        int docLength = 0;
        int currentSubstringLength = 0;
        int longestSubstringLength = 0;
        if(doc1.length() < doc2.length()){
            docLength = doc2.length();
        }
        else{
            docLength = doc1.length();
        }
        int[][] arrMatches = new int[docLength][docLength];
        initArrMatches(arrMatches, doc1, doc2);
        for(int i = 0; i < docLength; i++){
            currentSubstringLength = getSubLength(arrMatches, i);
            if(currentSubstringLength > longestSubstringLength){
                longestSubstringLength = currentSubstringLength;
            }
        }
        return longestSubstringLength;
    }

    /*
        Stores the index of matching characters in each index of the column for each character.
     */
    private static void initArrMatches(int[][] arrMatches, String doc1, String doc2) {

        if((arrMatches == null) || (arrMatches.length <= 0)){
            return;
        }
        for(int i = 0; i < arrMatches.length; i++){
            for(int j = 0; j < arrMatches[0].length; j++){
                arrMatches[i][j] = -1;
            }
        }
        int k;
        for(int i = 0; i < doc1.length(); i++){
            k = 0;
            for(int j = 0; j < doc2.length(); j++){
                if(doc1.charAt(i) == doc2.charAt(j)){
                    arrMatches[i][k] = j;
                    k++;
                }

            }
        }
    }
    /*

     */
    private static int getSubLength(int[][] arrMatches, int pos) {
        if((arrMatches == null) || (arrMatches.length <= 0)){
            return 0;
        }
        if((pos < 0) || (pos >= arrMatches.length)){
            return 0;
        }
        int currentLength = 0;
        int compareNum = 0;
        if((pos == 0) && (arrMatches[pos][0] != -1)){
            return 1;
        }
        for(int i = 0; i < arrMatches[pos].length; i++){
            if(arrMatches[pos][i] == -1){
                return currentLength;
            }
            if(arrMatches[pos][i] == 0){
                currentLength++;
            }
            else{
                compareNum = arrMatches[pos][i];
                for(int j = pos - 1; j >= 0; j--){
                    for(int k = 0; k < arrMatches[j].length; k++){
                        if((arrMatches[j][k] < compareNum) && (arrMatches[j][k] != -1)){
                            currentLength++;
                            compareNum = arrMatches[j][k];
                        }
                    }
                }
            }
        }
    }

    /*
    Pseudocode:
    Use a 1-d array, only storing the first time you see the letter in the second string.
    If you find the same letter repeated again later in string 1, then don't use the first number in the table again, use the second number, in the 2nd # stored in the A cplumn.


     */

}
