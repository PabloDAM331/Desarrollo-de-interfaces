package t1.pkg2calculadora;

/**
 *
 * @author pablo
 */
import javax.swing.SwingUtilities;

public class CalculadoraApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraModelo modelo = new CalculadoraModelo();
            CalculadoraVista vista   = new CalculadoraVista();
            new CalculadoraControlador(modelo, vista);
            vista.setVisible(true);
        });
    }
}
