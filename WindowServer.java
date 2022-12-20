package fenetre;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import objets.MyFile;
 
public class WindowServer extends JFrame{

    DefaultTableModel dm= new DefaultTableModel(0,0);
    
    public WindowServer() throws Exception {

        
        /*  Property of the JFrame */
        this.setTitle("Server Principale");
            this.setLayout(null);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.setSize(500, 500);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel pan=new JPanel(null);
            pan.setBounds(0, 0, this.getWidth(), this.getHeight());
            this.add(pan);

            /*JTable */
            Vector<MyFile> vFiles=new Vector<>();
            JTable tableUpload=new JTable();
            String header []= new String[] {"n°","FileName","FileSize"};
            getDm().setColumnIdentifiers(header);
            tableUpload.setModel(getDm());
            JScrollPane jPane=new JScrollPane(tableUpload);
            jPane.setBounds(50, 0, 400, 450);
            pan.add(jPane);
             
            try {
                
                vFiles=getFromfile(new File("Server Principal/memo.txt"));
                addRow(vFiles, getDm());
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        
            /*Socket */
        ServerSocket serverSocket=new ServerSocket(1234);
        while(true){
            Socket socket=serverSocket.accept();
            DataInputStream dataInputStream= new DataInputStream(socket.getInputStream());

            int filenameLength= dataInputStream.readInt();

            if (filenameLength>0) {
                byte[] fileNameBytes=new byte[filenameLength];
                dataInputStream.readFully(fileNameBytes,0,fileNameBytes.length);
                String filename= new String(fileNameBytes);

                int fileContentLength= dataInputStream.readInt();

                if (fileContentLength>0) {
                    byte [] fileContentBytes=new byte[fileContentLength];
                    dataInputStream.readFully(fileContentBytes, 0, fileContentLength);

                    MyFile myFile=new MyFile(filename,fileContentLength);
                    File fileToDownload=new File("Server Principal/"+filename);
                    try {
                        FileOutputStream fileOutputStream= new FileOutputStream(fileToDownload);
                        fileOutputStream.write(fileContentBytes);
                        fileOutputStream.close();
                        writeFile(myFile);
                        vFiles=getFromfile(new File("Server Principal/memo.txt"));
                        addRow(vFiles, getDm());
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public  void writeFile(Object o) throws Exception{
        File file=new File("Server Principal/memo.txt");
         Vector v=new Vector<>();
        if (!file.exists()) {
         System.out.println("tsy misy atramzao");
         file.createNewFile();
         System.out.println("File Created");
        }
        
     //    System.out.println("Le vecteur inscrit: "+v.get(0));
        else{
         System.out.println("file already exists");
         v=getFromfile(file);
        }
        v.add(o);
       
     FileOutputStream fileOut=new FileOutputStream(file);
        ObjectOutputStream objectOut=new ObjectOutputStream(fileOut);
         objectOut.writeObject(v);
         fileOut.close();
         objectOut.close();
         System.out.println("Vector insered");
     }
     public  Vector getFromfile(File file) throws IOException, ClassNotFoundException{
         
         Vector v=new Vector<>();
         FileInputStream fin=new FileInputStream(file);
         ObjectInputStream in=new ObjectInputStream(fin);
         v=(Vector)in.readObject();
         fin.close();
         in.close();
         return v;
     }
     public void addRow(Vector <MyFile> vFiles,DefaultTableModel dModel){
        for (int i = dModel.getRowCount(); i>0; i--) {
            dModel.removeRow(i);
        }
        for (int i = 0; i < vFiles.size(); i++) {
            Vector <Object> data= new Vector<Object>();
            data.add(i);
            data.add(vFiles.get(i).getName());
            data.add(vFiles.get(i).getSize()+" octets");
            dModel.addRow(data);
        }
     }
    
    public static void main(String[] args) {
        try {
            new WindowServer();
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    public DefaultTableModel getDm() {
        return dm;
    }
    public void setDm(DefaultTableModel dm) {
        this.dm = dm;
    }
}