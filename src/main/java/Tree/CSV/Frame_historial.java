package Tree.CSV;
import javax.swing.*;
import java.awt.*;

/**
 * @author Carolina Rodriguez , Kendall Marin
 *
 */

public class Frame_historial {
    public static JPanel panel;
    public Frame_historial() {
        JFrame frame1;
        frame1 = new JFrame("Historial");
        frame1.setBounds(0, 0, 350, 500);
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(900, 500);
        frame1.add(panel);
        frame1.setVisible(true);

        JLabel his;
        his = new JLabel();
        his.setFont(new Font("MV Boli", Font.PLAIN, 15));
        his .setBounds(20, 170, 1000, 40);
        his.setText("");
        panel.add(his);


    }
}