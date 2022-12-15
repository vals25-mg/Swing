package fenetre;

import java.awt.*;
import java.io.*;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import listener.ListenerClient;

public class WindowClient extends JFrame{
    int port;
    JButton choose,send;
    File fichier;
    JLabel showFile;

    
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
            this.setSize(500, 500);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        /*@File Declaration */
        
        /*@Declarations of JLabel */
        JLabel title=new JLabel();
        title.setFont(new Font("Arial",Font.BOLD,20));
        title.setText("UPLOAD / DOWNLOAD A FILE");
        title.setBounds(50,20, 400, 75);

        setShowFile(new JLabel());
        getShowFile().setFont(new Font("Arial",Font.BOLD,20));
        getShowFile().setBounds(70, 120, 200, 50);
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
        this.add(title);
        this.add(this.getChoose());
        this.add(this.getSend());
   }
   public void changeMessage(String newText){
    getShowFile().setForeground(Color.GREEN);
    getShowFile().setText(newText);
   }
   public static void main(String[] args) {
    new WindowClient();
   }



    
}
