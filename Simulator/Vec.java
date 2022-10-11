public class Vec
{
    private double x = 1;
    private double y = 1;

    //Constructors

    public Vec()
    {}

    public Vec(double n)
    {
        x = n;
        y = n;
    }

    public Vec(double x0,double y0)
    {
        x = x0;
        y = y0;
    }

    public Vec(Vec v0)
    {
        x = v0.getX();
        y = v0.getY();
    }

    public Vec(double[] v0)
    {
        x = v0[0];
        y = v0[1];
    }

    //Set functions

    void setV(Vec v0)       //Set Vector
    {
        x = v0.getX();
        y = v0.getY();
    }

    void setA(double[] v0)  //Set Array
    {
        x = v0[0];
        y = v0[1];
    }

    void setX(double x0)    //Set X
    {
        x = x0;
    }

    void setY(double y0)    //Set Y
    {
        y = y0;
    }

    void setXY(double x0,double y0) //Set X,Y
    {
        x = x0;
        y = y0;
    }

    //Get functions

    Vec getV()              //Get Vector
    {
        Vec answer = new Vec(x,y);
        return(answer);
    }

    double[] getA()         //Get Array
    {
        double[] answer = {x,y};
        return(answer);
    }

    double getX()           //Get X
    {
        return(x);
    }

    double getY()           //Get Y
    {
        return(y);
    }

    double getScale()
    {
        return(Math.sqrt((x*x)+(y*y)));
    }

    //Mutator functions

    void normalise()
    {
        double scale = this.getScale();
        x = x/scale;
        y = y/scale;
    }

    void add(Vec v0)
    {
        x = x + v0.getX();
        y = y + v0.getY();
    }

    void sub(Vec v0)
    {
        x = x - v0.getX();
        y = y - v0.getY();
    }

    void mul(Vec v0)
    {
        x = x * v0.getX();
        y = y * v0.getY();
    }

    void div(Vec v0)
    {
        x = x / v0.getX();
        y = y / v0.getY();
    }

    //Static functions

    static Vec normalise(Vec v0)
    {
        Vec answer = new Vec(v0);
        answer.normalise();
        return(answer.getV());
    }

    static double[] normalise(double[] v0)
    {
        Vec answer = new Vec(v0);
        answer.normalise();
        return(answer.getA());
    }

    static Vec add(Vec v0,Vec v1)
    {
        Vec answer = new Vec();
        answer.setX(v0.getX()+v1.getX());
        answer.setY(v0.getY()+v1.getY());
        return(answer);
    }

    static Vec sub(Vec v0,Vec v1)
    {
        Vec answer = new Vec();
        answer.setX(v0.getX()-v1.getX());
        answer.setY(v0.getY()-v1.getY());
        return(answer);
    }

    static Vec mul(Vec v0,Vec v1)
    {
        Vec answer = new Vec();
        answer.setX(v0.getX()*v1.getX());
        answer.setY(v0.getY()*v1.getY());
        return(answer);
    }

    static Vec div(Vec v0,Vec v1)
    {
        Vec answer = new Vec();
        answer.setX(v0.getX()/v1.getX());
        answer.setY(v0.getY()/v1.getY());
        return(answer);
    }

}