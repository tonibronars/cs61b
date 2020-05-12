public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int prev_sum = 0;
        while (x < 10) {
            System.out.print(x+prev_sum + " ");
            prev_sum = prev_sum+x;
            x = x + 1;
        }
    }
}