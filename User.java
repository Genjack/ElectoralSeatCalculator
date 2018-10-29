/* This class contains generic submodules for use in other programs.
 * Note: This file must be in the same directory as the calling program in 
 * order to be called as User.subModule(). 

   REFERENCING: Taken straight from OOPD; I created this class months ago.
*/
import java.util.*;

public class User
{
//*********************** Submodule: Integer Input *************************//

    public static int intInput( String prompt, int min, int max )
    {
        int integer = 0;
        String error = "";
        Scanner sc = new Scanner ( System.in );
        do
        {
            try
            {
                System.out.println ( error );
                System.out.println( prompt );
                integer = sc.nextInt();
                error = "Please enter a value within the range shown.";
            }
            catch ( InputMismatchException intError )
            {
                String flush = sc.nextLine();
                error = "Error: Please enter an integer.";
            }// End Try-Catch.
        } while ( ( integer < min ) || ( integer > max ) );
        return integer;
    }// End Submodule.

//************************ Submodule: Real Input *************************//

    // Submodule: Real Input.
    public static double realInput( String prompt, double min, double max )
    {
        double real = 0.0;
        String error = "";

        Scanner sc = new Scanner( System.in );
        do
        {
            try
            {
                System.out.println( error );
                System.out.println( prompt );
                error = "Error: Please enter a value within the range shown.";
                real = sc.nextDouble();
            }
            catch ( InputMismatchException realError )
            {
                String flush = sc.nextLine(); // Clear! 
                error = "Error: Please enter a real number.";
            } // End try-catch.
        } while ( ( real < min ) || ( real > max ) ) ;
        //Assertion: min <= real <= max.
        return real;
    } //End Submodule.  

//************************ Submodule: String Input *************************//

    // Submodule: Initial Input of Character to select shape.
    public static String getString( String prompt )
    {
        String error = "";
        String userString = null;
        Scanner sc = new Scanner ( System.in );
        do
        {
            System.out.println( error );    
            System.out.printf( prompt );
            userString = sc.nextLine();
            error = "Error: String cannot be empty.";
        }
        while ( userString == null );
        return userString;
    } // End Submodule.

}// End User Class.

