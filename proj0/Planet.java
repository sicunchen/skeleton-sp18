public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static double G = 6.67e-11;
    public void draw(){
        StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
    }

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt(
                (p.xxPos - this.xxPos) * (p.xxPos - this.xxPos) + (p.yyPos - this.yyPos) * (p.yyPos - this.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        return (G * p.mass * this.mass) / (calcDistance(p) * calcDistance(p));
    }

    public double calcForceExertedByX(Planet p) {
        return (this.calcForceExertedBy(p) * (p.xxPos - this.xxPos)) / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return (this.calcForceExertedBy(p) * (p.yyPos - this.yyPos)) / this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double sumX = 0;
        for (Planet p : planets) {
            if (p.equals(this)) {
                continue;
            }
            sumX += this.calcForceExertedByX(p);
        }
        return sumX;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double sumY = 0;
        for (Planet p : planets) {
            if (p.equals(this)) {
                continue;
            }
            sumY += this.calcForceExertedByY(p);
        }
        return sumY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel += dt * aX;
        this.yyVel += dt * aY;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

}