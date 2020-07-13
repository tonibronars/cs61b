import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne obo = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        // Test single character and 0 character edge cases
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome(" "));
        assertTrue(palindrome.isPalindrome("_"));
        assertTrue(palindrome.isPalindrome("a"));

        // Test intuitive cases
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("magnificent"));
        assertFalse(palindrome.isPalindrome("Level"));
        assertTrue(palindrome.isPalindrome("naan"));
        assertTrue(palindrome.isPalindrome("level"));
        assertTrue(palindrome.isPalindrome("racecar"));

        // Test cases that involve special characters
        assertTrue(palindrome.isPalindrome("!@!"));
        assertTrue(palindrome.isPalindrome("~~~~"));
        assertFalse(palindrome.isPalindrome("~$#~"));
        assertFalse(palindrome.isPalindrome("aaarace!caraaa"));
    }

    @Test
    public void testIsPalindrome2() {
        // Test single character and 0 character edge cases
        assertTrue(palindrome.isPalindrome("", obo));
        assertTrue(palindrome.isPalindrome(" ", obo));
        assertTrue(palindrome.isPalindrome("_", obo));
        assertTrue(palindrome.isPalindrome("a", obo));

        // Test intuitive cases
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("flke", obo));
        assertFalse(palindrome.isPalindrome("naan", obo));
        assertFalse(palindrome.isPalindrome("manan", obo));

        //Test cases that involve special characters
        assertTrue(palindrome.isPalindrome("&flake%", obo));
        assertFalse(palindrome.isPalindrome("!na_an!", obo));
    }
}