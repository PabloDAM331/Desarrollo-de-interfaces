package t1.pkg4_tres_en_raya;
/**
 *
 * @author pablo
 */
import javax.swing.*;
import java.awt.*;

public class VentanaTresEnRaya extends JFrame {

    public JButton[] botones = new JButton[9];
    public static final Color COLOR_AZUL = new Color(41, 128, 185);
    public static final Color COLOR_ROJO = new Color(192, 57, 43);
    public static final Color FONDO_BOTON = new Color(236, 240, 241);

    public VentanaTresEnRaya() {
        setTitle("3 en Raya");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3, 5, 5)); 
        getContentPane().setBackground(Color.DARK_GRAY);

        // Crear los 9 botones
        for (int i = 0; i < 9; i++) {
            JButton boton = new JButton();
            boton.setFont(new Font("Arial", Font.BOLD, 60));
            boton.setFocusPainted(false);
            boton.setBackground(FONDO_BOTON);
            boton.setOpaque(true);
            boton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            
            botones[i] = boton;
            add(boton);
        }

        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
