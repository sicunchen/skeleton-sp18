import java.util.ArrayList;

public class NBody {

    public static void main(String[] args){
        String backgroundImage = "images/starfield.jpg";
        Double T=Double.parseDouble(args[0]);
        Double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        Double radius=NBody.readRadius(filename);
        Planet[] planets=NBody.readPlanets(filename);
        StdDraw.enableDoubleBuffering();

        StdDraw.setScale(-radius, radius);


        double time=0;
        while(time<T){
            Double[] xForces=new Double[planets.length];
            Double[] yForces=new Double[planets.length];
            for(int i=0;i<planets.length;i++){
                xForces[i]=planets[i].calcNetForceExertedByX(planets);
                yForces[i]=planets[i].calcNetForceExertedByY(planets);
            }

            for(int i=0;i<planets.length;i++){
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.picture(0,0,backgroundImage);
            for(Planet planet: planets){
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            dt++;

        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
        

    }

    public static double readRadius(String filename) {
        In in = new In(filename);
        int numberOfPlanets = in.readInt();
        double radius = in.readDouble();
        return radius;

    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        ArrayList<Planet> planets = new ArrayList<Planet>();
        while (!in.isEmpty()) {
            String currentLine = in.readLine().trim();

            String[] currentLineArray = currentLine.split("\\s+");

            if (currentLineArray.length == 1) {
                continue;
            }

            try {
                double xxPos = Double.parseDouble(currentLineArray[0]);
                double yyPos = Double.parseDouble(currentLineArray[1]);

                double xxVel = Double.parseDouble(currentLineArray[2]);
                double yyVel = Double.parseDouble(currentLineArray[3]);
                double mass = Double.parseDouble(currentLineArray[4]);
                String imgFileName = currentLineArray[5];
                Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
                planets.add(planet);

            } catch (Exception e) {
                break;
            }

        }
        Planet[] planetArr = new Planet[planets.size()];
        planetArr = planets.toArray(planetArr);
        return planetArr;
    }
}