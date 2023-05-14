//This console utility is designed to convert integer numbers from decimal system (radix 10) to a system with a lower radix or backwards.
import java.util.Scanner;

public class NumSystemsConverter {
    static String inputTask, inputString, outputString;
    static String[] inputWords = new String[5];
    static int inputNumber, inputBase, outputBase;
    static Scanner type;

    public static void main(String[] args){
        System.out.println("This utility is designed to convert integer numbers from decimal system (radix 10) to a system with a lower radix or backwards.");
        type = new Scanner(System.in);
        getInput();
        type.close();
        if(inputBase > outputBase){
            outputString = convertDown(inputNumber, outputBase);
        }
        else{
            outputString = convertUp(inputString, inputBase);
        }
        System.out.printf("%s (radix %d) is converted to %s (radix %d)", inputString, inputBase, outputString, outputBase);
    }

    public static void getInput(){
        System.out.println("Type your request following this pattern (one of directions must be 10):");
        System.out.println("<NUMBER> from <radix_number> to <radix_number>");
        inputTask = type.nextLine();
        try{
            inputWords = inputTask.split(" ");
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage() + " - " + e.getClass());
            System.out.println("Incorrect pattern. Try again");
            getInput();
        }
        try{
            inputString = inputWords[0];
            inputNumber = Integer.parseInt(inputString);
            inputBase = Integer.parseInt(inputWords[2]);
            outputBase = Integer.parseInt(inputWords[4]);
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage() + " - " + e.getClass());
            System.out.println("Incorrect pattern (bad items). Try again");
            getInput();
        }
        if(! numberIsConformToRadix(inputString)){
            System.out.println("Incorrect pattern. Number is not conform to its radix. Try again");
            getInput();
        }
        if(inputBase > 10 || outputBase > 10){
            System.out.println("Incorrect pattern. Radix can't exceed 10. Try again");
            getInput();
        }
        if(inputBase < 2 || outputBase < 2){
            System.out.println("Incorrect pattern. Radix can't be lower 2. Try again");
            getInput();
        }
        if(inputBase == outputBase){
            System.out.println("Incorrect pattern. Both radix can't be 10. Try again");
            getInput();
        }
        if((inputBase == 10) == (outputBase == 10)){
            System.out.println("Incorrect pattern. At least one radix must be 10. Try again");
            getInput();
        }
    }

    public static boolean numberIsConformToRadix(String what){
        for(int i = 0; i < what.length(); i++){
            if(Character.digit(what.charAt(i), 10) >= inputBase){
                return false;
            }
        }
        return true;
    }
    
    public static String convertDown(int what, int to){
        StringBuilder output = new StringBuilder();
        while(what >= to){
            output.insert(0, what % to);
            what /= to;
        }
        output.insert(0, what);
        return output.toString();
    }

    public static String convertUp(String what, int from){
        int calc = 0;
        int power = 0;
        for(int i = what.length()-1; i >= 0; i--){
            calc += Character.digit(what.charAt(i), 10) * (int) Math.pow(from, power);
            power++;
        }
        return Integer.toString(calc);
    }
}