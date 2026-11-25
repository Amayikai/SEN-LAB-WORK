/*CIS/SWE/24/005
  WUNUKEN AMAYIKAI
   SOFTWARE ENGINEERING*/

   
public class PalindromeChecker {

    /** Ignores case, spaces and punctuation. Only letters and digits are compared. */
    static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char l = s.charAt(left);
            char r = s.charAt(right);
            if (!Character.isLetterOrDigit(l)) {
                left++;
            } else if (!Character.isLetterOrDigit(r)) {
                right--;
            } else {
                if (Character.toLowerCase(l) != Character.toLowerCase(r)) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama")); // true
        System.out.println(isPalindrome("Race car"));                       // true
        System.out.println(isPalindrome("hello"));                          // false
        System.out.println(isPalindrome(""));                               // true
        System.out.println(isPalindrome("!!!"));                            // true
        System.out.println(isPalindrome(null));                             // false
    }
}