import java.io.File;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
/**  this class reads in a file of words, which is stored in an arraylist. This program will then take in
 * a string of length n and finds words of length n-1 found in the arraylist containing the words.
 * Ex. String n is state. words of length five in state that is found in the text file is state, a word of
 * length 4 is tate. this program does that all the way down to length n-1.
 *
 * @author (Amar Jagrup)
 * @version (1/30/19)
 */
public class UnScrabbler
{
    //global variable
    public  static ArrayList<String> arr;
    
    /**
     * This method is used to read in the text file using a bufferedReader
     * @param words this parameter represents the name of the text file to be read. reads filename without .txt.
     * @return  nothing
     * @ exception IOException when the text file is not found
     */
    public  UnScrabbler(String words) throws IOException {
        FileReader file = new FileReader(words +".txt"); 
        BufferedReader buff = new BufferedReader(file);        
        String line = buff.readLine();
        line = line.replaceAll("\\s","");  // gets rid of white space. 
        arr = new ArrayList<String>();
        while( line!= null)
        {
            line = line.replaceAll("\\s",""); 
            if( line.length()>=1)
            {
                arr.add(line.toLowerCase());
                
            }
            line = buff.readLine();

        }
        buff.close();

    }

    /**
     * This main method finds word of length n down to n-1 of the parameter args[0]. 
     * this method then prints out words found in args[0]. 
     * @param args[]- args[0] will be the word to find 
     * @return  nothing
     * @ exception IOException when the text file is not found
     */
    public  static  void main(String args[]) throws IOException
    {         

        args[0] = args[0].replaceAll("\\s",""); // gets rid of white space. 
        int wordLength = args[0].length();
        UnScrabbler d = new UnScrabbler("words");
        ArrayList<String> arr2 = new ArrayList<String>(arr); // contains list of words
        ArrayList<Character> arr3 = new ArrayList<Character>();  // list of inout string n
        ArrayList<String> arrScramble = new ArrayList<String>();
        ArrayList<String> finalList = new ArrayList<String>(); // final list to be printed 
        char [] arr1 = args[0].toLowerCase().toCharArray();

        char[] cArr = new char[args[0].length()];
        int count = 0;
        int counter = 0;
        int countFile = 0;
        for(Character c: arr1)
        {
            arr3.add(c);  // contains the charachters of the input string, n.

        }
        Collections.sort(arr3);
        int currentWordLength = 0;
        
        while(wordLength > 1 )
        {
            for(int i=0; i<arr3.size();i++) 

            {

                for(int  j=0; j< arr2.size();j++)
                {   
                    cArr = arr2.get(j).toCharArray(); // get the word at j and sort that word alphabetically
                    Arrays.sort(cArr);
                    currentWordLength = arr2.get(j).length();
                    counter =0;
                    countFile=0;
                    
                    if(arr3.get(i).equals (cArr[count]) && currentWordLength <=wordLength)
                    {

                        char [] w = arr2.get(j).toCharArray(); // this converts the word to char array
                        Arrays.sort(w); // sorts the char array alpahbetically

                        counter= i+1; // counter for input word,n
                        countFile++; // counter for word in file
                        
                        while(counter <args[0].length() && countFile < currentWordLength )
                        {
                            if(w[countFile] == arr3.get(counter)) 
                            {
                                countFile++;
                            }

                            counter++;
                        }

                        if(countFile == currentWordLength && currentWordLength >1&& !arrScramble.contains(arr2.get(j)))

                        {
                            arrScramble.add(arr2.get(j));

                        }

                    }
                }
                wordLength--;
            }

        }

        //if no words in the text file is contained within string n print no words found
        if(arrScramble.size() <1)
        {
            System.out.println("no words found");
            return;
        }

        Collections.sort(arrScramble, Comparator.comparing(String::length));  //sorts the list by length, by using a comparator
        int maxLength = arrScramble.get(arrScramble.size()-1).length(); // get the length of the largest word
        Collections.sort(arrScramble); // sort the words back in alphabetical order 

        while(maxLength >=1)
        {
            int i;

            for( i=0; i<arrScramble.size(); i++)  
            {
                
                if(arrScramble.get(i).length() == maxLength)
                {
                    finalList.add(arrScramble.get(i));
                }

                if(i == arrScramble.size()-1)
                {
                    break;
                }
            }
            i=0;
            maxLength--;
        }

        int lineLength =0;
        maxLength = finalList.get(0).length();
        int i=0;
        
        // this loop prints out the words 
        while(i<=finalList.size())
        {
            System.out.print("Words of length " + maxLength+" in "  + args[0] + " " +"\n");

            while(finalList.get(i).length() == maxLength  )
            {

                if(lineLength <40)
                {

                    System.out.print(finalList.get(i) + " ");
                    lineLength += finalList.get(i).length();
                }
                else
                {
                    System.out.println();
                    lineLength = 0;
                }

                if(i>=finalList.size()-1)
                {
                    
                    return;

                }
                i++;
                
            }

            System.out.println();
            System.out.println();
            lineLength = 0;
            maxLength = finalList.get(i).length();
        }   

    }
}