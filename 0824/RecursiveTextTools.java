public class RecursiveTextTools {

    public static String reverse(String text) {
        if (text == null || text.length() <= 1) {
            return text;
        }

        return reverse(text.substring(1)) + text.charAt(0);
    }

    public static boolean isPalindrome(String text) {
        if (text == null) {
            return false;
        }

        String cleaned = text.replaceAll("\\s+", "").toLowerCase();

        return isPalindromeHelper(cleaned, 0, cleaned.length() - 1);
    }

    private static boolean isPalindromeHelper(String text, int left, int right) {
        if (left >= right) {
            return true;
        }

        if (text.charAt(left) != text.charAt(right)) {
            return false;
        }

        return isPalindromeHelper(text, left + 1, right - 1);
    }

    public static int countCharacter(String text, char target) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        int count = text.charAt(0) == target ? 1 : 0;

        return count + countCharacter(text.substring(1), target);
    }

    public static void main(String[] args) {

        System.out.println("empty：");
        System.out.println("reverse = " + reverse(""));
        System.out.println("isPalindrome = " + isPalindrome(""));
        System.out.println("countCharacter = " + countCharacter("", 'a'));

        System.out.println();

        System.out.println("single character：");
        System.out.println("reverse = " + reverse("A"));
        System.out.println("isPalindrome = " + isPalindrome("A"));
        System.out.println("countCharacter = " + countCharacter("A", 'A'));

        System.out.println();

        System.out.println("Level：");
        System.out.println("reverse = " + reverse("Level"));
        System.out.println("isPalindrome = " + isPalindrome("Level"));
        System.out.println("countCharacter = " + countCharacter("Level", 'e'));

        System.out.println();

        System.out.println("一般字串：");
        System.out.println("reverse = " + reverse("Hello"));
        System.out.println("isPalindrome = " + isPalindrome("Hello"));
        System.out.println("countCharacter = " + countCharacter("Hello", 'l'));

        System.out.println();

        System.out.println("含空白 palindrome：");
        System.out.println(
                "isPalindrome = " + isPalindrome("Never odd or even"));
    }
}