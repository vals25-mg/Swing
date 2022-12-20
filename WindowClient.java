package fenetre;

import java.awt.*;
import java.io.*;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.net.Socket;

import listener.ListenerClient;

public class WindowClient extends JFrame{
    int port;
    JButton choose,send;
    File fichier;
    JLabel showFile;
    JTable tableUpload,tableDownload;
    
    
    public JTable getTableUpload() {
        return tableUpload;
    }

    public void setTableUpload(JTable tableUpload) {
        this.tableUpload = tableUpload;
    }

    public JTable getTableDownload() {
        return tableDownload;
    }

    public void setTableDownload(JTable tableDownload) {
        this.tableDownload = tableDownload;
    }

    public JLabel getShowFile() {
        return showFile;
    }

    public void setShowFile(JLabel showFile) {
        this.showFile = showFile;
    }

    public File getFichier() {
        return fichier;
    }
    
    public void setFichier(File fichier) {
        this.fichier = fichier;
    }

    public JButton getChoose() {
        return choose;
    }

    public void setChoose(JButton choose) {
        this.choose = choose;
    }

    public JButton getSend() {
        return send;
    }

    public void setSend(JButton send) {
        this.send = send;
    }
    
    public int getPort() {
        return port;
    }
    
    public void setPort(int port) {
        this.port = port;
    }
    
    public WindowClient() {

        /*  @Property of the JFrame */
            this.setTitle("Client");
            this.setLayout(null);
            // this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.setSize(500, 520);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        /*JTable */  
        String[] columnnames={ "First Name",
                                "Last Name",
                                "Sport",
                                "# of Years",
                                "Vegetarian"
        };
        Object[][] data={
            {"Kathy","Smith","SnowBoarding",5,false},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true},
            {"John","Doe","Rowing",3,true}
        };
        setTableUpload(new JTable(data, columnnames));  
        JScrollPane scrollpane=new JScrollPane(getTableUpload());
        scrollpane.setBounds(0, 0, 400, 200);

        /*File */
        

        /*@Declaration of JPanel */
        JPanel jp=new JPanel(null);
        jp.setBounds(0, 0, 500, 500);
        JPanel jp1=new JPanel(null);
        jp1.setLayout(null);
        jp1.setBounds(0, 0, 500, 500);

        /*@Declaration NavBar*/
        JTabbedPane onglets=new JTabbedPane();
        onglets.setBounds(0, 0, 500, 500);
        onglets.add("Download",jp);
        onglets.add("Upload",jp1);

        /*@Declarations of JLabel */
        JLabel title=new JLabel();
        title.setFont(new Font("Arial",Font.BOLD,20));
        title.setText("UPLOAD / DOWNLOAD A FILE");
        title.setBounds(50,20, 400, 75);

        setShowFile(new JLabel());
        getShowFile().setFont(new Font("Arial",Font.BOLD,20));
        getShowFile().setBounds(10, 120, 350, 50);
        System.out.println(getFichier());
            getShowFile().setText("No File Selected");
            getShowFile().setForeground(Color.RED);

        /* @Declaration of JButtons*/
        setChoose(new JButton("CHOOSE"));
        getChoose().setFont(new Font("Arial",Font.TRUETYPE_FONT,14));

        setSend(new JButton("SEND"));
        getSend().setFont(new Font("Arial",Font.TRUETYPE_FONT,14));
        System.out.println(1);

        /*@Location of all Components */
        getChoose().setBounds(150, 400, 100, 50);
        getSend().setBounds(250, 400, 100, 50);

        /*@Add all Listeners */
        ListenerClient listenerClient=new ListenerClient(this);
        getChoose().addActionListener(listenerClient);
        getSend().addActionListener(listenerClient);

        /*@Add All Components */
        // jp.add(title);
        jp.add(getShowFile());
        jp.add(this.getChoose());
        jp.add(this.getSend());


        jp1.add(scrollpane);

        this.add(onglets);
   }
   public void changeMessage(String newText){
    getShowFile().setForeground(Color.GREEN);
    getShowFile().setText(newText);
   }
   public void connectServer(){

   }
   public void send() throws Exception{
    FileInputStream fileInputStream= new FileInputStream(getFichier().getAbsolutePath());
                Socket socket= new Socket("localhost", 1234);

                DataOutputStream dataOutputStream= new DataOutputStream(socket.getOutputStream());

                String filename=getFichier().getName();
                byte[] fileNameBytes= filename.getBytes();

                byte[] fileContentBytes=new byte[(int)getFichier().length()];
                
                fileInputStream.read(fileContentBytes);

                dataOutputStream.writeInt(fileNameBytes.length);
                dataOutputStream.write(fileNameBytes);

                dataOutputStream.writeInt(fileContentBytes.length);
                dataOutputStream.write(fileContentBytes);
   }
   public static void main(String[] args) {
    new WindowClient();
   }    
}
