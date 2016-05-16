import java.io.*;
import java.util.*; 
/**
 * A class that takes a Ruby script and translates it into a Java program
 * 
 * @author Carson Sloan and Andrew Kitterman
 */
public class Translator
{
    private static ArrayList<Variable> vars = new ArrayList<Variable>(); //Stores variables to be added as state variables in the translated code
    private static int lineNum = -1; //Stores which line is being read

    /**
     * Takes the user's Ruby file, then translates it to java and prints it to a new file
     * Runs the program
     * @param args unused
     */
    public static void main(String[] args)
    {   

        Scanner kbReader = new Scanner(System.in);
        System.out.println("Please input the name of the file you would ike to have translated");
        String fileName = kbReader.nextLine();
        System.out.println("Please input the name of the translated file to be output");
        String exportName = kbReader.nextLine();
        File file = null;
        FileWriter writer = null;
        try {//starts a try-catch block
            file = new File(exportName);//sets our file name equal to theirs because reasons
            file.createNewFile();//creates a new file
            writer = new FileWriter(file);//Sets writer equal to a new filewriter that writes to a file

        }
        catch (IOException e) {//catches an IO Exception
            System.out.println("oops");//Something went wrong
            System.exit(1);//Exits with an error
        }

        String line = null; //Used to scan line-by-line
        try//starts a try block
        {
            FileReader reader = new FileReader(fileName);//Filewriters be like
            BufferedReader bufferedReader = new BufferedReader(reader);//Too long; read anyway
            while((line = bufferedReader.readLine()) != null)//Starts a while loopy-thingy
            {
                lineNum++; //corrects what line is being read
                writer.write(translate(line) + "\n"); 
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        
        
        {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex)
        {
            ex.printStackTrace(); //For other errors
        }

        //Variable declarations
        //Executes at the end after all main method lines have been translated
        for(Variable var : vars)
        {
            try
            {
                writer.write("public" + var.toString() + ";");
            }
            catch(IOException I)
            {
                System.out.println("oops.");
                System.exit(1);//exits with an error
            }
        }
        try
        {
            writer.flush();//Clears the writer
            writer.close();//closes the writer
        }
        catch(IOException I)//Catches an IO exception
        {
            System.out.println("oops.");//Prints oops
            System.exit(1);//Exits with an error
        }
    }

    /**
     * Almost Done
     * Returns a java version of an inputted line of Ruby code
     * @return a java version of an inputted line of Ruby code
     */
    public static String translate(String line)//Declares the translate method
    {
        int eqPos = -1; //Stores the position of the equal sign so the variables can be separated
        String newLine = ""; //Translated line
        boolean isUntilLoop = false;//sets this boolean to false

        /* LOOPS */

        if (line.indexOf("while") != -1)//Checks index
        {
            newLine += "while ("; //Adds while to the new line
            eqPos = line.trim().indexOf("="); //Finds the index of the equal sign
            Variable var = new Variable(line.substring(line.indexOf("while") + 5, eqPos), "double");
            vars.add(var);
        }
        else if (line.indexOf("do") != -1)
        {
            newLine += "";
            eqPos = line.trim().indexOf("=");
            Variable var = new Variable(line.substring(line.indexOf("do") + 2, eqPos), "double");
            vars.add(var);
        }
        else if (line.indexOf("for") != -1)
        {
            newLine += "for (";
            eqPos = line.trim().indexOf("=");
            Variable var = new Variable(line.substring(line.indexOf("for") + 3, eqPos), "double");
            vars.add(var);//Carson won't remove these comments, so this one adds a new var to Vars.
        }
        else if(line.indexOf("until") != -1 )
        {
            isUntilLoop = true;
            if(isUntilLoop)
            {
                newLine += "do{" + line + "}";//This is still kinda broken.
                eqPos = line.trim().indexOf("="); //Works sorta
                Variable var = new Variable(line.substring(line.indexOf("until") + 5 , eqPos), "double");
                vars.add(var);
            }
        }

        /* CONSOLE */

        else if(line.indexOf("puts") != -1)
        {
            newLine += "System.out.println(//words);";
            eqPos = line.trim().indexOf("=");
            Variable var = new Variable(line.substring(line.indexOf("puts") + 4 , eqPos), "String");
            vars.add(var);
        }

        else if(line.indexOf(".chomp") != -1)
        {
            newLine += "Console console = System.console(); String line = console.readLine(//words you want to take input from) ";
            eqPos = line.trim().indexOf("=");
            Variable var = new Variable(line.substring(line.indexOf(".chomp") + 6 , eqPos), "String");
            vars.add(var);
        }

        /* VARIABLES */

        else if(line.indexOf("Array.new") != -1)
        {
            newLine += "double[] newArr = new double[some number here];";
            eqPos = line.trim().indexOf("=");
            Variable var = new Variable(line.substring(line.indexOf("Array.new") + 9 , eqPos), "double");
            vars.add(var);
        }

        else if(line.indexOf("Array.each") != -1)
        {
            newLine += "for(double u; newArr); {/*Some expression here/*}";
            eqPos = line.trim().indexOf("=");
            Variable var = new Variable(line.substring(line.indexOf("Array.each") + 10 , eqPos), "double");
            vars.add(var);
        }

        else if(line.indexOf("$")!= -1)
        {
            newLine +=  line.substring(line.indexOf("$"),line.indexOf(" ") );
            eqPos = line.trim().indexOf("=");
            Variable var = new Variable(line.substring(line.indexOf("$") + 1 , eqPos), "String");
            vars.add(var);
        }

        else if(line.indexOf("@")!= -1)
        {
            newLine +=  line.substring(line.indexOf("@"),line.indexOf(" ") );
            eqPos = line.trim().indexOf("=");
            Variable var = new Variable(line.substring(line.indexOf("@") + 1 , eqPos), "String");
            vars.add(var);
        }

        else if(line.indexOf("@@")!= -1)
        {
            newLine +=  line.substring(line.indexOf("@@"),line.indexOf(" ") );
            eqPos = line.trim().indexOf("=");
            Variable var = new Variable(line.substring(line.indexOf("@@") + 1 , eqPos), "String");
            vars.add(var);
        }

        else if(line.indexOf("def")!= -1)
        {
            newLine += line.substring(line.indexOf("def"), line.indexOf(" "));
            eqPos = line.trim().indexOf("=");
            Variable var = new Variable(line.substring(line.indexOf("def") +1, eqPos), "String");
            vars.add(var);
        }

        /* OPERATIONS */

        else if(line.indexOf("+") != -1)
        {
            newLine = line + ";";
        }

        else if(line.indexOf("-") != -1)
        {
            newLine = line + ";";
        }

        else if(line.indexOf("/") != -1)
        {
            newLine = line + ";";
        }

        else if(line.indexOf("*") != -1)
        {
            newLine = line + ";";
        }

        else if(line.indexOf("%") != -1)
        {
            newLine = line + ";";
        }

        /* STUFF */
        //Not sure what you want this to do

        else if(line.indexOf("end")!= -1)
        {
            newLine += line.substring(line.indexOf("end"), line.indexOf(" "));
            eqPos = line.trim().indexOf("=");
            //Unnecessary
            //Variable var = new Variable(line.substring(line.indexOf("end") +1, eqPos), "String");
            //vars.add(var);
        }

        else if(line.indexOf("return")!= -1)
        {
            newLine += line.substring(line.indexOf("return"), line.indexOf(" "));
            eqPos = line.trim().indexOf("=");
            //Unnecessary
            //Variable var = new Variable(line.substring(line.indexOf("return") +1, eqPos), "String");
            //vars.add(var); 
        }

        /* HASHES */
        //So basically we don't want to take the time to translate all this crap. That would take years.
        //So we're going to make sure it can recognize it at least
        //I don't even know what half of this is - Kitterman
        else if(line.indexOf("do")!= -1)
        {
            //does something
        }
        else if(line.indexOf("when")!= -1)
        {
            //when a condition is met, do something
        }
        else if(line.indexOf("new")!= -1)
        {
            //creates a new object.     
        }
        else if(line.indexOf("upto")!= -1)
        {
            //passes an int up and to a number
        }
        else if(line.indexOf("# -> []")!= -1)
        {
            //I think this is something about arrays
        }
        else if(line.indexOf("end")!= -1)
        {
            //ends something
        }
        //There aren't hashmaps in ruby, only hashes.
        else if(line.indexOf("hash.new")!= -1)
        {
            //creates a new hash
        }
        else if(line.indexOf("hash.[key]")!= -1)
        {
            //associates the value of key with a new value that isn't declared here because WHAT IF THEY DIDN'T NAME THEIR OBJECT VALUE??
        }
        else if(line.indexOf("hash.clear")!= -1)
        {
            //removes all key-value pairs from a hash
        }
        else if(line.indexOf("hash.default(")!= -1)
        {
            //returns the default value for the hash
        }
        else if(line.indexOf("hash.delete")!= -1)
        {
            //delets a key value pair from the hash
        }
        else if(line.indexOf("hash.each")!= -1)
        {
            //Iterates over the hash
        }
        else if(line.indexOf("hash.empty?")!= -1)
        {
            //checks to see if the hash is empty.
        }
        else if(line.indexOf("hash.fetch")!= -1)
        {
            //returns a value for the given key
        }
        else if(line.indexOf("hash.has")!= -1)
        {
            //checks to see if the hash contains the key-value pair
        }
        else if(line.indexOf("hash.key?")!= -1)
        {
            //checks to see if the hash contains the key
        }
        else if(line.indexOf("hash.index")!= -1)
        {
            //returns the given key for the value
        }
        else if(line.indexOf("hash.indexes")!= -1)
        {
            //returns a new array of values for the given key
        }
        else if(line.indexOf("hash.indices")!= -1)
        {
            //same thing as indexes.
        }
        else if(line.indexOf("hash.inspect")!= -1)
        {
            //returns a pretty string version of the hash
        }
        else if(line.indexOf("hash.invert")!= -1)
        {
            //creates a new hash, inverting the keys and values
        }
        else if(line.indexOf("hash.keys")!= -1)
        {
            //creates a new array of keys from hash
        }
        else if(line.indexOf("hash.length")!= -1)
        {
            //returns the size of hash as an Integer
        }
        else if(line.indexOf("hash.merge")!= -1)
        {
            //returns a new hash merging two hashes, hash and other_hash
        }
        else if(line.indexOf("hash.merge!")!= -1)
        {
            //same as .merge, but in place with no duplicates
        }
        else if(line.indexOf("hash.rehash")!= -1)
        {
            //builds a new hash based on the current values for each key
        }
        else if(line.indexOf("hash.reject")!= -1)
        {
            //returns a new hash for every pair of the block that returns true
        }
        else if(line.indexOf("hash.reject!")!= -1)
        {
            //same as reject, but changes are made in place
        }
        else if(line.indexOf("hash.replace")!= -1)
        {
            //replaces the current hash with the contents of a different hash
        }
        else if(line.indexOf("hash.select")!= -1)
        {
            //returns a new hash with the values of the block that return true
        }
        else if(line.indexOf("hash.shift")!= -1)
        {
            //removes a key-value pair from hash, returning a 2-element array
        }
        else if(line.indexOf("hash.size")!= -1)
        {
            //returns the size/length of the hash as an Integer
        }
        else if(line.indexOf("hash.sort")!= -1)
        {
            //converts hash to a 2d array and sorts it as an array
        }
        else if(line.indexOf("hash.store")!= -1)
        {
            //stores a key-value pair in hash
        }
        else if(line.indexOf("hash.to_")!= -1)
        {
            //converts hash to an array, taking all the key-value pairs with it
        }
        else if(line.indexOf("hash.update")!= -1)
        {
            //returns the contents of hash and another hash with duplicate keys
        }
        else if(line.indexOf("hash.value?")!= -1)
        {
            //tests whether hash contains the value
        }
        else if(line.indexOf("hash.values")!= -1)
        {
            //returns a new array containing all the values of hash
        }
        else if(line.indexOf("hash.values_at")!= -1)
        {
            //returns a new hash that has all the keys associated with the values
        }
        
        else if (line.indexOf("#") != -1)
        {
            newLine += "//" + line.substring(line.indexOf("#"));
        }
        
        else
        {
            throw new IndexOutOfBoundsException("Unknown argument on line " + lineNum); //If it finds an argument that isn't listed (Obviously, more need to be added)
        }
        return newLine;
    }
}
