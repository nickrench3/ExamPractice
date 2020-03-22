/*
 * Name: Nick Rentschler
 * Date: 12-10-2019
 * Program: Enter the exam key in one text file,
 * enter the answers in another and compare to see what is wrong.
 * 
 */

import java.util.*;
import java.io.*;
import javax.swing.*;

class ExamScore {
    public static void main (String[] args) throws IOException {
        String fileKey, fileAnswer, numQuestions;
        String[] answers, entry, wrongArray;
        int score, count;
        double average;
        Scanner inFile;
        
        // Call to method to count the number of questions 
        count = countQuestions();
        
        // File where the results will be printed
        PrintWriter outFile = new PrintWriter(new File("output.txt"));
       
        // File where the answer key is and puts it into an array
        inFile = new Scanner(new FileReader("key.txt"));
        answers = inputArray(inFile, count);
        
        // File where the user entered answers and puts it into an array
        inFile = new Scanner(new FileReader("answer.txt"));
        entry = inputArray(inFile, count);
        
        // Call to method to return an array that puts wrong answers into an array
        wrongArray = wrongArray(answers, entry, count);
        
        // Call to method to return the number of right answers
        score = score(answers, entry);
        
        // Call to method to print the number correct, wrong answers
        outputWrong(wrongArray, answers, outFile, score, count);
        
    }
    
    public static String[] inputArray(Scanner inFile, int count) {
        String[] array;
        
        array = new String[count];
        
        for(int i=0; i<array.length; i++)
            array[i] = inFile.nextLine();
        inFile.close();
        return array;
    }
    
    public static int score(String[] array1, String[] array2) {
        int count = 0;
        for(int i=0; i<array1.length; i++){
            if(array1[i].equals(array2[i]))
                count++;
        }
        return count;
    }
    
    public static String[] wrongArray(String[] array1, String[] array2, int count) {
        String[] array;
        array = new String[count];
        int position = 0;
        
        for(int i=0; i<array1.length; i++) {
            position++;
            if(!array1[i].equals(array2[i]))
                array[i] = Integer.toString(position);
           
        }
        return array;
    }
    
    public static void outputWrong(String[] array, String[] array2, PrintWriter outFile, int score, int count) {
        outFile.println("You got a " + score + " out of " + count);
        outFile.print("The questions you got wrong with their correct answers are numbers: ");
        for(int i=0; i<array.length; i++){
            if(array[i]!=null){
                outFile.print(array[i] + "-");
                outFile.print(array2[i] + ", ");
            }
        }
        outFile.println();
        outFile.close();
    }
    
    public static int countQuestions() throws IOException  {
        BufferedReader reader = new BufferedReader(new FileReader("key.txt"));
        int lines = 0;
        while (reader.readLine() != null) {
            lines++;
        }
        reader.close();
        
        return lines;
    }
        
}
       