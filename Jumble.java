// Imports for using hashset and Sets
import java.util.*;
// Imports for doing file operations
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

 
public class Jumble {
	public static Set<String> dictionary= null;
	public static Set<String> validWords= null;
	public static void main(String[] args) {
		// Check if a valid string is passed in and dictionary is passed in
		if(args.length < 2) {
			System.out.println("Usage: java <executable> string dictionary");
			System.exit(1);
		}
		Jumble obj = new Jumble();
		Jumble.dictionary = new HashSet<String>();
		Jumble.validWords = new HashSet<String>();
		try {
		        BufferedReader in = new BufferedReader(new FileReader(args[1]));
	        	String word = null;
		        while ((word = in.readLine()) != null) {
			    Jumble.dictionary.add(word);
		        }
		        in.close();
		} catch(Exception e) {
			System.out.println("Failed to open dictionary and read data");
			System.exit(1);
		}
		try {	
			Set<String> combinations = obj.generateCombinations(args[0], new StringBuffer (),0);
			Iterator iter = combinations.iterator();
			while (iter.hasNext()) {
				// For each combination generate permutations and check if they are valid words
				obj.generatePermutations("",iter.next().toString());
			}
		} catch(Exception e) {

			System.out.println("Failed to open dictionary and read data");
			System.exit(1);
		}
		System.out.println(validWords.toString());
		
	}
	/**
		Generate the combinations of the string, so basically given a string ab, this method generates a,b,ab
	*/
	private Set<String> generateCombinations(String input, StringBuffer output, int index)
	{
		Set<String> combinations = new HashSet<String>();
		for (int i = index; i < input.length(); i++)
		{
			output.append(input.charAt(i));
			combinations.add(output.toString());
			combinations.addAll(this.generateCombinations(input, output, i + 1));
			output.deleteCharAt(output.length()-1);
		}
		return combinations;
	}
	/**
		Generate the permutations of the string, and check if its a valid word, so basically given a string dog, you generate dog, god ogd and insert it in the dictionary only if its a valid word.
	*/
	private void generatePermutations(String prefix, String str) {
	    int n = str.length();
    	    if (n == 0) {
		if(Jumble.dictionary.contains(prefix)) { // Check if its a valid dictionary word add to valid list
			Jumble.validWords.add(prefix);
		} 
	    }
    	    else {
        	for (int i = 0; i < n; i++)
            	  this.generatePermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
    	    }
 	}				
}








