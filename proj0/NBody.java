public class NBody {

	public static double readRadius(String filename) {
		In in = new In(filename);
		int num_planets = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String filename) {
		In in = new In(filename);
		int num_planets = in.readInt();
		double radius = in.readDouble();
		Body[] bodies = new Body[num_planets];
		for(int i = 0; i < num_planets; i = i+1) {
			double xPos = in.readDouble();
			double yPos = in.readDouble();
			double xVel = in.readDouble();
			double yVel = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();
			Body body = new Body(xPos, yPos, xVel, yVel, mass, img);
			bodies[i]= body;
		} return bodies;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] bodies = readBodies(filename);

		StdDraw.setScale(-1*radius, radius);
		StdDraw.clear();
		StdDraw.picture(0,0,"images/starfield.jpg");
		for(Body b:bodies) {
			b.draw();
		}	

		StdDraw.enableDoubleBuffering();
		double time = 0;
		int num_planets = bodies.length;
		while(time <= T) {
			double[] xForces = new double[num_planets];
			double[] yForces = new double[num_planets];
			for(int i = 0; i < bodies.length; i = i+1) {
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}
			for(int i = 0; i < bodies.length; i = i+1) {
				bodies[i].update(dt,xForces[i],yForces[i]);
			}
			StdDraw.setScale(-1*radius, radius);
			StdDraw.clear();
			StdDraw.picture(0,0,"images/starfield.jpg");
			for(Body b:bodies) {
				b.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time = time + dt;
		}
	}
}