package boid;

import vector.Vector;

import java.util.ArrayList;
import java.util.Random;

public class Boid {
    public final float MAX_VELOCITY = 5, MAX_ACCELERATION = 1;
    private final Vector position, velocity, acceleration;
    public int width, height;
    Random rand = new Random();

    public Boid(int width, int height) {
        this.position = new Vector(rand.nextFloat() * width, rand.nextFloat() * height);
        this.velocity = new Vector(rand.nextFloat() * MAX_VELOCITY * (rand.nextBoolean() ? -1 : 1),
                rand.nextFloat() * MAX_VELOCITY * (rand.nextBoolean() ? -1 : 1));
        this.acceleration = new Vector(0, 0);
        this.width = width;
        this.height = height;
    }

    public void update() {
        position.add(velocity);

        if (position.getXComp() < 0)
            position.setXComp(width);
        else if (position.getXComp() > width)
            position.setXComp(0);

        if (position.getYComp() < 0)
            position.setYComp(height);
        else if (position.getYComp() > height)
            position.setYComp(0);

        velocity.add(acceleration);
        if (velocity.getMagnitude() > MAX_VELOCITY)
            velocity.divide(velocity.getMagnitude());

        acceleration.clear();
    }

    public void cohesion(ArrayList<Boid> closeBoids) {
        if (closeBoids.size() == 0)
            return;

        Vector centerOfMass = new Vector(0, 0);
        for (Boid boid : closeBoids)
            centerOfMass.add(boid.position);

        centerOfMass.divide(closeBoids.size());
        centerOfMass.subtract(this.position);

        if (centerOfMass.getMagnitude() > MAX_ACCELERATION)
            centerOfMass.divide(centerOfMass.getMagnitude());

        this.acceleration.add(centerOfMass);
    }

    public void alignment(ArrayList<Boid> closeBoids) {
        if (closeBoids.size() == 0)
            return;

        Vector avgVelocity = new Vector(0, 0);
        for (Boid boid : closeBoids)
            avgVelocity.add(boid.velocity);

        avgVelocity.divide(closeBoids.size());
        avgVelocity.divide(avgVelocity.getMagnitude());

        this.acceleration.add(avgVelocity);
    }

    public void separation(ArrayList<Boid> closeBoids) {
        if (closeBoids.size() == 0)
            return;

        Vector sepDirection = new Vector(0, 0), difference;
        for (Boid boid : closeBoids) {
            difference = Vector.subtract(this.position, boid.position);
            difference.divide(this.getDistance(boid));
            sepDirection.add(difference);
        }

        sepDirection.divide(closeBoids.size());
        sepDirection.divide(sepDirection.getMagnitude() / 1.05);

        this.acceleration.add(sepDirection);
    }

    public float getDistance(Boid boid) {
        float deltaX = boid.position.getXComp() - this.position.getXComp();
        float deltaY = boid.position.getYComp() - this.position.getYComp();

        return (float) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Vector getAcceleration() {
        return acceleration;
    }

    @Override
    public String toString() {
        return "Position: " + position + " Velocity: " + velocity + " Acceleration: " + acceleration;
    }
}
