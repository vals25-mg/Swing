package listener;

import java.awt.event.*;

import javax.swing.JFileChooser;

import fenetre.WindowClient;

public class ListenerClient implements ActionListener{
  
    WindowClient windowClient;

    public WindowClient getWindowClient() {
        return windowClient;
    }


    public void setWindowClient(WindowClient windowClient) {
        this.windowClient = windowClient;
    }
    
    
    public ListenerClient(WindowClient windowClient) {
        this.windowClient = windowClient;
    }


    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub 
        if (arg0.getSource()==getWindowClient().getChoose()) {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
               System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
            }
        }
        if (arg0.getSource()==getWindowClient().getSend()) {
            System.out.println("Send File");
        }
        
    }


    
}
