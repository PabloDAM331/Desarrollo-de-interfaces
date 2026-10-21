package t1.pkg2calculadora;
/**
 *
 * @author pablo
 */
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class CalculadoraControlador {

    private final CalculadoraModelo modelo;
    private final CalculadoraVista vista;

    public CalculadoraControlador(CalculadoraModelo modelo, CalculadoraVista vista) {
        this.modelo = modelo;
        this.vista  = vista;
        conectarBotones();
        conectarTeclado();
    }

    private void conectarBotones() {
        for (int i = 0; i <= 9; i++) {
            final String d = String.valueOf(i);
            vista.botonesNumeros[i].addActionListener(e -> {
                modelo.agregarDigito(d);
                refrescar();
            });
        }

        vista.btnPunto.addActionListener(e -> {
            modelo.agregarPunto();
            refrescar();
        });

        vista.btnSuma.addActionListener(e  -> { modelo.aplicarOperador("+"); refrescar(); });
        vista.btnResta.addActionListener(e -> { modelo.aplicarOperador("-"); refrescar(); });
        vista.btnMulti.addActionListener(e -> { modelo.aplicarOperador("*"); refrescar(); });
        vista.btnDiv.addActionListener(e   -> { modelo.aplicarOperador("/"); refrescar(); });

        vista.btnResultado.addActionListener(e -> {
            modelo.calcularResultado();
            refrescar();
        });

        vista.btnLimpiar.addActionListener(e -> {
            modelo.limpiar();
            refrescar();
        });
    }

    private void conectarTeclado() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
            .addKeyEventDispatcher(evt -> {
                if (evt.getID() != KeyEvent.KEY_PRESSED) return false;
                char c = evt.getKeyChar();

                if (Character.isDigit(c)) {
                    modelo.agregarDigito(String.valueOf(c));
                    refrescar();
                    return true;
                }
                switch (c) {
                    case '.': case ',':
                        modelo.agregarPunto(); refrescar(); return true;
                    case '+': case '-': case '*': case '/':
                        modelo.aplicarOperador(String.valueOf(c)); refrescar(); return true;
                    case '\n': case '=':
                        modelo.calcularResultado(); refrescar(); return true;
                    case 'c': case 'C':
                        modelo.limpiar(); refrescar(); return true;
                }
                return false;
            });
    }

    private void refrescar() {
        vista.setTextoDisplay(modelo.getTextoDisplay());
    }
}