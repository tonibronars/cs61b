public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public static final double g_constant = 6.67e-11;

	public Body(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body other) {
		double x_dist = Math.abs(xxPos - other.xxPos);
		double y_dist = Math.abs(yyPos - other.yyPos);
		return Math.sqrt((x_dist*x_dist) + (y_dist*y_dist));
	}

	public double calcForceExertedBy(Body other) {
		double dist = calcDistance(other);
		return (g_constant * mass * other.mass)/(dist*dist);
	}
}