public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordToDeque = new ArrayDeque<Character>();
        for(int i = 0; i < word.length(); i++) {
            wordToDeque.addLast(word.charAt(i));
        }
        return wordToDeque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        while(wordDeque.size() > 1) {
            Character first = wordDeque.removeFirst();
            Character last = wordDeque.removeLast();
            if(!first.equals(last)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        while (wordDeque.size() > 1) {
            char first = wordDeque.removeFirst();
            char last = wordDeque.removeLast();
            if(!cc.equalChars(first,last)) {
                return false;
            }
        }
        return true;
    }
}
