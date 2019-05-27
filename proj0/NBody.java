public class NBody {

	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int no_of_planets = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String fileName) {
		In in = new In(fileName);
		int no_of_planets = in.readInt();
		Body[] planets_list = new Body[no_of_planets];
		double radius = in.readDouble();
		int i = 0;
		while(in.hasNextLine() && i < no_of_planets) {
			double xPos = in.readDouble();
			double yPos = in.readDouble();
			double xVel = in.readDouble();
			double yVel = in.readDouble();
			double m = in.readDouble();
			String planet = in.readString();
			Body planet_body = new Body(xPos, yPos, xVel, yVel, m, planet);
			planets_list[i] = planet_body;
			i++;
		}
		return planets_list;
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.exit(0);
		}
		else {
			double T = Double.parseDouble(args[0]);
			double dt = Double.parseDouble(args[1]);
			String filename = args[2];
			double radius = readRadius(filename);
			Body[] actualBodies = readBodies(filename);

			String currentWorkingDir = System.getProperty("user.dir");
			String imageToDraw = currentWorkingDir + "\\images\\starfield.jpg";
			
			// StdDraw.setScale(-radius, radius);
			StdDraw.setXscale(-radius, radius);
			StdDraw.setYscale(-radius, radius);
			StdDraw.enableDoubleBuffering();
			StdDraw.picture(-radius, radius, imageToDraw);
			
			double t = 0;
			while (t <= T) {
				double[] xForces = new double[actualBodies.length];
				double[] yForces = new double[actualBodies.length];
				int i = 0;
				while (i < actualBodies.length) {
					xForces[i] = actualBodies[i].calcNetForceExertedByX(actualBodies);
					yForces[i] = actualBodies[i].calcNetForceExertedByY(actualBodies);
					actualBodies[i].update(t, xForces[i], yForces[i]);
					StdDraw.picture(-radius, radius, imageToDraw);
					StdDraw.picture(-radius, radius, imageToDraw);
					actualBodies[i].draw();
					StdDraw.show();
					StdDraw.pause(10);
					i++;
				}
				t = t + dt;
			}
		}
	}

}
