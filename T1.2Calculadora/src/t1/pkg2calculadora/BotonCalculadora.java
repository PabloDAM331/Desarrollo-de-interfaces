package t1.pkg2calculadora;
/**
 *
 * @author pablo
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BotonCalculadora extends JButton {

    
    private static final Color FONDO_NORMAL  = new Color(60, 60, 60);     // gris oscuro
    private static final Color FONDO_HOVER   = new Color(80, 80, 80);     // gris un poco más claro
    private static final Color FONDO_PULSA   = new Color(45, 45, 45);     // gris más oscuro
    private static final Color TEXTO         = new Color(230, 230, 230);  // gris muy claro

    public BotonCalculadora(String texto) {
        super(texto);
        setFont(new Font("Arial", Font.PLAIN, 26));
        setForeground(TEXTO);
        setBackground(FONDO_NORMAL);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(true);
        setContentAreaFilled(true);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e)  { setBackground(FONDO_HOVER); }
            @Override public void mouseExited(MouseEvent e)   { setBackground(FONDO_NORMAL); }
            @Override public void mousePressed(MouseEvent e)  { setBackground(FONDO_PULSA); }
            @Override public void mouseReleased(MouseEvent e) { setBackground(FONDO_HOVER); }
        });
    }

    public void setFuenteResultado() {
        setFont(new Font("Arial", Font.PLAIN, 34));
    }
}