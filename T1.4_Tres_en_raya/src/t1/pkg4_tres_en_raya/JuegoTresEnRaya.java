
package t1.pkg4_tres_en_raya;

/**
 *
 * @author pablo
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JuegoTresEnRaya implements ActionListener {

    private VentanaTresEnRaya ventana;
    private int[] tablero = new int[9]; // 0=vacío, 1=Azul, 2=Rojo
    private int turnoActual = 1;
    private int movimientos = 0;

    public JuegoTresEnRaya(VentanaTresEnRaya ventana) {
        this.ventana = ventana;
        
        for (int i = 0; i < 9; i++) {
            ventana.botones[i].addActionListener(this);
        }
    }

  
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botonPulsado = (JButton) e.getSource();
        
       
        int index = -1;
        for (int i = 0; i < 9; i++) {
            if (ventana.botones[i] == botonPulsado) {
                index = i;
                break;
            }
        }

        // Si la casilla ya está ocupada no hacemos nada
        if (tablero[index] != 0) return;

        // Pintar y bloquear el botón
        if (turnoActual == 1) {
            botonPulsado.setBackground(VentanaTresEnRaya.COLOR_AZUL);
        } else {
            botonPulsado.setBackground(VentanaTresEnRaya.COLOR_ROJO);
        }
        botonPulsado.setEnabled(false);
        
        tablero[index] = turnoActual;
        movimientos++;

        // Comprobar si el juego ha terminado
        if (hayGanador()) {
            String ganador = (turnoActual == 1) ? "Jugador 1 (Azul)" : "Jugador 2 (Rojo)";
            JOptionPane.showMessageDialog(ventana, "¡Ha ganado el " + ganador + "!");
            reiniciar();
        } else if (movimientos == 9) {
            JOptionPane.showMessageDialog(ventana, "¡Empate!");
            reiniciar();
        } else {
            // Si nadie ha ganado, cambiamos de turno
            turnoActual = (turnoActual == 1) ? 2 : 1;
        }
    }

    // El método de victoria que pediste (fácil de entender)
    public boolean hayGanador() {
        // Filas
        if (tablero[0] != 0 && tablero[0] == tablero[1] && tablero[1] == tablero[2]) return true;
        if (tablero[3] != 0 && tablero[3] == tablero[4] && tablero[4] == tablero[5]) return true;
        if (tablero[6] != 0 && tablero[6] == tablero[7] && tablero[7] == tablero[8]) return true;
        // Columnas
        if (tablero[0] != 0 && tablero[0] == tablero[3] && tablero[3] == tablero[6]) return true;
        if (tablero[1] != 0 && tablero[1] == tablero[4] && tablero[4] == tablero[7]) return true;
        if (tablero[2] != 0 && tablero[2] == tablero[5] && tablero[5] == tablero[8]) return true;
        // Diagonales
        if (tablero[0] != 0 && tablero[0] == tablero[4] && tablero[4] == tablero[8]) return true;
        if (tablero[2] != 0 && tablero[2] == tablero[4] && tablero[4] == tablero[6]) return true;

        return false; 
    }

    private void reiniciar() {
        // Limpiamos el array lógico
        for (int i = 0; i < 9; i++) {
            tablero[i] = 0;
        }
        movimientos = 0;
        turnoActual = 1;

        // Limpiamos los botones visuales
        for (int i = 0; i < 9; i++) {
            ventana.botones[i].setBackground(VentanaTresEnRaya.FONDO_BOTON);
            ventana.botones[i].setEnabled(true);
        }
    }
}