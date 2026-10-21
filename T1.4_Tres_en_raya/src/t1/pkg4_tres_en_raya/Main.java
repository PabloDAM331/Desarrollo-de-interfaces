package t1.pkg4_tres_en_raya;
/**
 *
 * @author pablo
 */
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaTresEnRaya ventana = new VentanaTresEnRaya();
            new JuegoTresEnRaya(ventana); 
            ventana.setVisible(true);     
        });
    }
}
