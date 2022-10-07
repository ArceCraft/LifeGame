import javax.swing.*;

public class Frame extends JFrame {

    PanelTablero panel;

    Frame(){
        panel  = new PanelTablero();
        this.setLocationRelativeTo(null);
        this.add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

}
