package sprint4Increment;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
/**
 * 
 * @author Shauna-Marie Grieve
 * 40010493
 * This class writes and reads objects from file.
 * In the ER PAS this class will be utilised to back-up the state  
 * of the system in case of power failure etc...
 *
 */
public class HospitalBackup {
 
 
 
 /**
  * @param object obj
  * @throws IOException
  * This method will write an object to file
  */
 public static void writeToFile(Object obj, String name) throws IOException{
        
  /**
   *   FILENAME = HospitalLog.temp
   */
  File FILENAME = new File("HospitalLog.temp"+name);
  //create a file output stream, pass FILENAME as param
  FileOutputStream fos = new FileOutputStream(FILENAME);
  
  //create object output stream, pass file out put stream as param
  ObjectOutputStream oos = new ObjectOutputStream(fos);
  
  //try to write the object to file
  try {  
  
  oos.writeObject(obj);
    
  }catch (IOException ex ){ ;}
  
  //make sure to close resources
  finally{
   oos.close();
  } }
 
 /**
  * 
  * @return Object from file
  * @throws IOException
  * method to read an object from file
  */
 public static Object readFromFile(String name)throws IOException{
  /**
   *   FILENAME = HospitalLog.temp
   */
  File FILENAME = new File("HospitalLog.temp"+name);
  
  // create a null object to store our returned object
  Object obj=null;
  
  //create a file input stream, pass FILENAME as param
  FileInputStream fis = new FileInputStream(FILENAME);
  
  //create object input stream, pass file out put stream as param
  ObjectInputStream ois = new ObjectInputStream(fis);
  
  
   //try to read the object from file and save in obj
   try {
    obj=ois.readObject();
   } catch (ClassNotFoundException e) {
    e.printStackTrace();
   }
   //make sure to close resources
   finally{
    ois.close();
   }
 
  //return obj to calling class/method
  return obj;
  
 }
 
 public static void writeBoolean(boolean b,String name){
	   
	    String FILENAME =("HospitalLog.temp"+name);
	   
	    try
	    {
	      //create FileOutputStream object
	      FileOutputStream fos = new FileOutputStream(FILENAME);
	     
	      /*
	       * To create DataOutputStream object from FileOutputStream use,
	       * DataOutputStream(OutputStream os) constructor.
	       *
	       */
	     
	       DataOutputStream dos = new DataOutputStream(fos);
	     
	       boolean a = b;
	     
	       /*
	        * To write a boolean value to a file, use
	        * void writeBoolean(boolean b) method of Java DataOutputStream class.
	        *
	        * This method writes specified boolean to output stream as a 1 byte (true
	        * value is written out as 1 whereas false as 0)
	        */
	       
	        dos.writeBoolean(a);
	       
	        /*
	         * To close DataOutputStream use,
	         * void close() method.
	         *
	         */
	       
	         dos.close();
	       
	    }
	    catch (IOException e)
	    {
	      System.out.println("IOException : " + e);
	    }
	 
	  }
 
 public static boolean readBoolean(String name) throws IOException{
	 boolean available=false;
	 try
	    {
	      //create FileOutputStream object
	      FileInputStream fis = new FileInputStream(name);
	     
	      /*
	       * To create DataOutputStream object from FileOutputStream use,
	       * DataOutputStream(OutputStream os) constructor.
	       *
	       */
	     
	       DataInputStream dos = new DataInputStream(fis);   
	       /*
	        * To write a boolean value to a file, use
	        * void writeBoolean(boolean b) method of Java DataOutputStream class.
	        *
	        * This method writes specified boolean to output stream as a 1 byte (true
	        * value is written out as 1 whereas false as 0)
	        */
	       
	        available= dos.readBoolean();
	       
	        /*
	         * To close DataOutputStream use,
	         * void close() method.
	         *
	         */
	       
	         dos.close();
	       
	    }
	    catch (IOException e)
	    {
	      System.out.println("IOException : " + e);
	    }
	 
	 
	 return  available;
 }
 
}