public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Body(double xP, double yP, double xV,
			double yV, double m, String img) {
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

	public double calcDistance(Body b1) {
		double dx = this.xxPos - b1.xxPos;
		double dy = this.yyPos - b1.yyPos;
		double r_2 = (dx*dx) + (dy*dy);
		double r = java.lang.Math.sqrt(r_2);
		return r;
	}

	public double calcForceExertedBy(Body b1) {
		double G = 6.67 * java.lang.Math.pow(10, -11);
		double F = (G * this.mass * b1.mass) / (this.calcDistance(b1) * this.calcDistance(b1));
		return F;
	}

	public double calcForceExertedByX(Body b1) {
		double dx = b1.xxPos - this.xxPos;
		double Fx = this.calcForceExertedBy(b1) * dx / this.calcDistance(b1);
		return Fx;
	}

	public double calcForceExertedByY(Body b1) {
		double dy = b1.yyPos - this.yyPos;
		double Fy = this.calcForceExertedBy(b1) * dy / this.calcDistance(b1);
		return Fy;
	}

	public double calcNetForceExertedByX(Body[] allBodys) {
		double num_of_bodys = allBodys.length - 1;
		double FNetX = 0;
		int i = 0;
		while (i <= num_of_bodys) {
			if (this.equals(allBodys[i])) {
				i++;
				continue;
			}
			else {
				FNetX += this.calcForceExertedByX(allBodys[i]);
				i++;
			}
		}
		return FNetX;
	}

	public double calcNetForceExertedByY(Body[] allBodys) {
		double num_of_bodys = allBodys.length - 1;
		double FNetY = 0;
		int i = 0;
		while (i <= num_of_bodys) {
			if (this.equals(allBodys[i])) {
				i++;
				continue;
			}
			else {
				FNetY += this.calcForceExertedByY(allBodys[i]);
				i++;
			}
		}
		return FNetY;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX/this.mass;
		double aY = fY/this.mass;
		this.xxVel = this.xxVel + dt * aX;
		this.yyVel = this.yyVel + dt * aY;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}
	
	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "\\images\\" + this.imgFileName);
	}
}

