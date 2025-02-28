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
        int docLength = 0; // Used to store the largest length of the strings doc1 and doc2.
        int currentSubstringLength = 0; // Used to store the return value of the length of the currentLargestSubstring at a given position.
        int longestSubstringLength = 0; // Used to store the largest substring length from any function.
        if(doc1.length() < doc2.length()){
            docLength = doc2.length();
        }
        else{
            docLength = doc1.length();
        }
        int[][] arrMatches = new int[docLength][docLength]; // This stores the index values of matched characters between the 2 strings.
        int[][] arrLength = new int[docLength][docLength]; // This stores the substring value at a given position.
        // Initializing the values of the 2 arrays.
        for(int i = 0; i < arrMatches.length; i++){
            for(int j = 0; j < arrMatches[0].length; j++){
                arrMatches[i][j] = -1;
                arrLength[i][j] = 0;
            }
        }
        // populates matched data in the array
        initArrMatches(arrMatches, doc1, doc2);
        for(int i = 0; i < docLength; i++){
            currentSubstringLength = getSubLength(arrMatches, i, arrLength);
            if(currentSubstringLength > longestSubstringLength){
                longestSubstringLength = currentSubstringLength;
            }
        }
        return longestSubstringLength;
    }

    /*
        Stores the index of matching characters in each index of the column for each character.
     */

    /**
     * @param arrMatches
     * @param doc1
     * @param doc2
     */
    private static void initArrMatches(int[][] arrMatches, String doc1, String doc2) {
        if((arrMatches == null) || (arrMatches.length <= 0)){
            return;
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

    /**
     *
     * @param arrMatches
     * @param pos
     * @param arrLength
     * @return
     */
    private static int getSubLength(int[][] arrMatches, int pos, int[][] arrLength) {
        if((arrMatches == null) || (arrMatches.length <= 0)){
            return 0;
        }
        if((pos < 0) || (pos >= arrMatches.length)){
            return 0;
        }
        int currentLength = 0;
        int previousLength = 0;
        int myLength = 0;
        if(pos == 0){
            for(int k = 0; k < arrLength[pos].length; k++){
                if(arrMatches[pos][k] >= 0){
                    arrLength[pos][k] = 1;
                    currentLength = 1;
                }
                else{
                    break;
                }
            }
            return currentLength;
        }
        for(int k = 0; k < arrMatches[pos].length; k++){
            if(arrMatches[pos][k] == -1){
                return myLength;
            }
            if(arrMatches[pos][k] >= 0){
                currentLength = 1;
            }
            previousLength = 0;
            for(int j = pos - 1; j >= 0 && previousLength < j+1; j--){
                for(int l = 0; l < arrMatches[j].length; l++){
                    if(arrMatches[j][l] == -1){
                        break;
                    }
                    if((arrMatches[j][l] < arrMatches[pos][k]) && (previousLength < arrLength[j][l])){
                        previousLength = arrLength[j][l];
                    }
                }
                if(currentLength == 1){
                    arrLength[pos][k] = currentLength + previousLength;
                }
                if(myLength < arrLength[pos][k]){
                    myLength = arrLength[pos][k];
                }
            }
        }
        return myLength;
    }
}
