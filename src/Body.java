
public class Body {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	public Body (double xp, double yp, double xv, double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}
	
	public Body (Body b) {
	 myXPos = b.getX();
	 myYPos = b.getY();
	 myXVel = b.getXVel();
	 myYVel = b.getYVel();
	 myMass = b.getMass();
	 myFileName = b.getName();
	}
	
	/**
	 * getter method returns x position of this body
	 * @return x position 
	 */
	public double getX() {
		return myXPos;
	}
	
	/**
	 * getter method returns y position of this body
	 * @return y position
	 */
	public double getY() {
		return myYPos;
	}
	
	/**
	 * getter method returns x velocity of this body
	 * @return x velocity
	 */
	public double getXVel() {
		return myXVel;
	}
	
	/**
	 * getter method returns y velocity of this body
	 * @return y velocity
	 */
	public double getYVel() {
		return myYVel;
	}
	
	/**
	 * getter method returns mass of this body
	 * @return mass
	 */
	public double getMass() {
		return myMass;
	}
	
	/**
	 * getter method returns filename
	 * @return filename
	 */
	public String getName() {
		return myFileName;
	}
	
	/**
	 * return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
public double calcDistance(Body b) {
	double distance2 = 0;
	distance2 = (myXPos - b.getX())*(myXPos - b.getX()) + (myYPos - b.getY())*(myYPos - b.getY());
	return Math.sqrt(distance2);
}

/**
 * return the force exerted on this body by another body
 * @param p the other body that exerts a force on this body
 * @return force exerted by body p on this body
 */
public double calcForceExertedBy(Body p) {
	double force = 0;
	force = ((6.67*1e-11)*(myMass)*(p.getMass())/(this.calcDistance(p)*this.calcDistance(p)));
	return force;
}

/**
 * return the force exerted in the x direction by another body
 * @param p the other body that exerts this force
 * @return force in the x direction exerted by body p on this body
 */
public double calcForceExertedByX(Body p) {
	double forceX = 0;
	forceX = this.calcForceExertedBy(p)*(p.getX() - myXPos)/this.calcDistance(p);
	return forceX;
}

/**
 * return the force exerted in the y direction by another body
 * @param p the other body that exerts this force
 * @return force in the y direction exerted by body p on this body
 */
public double calcForceExertedByY(Body p) {
	double forceY = 0;
	forceY = this.calcForceExertedBy(p)*(p.getY() - myYPos)/this.calcDistance(p);
	return forceY;
}

/**
 * return the total forces exerted on this body in the x direction by all bodies in the array parameter
 * @param bodies all the bodies in the array parameter
 * @return the total force exerted in the x direction by bodies on this body
 */
public double calcNetForceExertedByX(Body[] bodies) {
	double netForceX = 0;
	for (Body b : bodies) {
		if (! b.equals(this)) {
			netForceX += calcForceExertedByX(b);
		}
	}
	return netForceX;
}

/**
 * return the total forces exerted on this body in the y direction by all bodies in the array parameter
 * @param bodies all the bodies in the array parameter
 * @return the total force exerted in the y direction by bodies on this body
 */
public double calcNetForceExertedByY(Body[] bodies) {
	double netForceY = 0;
	for (Body b : bodies) {
		if (! b.equals(this)) {
			netForceY += calcForceExertedByY(b);
		}
	}
	return netForceY;
}

/**
 * mutator that updates the instance variables of the Body object
 * @param deltaT small time steps
 * @param xforce net forces exerted on this body in the x direction by all other bodies
 * @param yforce net forces exerted on this body in the y direction by all other bodies
 */
public void update(double deltaT, double xforce, double yforce) {
	double ax = xforce/myMass;
	double ay = yforce/myMass;
	double nvx = myXVel + deltaT*ax;
	double nvy = myYVel + deltaT*ay; 
	double nx = myXPos + deltaT*nvx;
	double ny = myYPos + deltaT*nvy;
	myXPos = nx;
	myYPos = ny;
	myXVel = nvx;
	myYVel = nvy;
}

/**
 * draws the simulation
 */
public void draw() {
	StdDraw.picture(myXPos, myYPos,"images/"+myFileName);
}

}
