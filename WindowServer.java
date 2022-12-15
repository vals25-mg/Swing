package fenetre;

import javax.swing.JFrame;
 
public class WindowServer extends JFrame{

    public WindowServer() {

            /*  Property of the JFrame */
            this.setTitle("Server");
            this.setLayout(null);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.setSize(500, 500);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}