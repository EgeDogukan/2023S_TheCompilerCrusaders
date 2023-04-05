package database;



public class DbObject {
    private String username;
    private String password;
    private static DbObject db;
    
    private DbObject(){

    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
