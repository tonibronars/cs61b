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
		double x_dist = other.xxPos - xxPos;
		double y_dist = other.yyPos - yyPos;
		return Math.sqrt((x_dist*x_dist) + (y_dist*y_dist));
	}

	public double calcForceExertedBy(Body other) {
		double dist = calcDistance(other);
		return (g_constant * mass * other.mass)/(dist*dist);
	}

	public double calcForceExertedByX(Body other) {
		double dist = calcDistance(other);
		double force = calcForceExertedBy(other);
		double x_dist = other.xxPos - xxPos;
		return force*x_dist/dist;
	}

	public double calcForceExertedByY(Body other) {
		double dist = calcDistance(other);
		double force = calcForceExertedBy(other);
		double y_dist = other.yyPos - yyPos;
		return force*y_dist/dist;
	}

	public double calcNetForceExertedByX(Body[] allBodys) {
		double total_force = 0;
		for (Body b : allBodys) {
			if (this.equals(b))
				continue;
			total_force = total_force + calcForceExertedByX(b);
		} return total_force;
	}

	public double calcNetForceExertedByY(Body[] allBodys) {
		double total_force = 0;
		for (Body b : allBodys) {
			if (this.equals(b))
				continue;
			total_force = total_force + calcForceExertedByY(b);
		} return total_force;
	}

	public void update(double dt, double fX, double fY) {
		double a_x = fX/mass;
		double a_y = fY/mass;
		xxVel = xxVel + (a_x * dt);
		yyVel = yyVel + (a_y * dt);
		xxPos = xxPos + (xxVel * dt);
		yyPos = yyPos + (yyVel * dt);

	}

	public void draw() {
		StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
	}
}