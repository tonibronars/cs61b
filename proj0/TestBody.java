public class TestBody {
	public static void main (String[] args) {
		Body a = new Body(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
		Body b = new Body(2.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
		System.out.println(a.calcDistance(b) == 1.0);
	}
}