package t1.pkg2calculadora;
/**
 *
 * @author pablo
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CalculadoraVista extends JFrame {

    private static final Color FONDO = Color.BLACK;

    public JTextField display;
    public BotonCalculadora btnResultado;
    public BotonCalculadora[] botonesNumeros = new BotonCalculadora[10];
    public BotonCalculadora btnPunto, btnSuma, btnResta, btnMulti, btnDiv, btnLimpiar;

    public CalculadoraVista() {
        setTitle("Calculadora");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(FONDO);
        setResizable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        //  DISPLAY 
        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(Color.BLACK);
        display.setForeground(new Color(0, 255, 0));      // verde brillante
        display.setFont(new Font("Arial", Font.PLAIN, 40));
        display.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 1));
        display.setCaretColor(Color.GREEN);
        

        gbc.gridy = 0;
        gbc.weighty = 0.18;
        gbc.insets = new Insets(15, 15, 10, 15);
        add(display, gbc);

        // BOTÓN RESULTADO 
        btnResultado = new BotonCalculadora("Resultado");
        btnResultado.setFuenteResultado();

        gbc.gridy = 1;
        gbc.weighty = 0.18;
        gbc.insets = new Insets(0, 15, 10, 15);
        add(btnResultado, gbc);

        // TECLADO 4x4
        JPanel teclado = new JPanel(new GridLayout(4, 4, 8, 8));
        teclado.setBackground(FONDO);

        // Fila 1: 7 8 9 /
        botonesNumeros[7] = new BotonCalculadora("7");
        botonesNumeros[8] = new BotonCalculadora("8");
        botonesNumeros[9] = new BotonCalculadora("9");
        btnDiv             = new BotonCalculadora("/");
        teclado.add(botonesNumeros[7]);
        teclado.add(botonesNumeros[8]);
        teclado.add(botonesNumeros[9]);
        teclado.add(btnDiv);

        // Fila 2: 4 5 6 *
        botonesNumeros[4] = new BotonCalculadora("4");
        botonesNumeros[5] = new BotonCalculadora("5");
        botonesNumeros[6] = new BotonCalculadora("6");
        btnMulti           = new BotonCalculadora("*");
        teclado.add(botonesNumeros[4]);
        teclado.add(botonesNumeros[5]);
        teclado.add(botonesNumeros[6]);
        teclado.add(btnMulti);

        // Fila 3: 1 2 3 -
        botonesNumeros[1] = new BotonCalculadora("1");
        botonesNumeros[2] = new BotonCalculadora("2");
        botonesNumeros[3] = new BotonCalculadora("3");
        btnResta           = new BotonCalculadora("-");
        teclado.add(botonesNumeros[1]);
        teclado.add(botonesNumeros[2]);
        teclado.add(botonesNumeros[3]);
        teclado.add(btnResta);

        // Fila 4: C 0 . +
        btnLimpiar         = new BotonCalculadora("C");
        botonesNumeros[0]  = new BotonCalculadora("0");
        btnPunto           = new BotonCalculadora(".");
        btnSuma            = new BotonCalculadora("+");
        teclado.add(btnLimpiar);
        teclado.add(botonesNumeros[0]);
        teclado.add(btnPunto);
        teclado.add(btnSuma);

        gbc.gridy = 2;
        gbc.weighty = 0.64;
        gbc.insets = new Insets(0, 15, 15, 15);
        add(teclado, gbc);

        // Tamaño parecido al de la imagen (alto > ancho)
        setSize(420, 640);
        setMinimumSize(new Dimension(340, 520));
        setLocationRelativeTo(null);
    }

    public void setTextoDisplay(String texto) {
        display.setText(texto);
    }
}