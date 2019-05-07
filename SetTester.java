import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class SetTester {

    public static void main(String[] args){

    	ISet<String> s1 = new UnsortedSet<String>();
        s1.add("A");
        s1.add("A");
        s1.add("B");
        s1.add("A");
        
    	ISet<String> s2 = new UnsortedSet<String>();
        s2.add("B");
        s2.add("B");
        s2.add("B");
        s2.add("C");

        ISet<String> expected = new UnsortedSet<String>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        
        ISet<String> s3 = new UnsortedSet<String>();
       
        //test 1 Abstract AddAll
        s1.addAll(s2);
        if(s1.equals(expected))
            System.out.println("Passed test 1: abstract addall");
        else
            System.out.println("Failed test 1: abstract addall");

        //test 2 abstract clear
        s1.clear();
        expected.clear();
        if(s1.equals(expected))
            System.out.println("Passed test 2: abstract clear");
        else
            System.out.println("Failed test 2: abstract clear");
        
        //test 3 abstract contains
        if(s2.contains("B"))
            System.out.println("Passed test 3: abstract contains");
        else
            System.out.println("Failed test 3: abstract contains");
        
        //test 4 abstract containsAll on empty
        s1.clear();
        s2.clear();
        if(s2.containsAll(s1))
            System.out.println("Passed test 4: abstract containsAll");
        else
            System.out.println("Failed test 4: abstract containsAll");
        
        //test 5 abstract remove
        s1.add("A");
        s1.remove("A");
        if(!s1.contains("A"))
            System.out.println("Passed test 5: abstract remove");
        else
            System.out.println("Failed test 5: abstract remove");
        
        //test 6 abstract size
        if(s1.size() == 0)
            System.out.println("Passed test 6: abstract size");
        else
            System.out.println("Failed test 6: abstract size");
        
        //test 7 abstract equals
        s1.add("C");
        s2.add("C");
        if(s1.equals(s2))
            System.out.println("Passed test 7: abstract equals");
        else
            System.out.println("Failed test 7: abstract equals");
        
        //test 8 abstract union
        s1.clear();
        s2.clear();
        s1.add("A");
        s2.add("B");
        expected.add("A");
        expected.add("B");
        s3 = s1.union(s2);
        if(s3.equals(expected))
            System.out.println("Passed test 8: abstract union");
        else
            System.out.println("Failed test 8: abstract union");
        
        //test 9 unsorted add to already existing
        s1.add("A");
        expected.clear();
        expected.add("A");
        if(s1.equals(expected))
            System.out.println("Passed test 9: unsorted add");
        else
            System.out.println("Failed test 9: unsorted add");
        
        //test 10 unsorted iterator
        s1.add("A");
        s1.add("B");
        s2.add("A");
        Iterator<?> it1 = s1.iterator();
        Iterator<?> it2 = s2.iterator();
        if(it1.hasNext() && it2.hasNext())
            System.out.println("Passed test 10: unsorted iterator");
        else
            System.out.println("Failed test 10: unsorted iterator");
        
        //test 11 unsorted difference
        s3 = s1.difference(s2);
        if(s3.size() == 0)
            System.out.println("Passed test 11: unsorted difference");
        else
            System.out.println("Failed test 11: unsorted difference");
        
        //test 12 unsorted equals
        s1.clear();
        s2.clear();
        s1.add("C");
        s2.add("Y");
        if(!s1.equals(s2))
            System.out.println("Passed test 12: unsorted equals");
        else
            System.out.println("Failed test 12: unsorted equals");
        
        //test 13 unsorted intersection
        s1.add("Y");
        s3 = s1.intersection(s2);
        if(s3.contains("Y"))
            System.out.println("Passed test 13: unsorted intersection");
        else
            System.out.println("Failed test 13: unsorted intersection");
        
        //test 14 unsorted size
        if(s1.size() == 2)
            System.out.println("Passed test 14: unsorted size");
        else
            System.out.println("Failed test 14: unsorted size");
        
        //test 15 sorted set constructor
        s1.clear();
        expected.clear();
        s1.add("B");
        s1.add("A");
        expected.add("A");
        expected.add("B");
        s1 = new SortedSet<String>(s1);
        s2 = new SortedSet<String>(s2);
        s3 = new SortedSet<String>(s3);
        expected = new SortedSet<String>(expected);
        
        if(s1.equals(expected))
            System.out.println("Passed test 15: sorted constructor");
        else
            System.out.println("Failed test 15: sorted constructor");
       
        //test 16 sorted min
        if(((SortedSet<?>)s1).min().equals("A"))
            System.out.println("Passed test 16: sorted min");
        else
            System.out.println("Failed test 16: sorted min");
        
        //test 17 sorted max
        if(((SortedSet<?>)s1).max().equals("B"))
            System.out.println("Passed test 17: sorted max");
        else
            System.out.println("Failed test 17: sorted max");
        
        //test 18 sorted add
        s1.add("A");
        if(s1.equals(expected))
            System.out.println("Passed test 18: sorted add");
        else
            System.out.println("Failed test 18: sorted add");
        
        //test 19 sorted addAll
        s3.clear();
        s3.add("C");
        s3.add("D");
        s1.addAll(s3);
        expected.add("C");
        expected.add("D");
        if(s1.equals(expected))
            System.out.println("Passed test 19: sorted addall");
        else
            System.out.println("Failed test 19: sorted addall");
        
        //test 20 sorted contains
        s1.clear();
        s2.clear();
        s3.clear();
        expected.clear();
        s1.add("A");
        s1.add("B");
        if(s1.contains("A"))
            System.out.println("Passed test 20: sorted contains");
        else
            System.out.println("Failed test 20: sorted contains");
        
        //test 21 sorted containsAll
        s2.add("A");
        s2.add("B");
        if (s2.containsAll(s1))
            System.out.println("Passed test 21: sorted containsAll");
        else
            System.out.println("Failed test 21: sorted containsAll");
        
        //test 22 sorted iterator 
        Iterator<?> it3 = s1.iterator();
        Iterator<?> it4 = s2.iterator();
        if(it3.hasNext() && it4.hasNext()) 
            System.out.println("Passed test 22: sorted iterator");
        else
            System.out.println("Failed test 22: sorted iterator");
        

        //test 23 sorted difference 
        s2.clear();
        s2.add("A");
        s2.add("B");
        s1.add("A");
        s3 = s2.difference(s1);
        if(s3.equals(expected)) 
            System.out.println("Passed test 23: sorted difference");
        else
            System.out.println("Failed test 23: sorted difference");
             
        
        //test 24 sorted equals
        s1.clear();
        s2.clear();
        s3.clear();
        expected.clear();
        s2.add("B");
        s1.add("G");
        if(!s1.equals(s2)) 
            System.out.println("Passed test 24: sorted equals");
        else
            System.out.println("Failed test 24: sorted equals");
        
        //test 25 sorted intersection
        s1.add("B");
        expected = s1.intersection(s2);
        expected.add("B");
        if(!s1.equals(s2)) 
            System.out.println("Passed test 25: sorted intersection");
        else
            System.out.println("Failed test 25: sorted intersection");
        
        //test 26 sorted remove 
        s1.clear();
        s1.add("A");
        s1.remove("A");
        if(s1.size() == 0) 
            System.out.println("Passed test 26: sorted remove");
        else
            System.out.println("Failed test 26: sorted remove");
        
        //test 27 sorted size
        s1.add("A");
        s1.add("A");
        s1.add("I");
        s1.add("Z");
        if(s1.size() == 3)
        	System.out.println("Passed test 27: sorted size");
        else
        	System.out.println("Failed test 27: sorted size");
        
        //test 28 sorted union
        s1.clear();
        s2.clear();
        s1.add("A");
        s2.add("B");
        expected.clear();
        expected.add("A");
        expected.add("B");
        s3 = s1.union(s2);
        if(s3.equals(expected))
        	System.out.println("Passed test 28: sorted union");
        else
        	System.out.println("Failed test 28: sorted union");

        
        
     			try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch(Exception e) {
                    System.out.println("Unable to change look and feel");
                }
        		Scanner sc = new Scanner(System.in);
        		String response = "";
        		do {
        			largeTest();
        			System.out.print("Another file? Enter y to do another file: ");
        			response = sc.next();
        		} while( response != null && response.length() > 0 
                      && response.substring(0,1).equalsIgnoreCase("y") );

    }

    /*
     * Method asks user for file and compares run times to add words from file to
     * various sets, including CS314 UnsortedSet and SortedSet and Java's
     * TreeSet and HashSet.
     */
    private static void largeTest(){
        System.out.println();
        System.out.println("Opening Window to select file. You may have to minimize other windows.");
        String text = convertFileToString();
        System.out.println();
        System.out.println("***** CS314 SortedSet: *****");
        processTextCS314Sets(new SortedSet<String>(), text);
        System.out.println("****** CS314 UnsortedSet: *****");
        processTextCS314Sets(new UnsortedSet<String>(), text);
        System.out.println("***** Java HashSet ******");
        processTextJavaSets( new HashSet<String>(), text);
        System.out.println("***** Java TreeSet ******");
        processTextJavaSets( new TreeSet<String>(), text);
    }

    
    /*
     * pre: set != null, text != null
     * Method to add all words in text to the given set. Words are delimited by
     * white space.
     * This version for CS314 sets.
     */
    private static void processTextCS314Sets(ISet<String> set, String text){
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while( sc.hasNext() ){
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size());
    }


    /*
     * pre: set != null, text != null
     * Method to add all words in text to the given set. Words are delimited by
     * white space.
     * This version for Java Sets.
     */
    private static void processTextJavaSets(Set<String> set, String text){
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while( sc.hasNext() ){
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size());
    }

    
    /*
     * Show results of add words to given set.
     */
    private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, 
            int totalWords, int setSize) {

        System.out.println("Time to add the elements in the text to this set: " + s.toString() );
        System.out.println("Total number of words in text including duplicates: " + totalWords);
        System.out.println("Number of distinct words in this text " + setSize);


        System.out.print("Enter y to see the contents of this set: ");
        Scanner sc = new Scanner(System.in);
        String response = sc.next();

        if( response != null && response.length() > 0 && response.substring(0,1).equalsIgnoreCase("y") ){
            for(Object o : set)
                System.out.println(o);
        }	
        System.out.println();
    }


    /*
     * Ask user to pick a file via a file choosing window and
     * convert that file to a String. Since we are evalutatin the file
     * with many sets convert to string once instead of reading through
     * file multiple times.
     */
    private static String convertFileToString() {
        //create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        StringBuilder text = new StringBuilder();
        int retval = chooser.showOpenDialog(null);

        chooser.grabFocus();

        //read in the file
        if (retval == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            try {
                Scanner s = new Scanner( new FileReader( source ) );

                while( s.hasNextLine() ) {
                    text.append( s.nextLine() );
                    text.append(" ");
                }

                s.close();
            }
            catch(IOException e) {
                System.out.println("An error occured while trying to read from the file: " + e);
            }
        }

        return text.toString();
    }
}z
