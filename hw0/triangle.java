public class triangle {
	public static void main(String[] args) {
String star = "*";
int line_tracker = 0;
int star_tracker = 0;
int line_goal = 5;
int star_goal = 1;

while (line_tracker < line_goal) {
	while (star_tracker < star_goal){
		System.out.print(star);
		star_tracker = star_tracker + 1;
	}
	star_tracker = 0;
	star_goal = star_goal + 1;
	System.out.println();
	line_tracker = line_tracker + 1;
}
}
}