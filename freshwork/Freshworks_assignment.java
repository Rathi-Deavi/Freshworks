/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freshworks_assignment;
import java.io.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Freshworks_assignment {
       static File file=new File("D:/output.txt");
       
      
        static JSONObject create(String Rollno){
        Scanner details=new Scanner(System.in);
        String name,Address;
        int age;
        long phoneNumber;
        System.out.println("Enter the Student Name:");
        name=details.next();
        System.out.println("Enter the Student age:");
        age=details.nextInt();
        System.out.println("Enter the Student phone number:");
        phoneNumber=details.nextLong();
        System.out.println("Enter the Student Address:");
        Address=details.next();
        JSONObject obj = new JSONObject();
        obj.put("Rollno", Rollno);
        obj.put("Name", name);
        obj.put("Age", age);
        obj.put("PhoneNumber", phoneNumber);
        obj.put("Address", Address);
        return obj;
    }
    static void delete(String Key,Map<String,JSONObject> details){
        if(details.containsKey(Key)==true){
        details.remove(Key);
            System.out.println("data deleted successfully");
        
        try{
            FileWriter f=new FileWriter(file,true);
            for(Map.Entry data:details.entrySet()){
                f.write(data.getKey()+"=>"+data.getValue());
                f.append("\n");
            }
            f.close();
        }
        catch(Exception ex){
            
        }
        }
        else{
            System.out.println("Key does not exist");
        }
        
    }
    public static void main(String[] args) {
        Map<String,JSONObject> School_details=new LinkedHashMap<>();
        Scanner scan=new Scanner(System.in);
        JSONObject createdJson;
        BufferedReader br = null;
        
        try{
        if(file.exists()){
               if(file.length()!=0){
                   br = new BufferedReader( new FileReader(file) );
                    String line = null;
                    while ( (line = br.readLine()) != null ){
                     String[] parts = line.split("=>");
                     String name = parts[0].trim();
                     if( !name.equals("") && !parts[1].equals("") ){
                     JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(parts[1].trim());
                           School_details.put(name,json);
                     }
               }
                    br.close();
            }
               else{
                   System.out.println("File is empty");
               }
               
        }
        else{
            file.createNewFile();
            System.out.println("New File is created");
        }
      }
        catch(Exception ex){
            
        }
        System.out.println("Enter the option from the given below:::");
        for(;;){
            System.out.println("1.create  2.Read   3.Delete 4.exit");
            int option=scan.nextInt();
            switch(option){
                case 1:
                    System.out.println("Enter the rollno:");
                    String Rollno=scan.next();
                    if(School_details.containsKey(Rollno)==false){
                        createdJson=create(Rollno);
                        School_details.put(Rollno, createdJson);
                        System.out.println("Data created successfully");
                        try{
                            
                        FileWriter fw=new FileWriter(file,true);
                            
                        fw.write(Rollno+"=>"+createdJson);
                        fw.append("\n");
                        fw.close();
                            
                           
                        }
                        catch(Exception ex){
                            
                        }
                        
                    }
                    else
                    {
                        System.out.println("The user name already exists");
                    }
                      break; 
                case 2:
                    System.out.println("Enter the Key to search:");
                    String KeytoSearch=scan.next();
                    if(School_details.containsKey(KeytoSearch)==true)
                        System.out.println(School_details.get(KeytoSearch));
                    else
                        System.out.println("Key does not exist");
                   break;
                case 3:
                    System.out.println("Enter the key to delete:");
                    String Key=scan.next();
                    delete(Key,School_details);
                    break;
                default:
                    System.exit(0);
            }
                
        }
        
       
            
     
    }
    
}
