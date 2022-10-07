import javax.swing.*;
import java.awt.*;

public class PanelTablero extends JPanel {


    static Tablero tablero = new Tablero();
    boolean[][] universe;

    PanelTablero(){
        Canvas aliveCell = new Canvas();
        Canvas deadCell = new Canvas();
        aliveCell.setBackground(Color.WHITE);
        deadCell.setBackground(Color.BLACK);
        universe = Tablero.universeInicializer();
        GridLayout gridLayout = new GridLayout(universe.length, universe.length);
        this.setLayout(gridLayout);
        this.setPreferredSize(new Dimension(universe.length*20,universe.length*20));
        for (int i = 0; i < universe.length; i++) {
            for (int j = 0; j < universe.length; j++) {
                if(universe[i][j])
                    this.add(aliveCell);
                else
                    this.add(deadCell);
            }
        }
    }
}
