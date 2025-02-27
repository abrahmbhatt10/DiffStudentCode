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
        String substring1 = "";
        String substring2 = "";
        int currentSubstringLength = 0;
        int longestSubstringLength = 0;
        if(doc1.length() < doc2.length())
        return longestSubstringLength;
    }
}
