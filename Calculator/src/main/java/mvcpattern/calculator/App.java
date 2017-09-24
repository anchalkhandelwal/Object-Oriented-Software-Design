package mvcpattern.calculator;

/**
 * MVC pattern for simple calculator addition funtion
 * addition of two numbers
 */
public class App 
{
    public static void main( String[] args )
    {
        View theView = new View();
        Model theModel = new Model();
        Controller theController = new Controller(theView, theModel);
        theView.setVisible(true);
    }
}
