import boid.Boid;
import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    public static int numberOfBoids = 100;
    public static ArrayList<Boid> flock = new ArrayList<Boid>();
    public static ArrayList<Boid> closeBoids = new ArrayList<Boid>();
    final int WIDTH = 2000, HEIGHT = 1200, VIEW_DISTANCE = 100;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        background(50, 50, 100);
        for (int i = 0; i < numberOfBoids; i++)
            flock.add(new Boid(WIDTH, HEIGHT));
    }

    public void drawBoid(Boid boid) {
        fill(255, 231, 100);
        stroke(255, 231, 100);
        ellipse(boid.getPosition().getXComp(), boid.getPosition().getYComp(), 5, 5);
    }

    public void draw() {
        background(50, 50, 100);
        for (Boid boid : flock) {
            closeBoids.clear();
            for (Boid otherBoid : flock) {
                if (boid.equals(otherBoid))
                    continue;
                if (boid.getDistance(otherBoid) <= VIEW_DISTANCE)
                    closeBoids.add(otherBoid);
            }
            boid.cohesion(closeBoids);
            boid.alignment(closeBoids);
            boid.separation(closeBoids);
            boid.update();
            drawBoid(boid);
        }
    }
}
