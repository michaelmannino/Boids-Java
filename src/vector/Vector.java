package vector;

public class Vector {
    private float xComp, yComp;

    public Vector(float xComp, float yComp) {
        this.xComp = xComp;
        this.yComp = yComp;
    }

    public static Vector add(Vector i, Vector j) {
        return new Vector(i.xComp + j.xComp, i.yComp + j.yComp);
    }

    public static Vector subtract(Vector i, Vector j) {
        return new Vector(i.xComp - j.xComp, i.yComp - j.yComp);
    }

    public float getXComp() {
        return xComp;
    }

    public void setXComp(float xComp) {
        this.xComp = xComp;
    }

    public float getYComp() {
        return yComp;
    }

    public void setYComp(float yComp) {
        this.yComp = yComp;
    }

    public float getMagnitude() {
        return (float) Math.sqrt(Math.pow(xComp, 2) + Math.pow(yComp, 2));
    }

    public void add(Vector i) {
        this.xComp += i.xComp;
        this.yComp += i.yComp;
    }

    public void subtract(Vector i) {
        this.xComp -= i.xComp;
        this.yComp -= i.yComp;
    }

    public void divide(double i) {
        this.xComp /= i;
        this.yComp /= i;
    }

    public void clear() {
        this.xComp = 0;
        this.yComp = 0;
    }

    @Override
    public String toString() {
        return "(" + xComp + ", " + yComp + ")";
    }
}
