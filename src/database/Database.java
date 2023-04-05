package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;

public class Database implements DbAdapter{
    private static  Database db ;
    public Database(){

    }

   public boolean checkUser(String username) throws IOException{
    System.out.println("huobdoboeb");
    try{
        Path currentRelativePath = Paths.get("");
        String currentPath = currentRelativePath.toAbsolutePath().toString();
        String filePath = currentPath + "/database/db.txt";
        System.out.println(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();
        System.out.println(line);
        while ((line = reader.readLine()) != null) {
            


            if (line.contains(username)) {
                reader.close();
                return true;
            }
        }

        reader.close();
        return false;
    }

    catch(IOException e){
        System.out.println("database dosyasına bağlanamadı");
        return false;
    }
    
   }
}
