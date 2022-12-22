package connex;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Vector;

import objet.Personne;

import java.sql.Connection;
public class MyConnection {
    String username,password,database;

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setDatabase(String database) {
        this.database = database;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getDatabase() {
        return database;
    }
    public static Connection connectOracle(){
        Connection connection=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // connection=DriverManager.getConnection("jdbc:oracle:thin:@192.168.20.170:1521:orcl", "scott", "tiger");
            // System.out.println(connection);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return connection;
    }
    public static Connection connectPostgres(){
        Connection connection=null;
        try {
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5433/commerce","postgres","Passw0rd");
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
 
    public static Vector<Object> getObj(Object obj) throws Exception{
        Vector<Object> objList=new Vector<>();
        Connection c=connectOracle();
        Statement stmt=c.createStatement();
        ResultSet rs= stmt.executeQuery("select * from "+obj.getClass().getSimpleName());
        ResultSetMetaData rsmd= rs.getMetaData();
        for (int i = 0; i < rsmd.getColumnCount(); i++) {
            System.out.println(rs.getInt(rsmd.getColumnName(i)));
            // tokony asiana fonction hiconvertissena anle donnees any anaty base ho object  
        }
        return objList;
    }
    public static Object convertTObject(Object object) throws Exception{
        // Mandray anle resultset havadika ho objet
        Object obj=object.getClass().getName();

        // Mapiasa methode setter sy getter ampidirana azy tsirairay

        return obj;

    }
    public static String argtoTable(Field [] fields){
        String args="(";
        // Procedure amdihana anle field ho lasa column anle insert (....)
        for (Field field : fields) {
            args+= field.getName()+",";
        }
        args= args.substring(0, args.length()-1)+")";
        return args;
    }
    public static String fieldToString(Object object) throws Exception{
        String sql="(";
        Field[] fields= object.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object data=object.getClass().getDeclaredMethod("get"+capitalize(field.getName())).invoke(object);
            sql+= (data==null)? "null,"
            : (data.getClass()==String.class)? "\'"+data+"\' ,"
            : data+",";
        }
        return sql.substring(0, sql.length()-1)+")";
    }
    public static void insertData(Object object) throws Exception{ //Miantso fonction roa ilaina @ leh requete 
        Field [] field= object.getClass().getDeclaredFields();
        String sql="insert into "+object.getClass().getSimpleName() +" "+ argtoTable(field) +" values "+ fieldToString(object)+";";
        System.out.println("Requete :"+sql);
    }
    // public static String vect2args(Vector <String> args){
    //     String column="(";
    //     for (String string : args) {
    //         column+=
    //     }
        
    // }
    public static void update(Object object ,Vector <String> args){

    }
    public static String capitalize(String word){
        if(word==null || word.length()==0) return word;
        return word.substring(0, 1).toUpperCase()+word.substring(1);
        
    }
    public static void main(String[] args)throws Exception {
        // connectOracle();
        // Emp emp=new Emp(1521,"Jean","Police","2001/01/12",15000,200,40);
        // Field[] fields= emp.getClass().getDeclaredFields();
        // // insertData(emp); 
        // System.out.println(connectOracle());  
        
        Connection c=connectPostgres();
        Personne p=new Personne("huhu","haha");
        p.insert(p.getClass().getSimpleName(), p, c);
        c.close();
    }

}
