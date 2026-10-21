package t1.pkg2calculadora;
/**
 *
 * @author pablo
 */
public class CalculadoraModelo {

    private String textoDisplay = "0";
    private double operando1 = 0;
    private String operador = "";
    private boolean nuevaEntrada = true;
    private boolean hayPunto = false;

    public String getTextoDisplay() {
        return textoDisplay;
    }

    public void agregarDigito(String digito) {
        if (nuevaEntrada) {
            textoDisplay = digito;
            nuevaEntrada = false;
            hayPunto = false;
        } else {
            textoDisplay += digito;
        }
    }

    public void agregarPunto() {
        if (nuevaEntrada) {
            textoDisplay = "0.";
            nuevaEntrada = false;
            hayPunto = true;
            return;
        }
        if (hayPunto) return;
        textoDisplay += ".";
        hayPunto = true;
    }

    public void aplicarOperador(String op) {
        if (!operador.isEmpty() && !nuevaEntrada) {
            calcularResultado();
        }
        operando1 = Double.parseDouble(textoDisplay);
        operador = op;
        nuevaEntrada = true;
        hayPunto = false;
    }

    public void calcularResultado() {
        if (operador.isEmpty()) return;

        double operando2 = Double.parseDouble(textoDisplay);
        double res;

        switch (operador) {
            case "+": res = operando1 + operando2; break;
            case "-": res = operando1 - operando2; break;
            case "*": res = operando1 * operando2; break;
            case "/":
                if (operando2 == 0) {
                    textoDisplay = "Error";
                    reiniciarEstado();
                    return;
                }
                res = operando1 / operando2;
                break;
            default: return;
        }

        textoDisplay = (res == (long) res) ? String.valueOf((long) res): String.valueOf(res);
        operador = "";
        nuevaEntrada = true;
        hayPunto = false;
    }

    public void limpiar() {
        textoDisplay = "0";
        reiniciarEstado();
    }

    private void reiniciarEstado() {
        operando1 = 0;
        operador = "";
        nuevaEntrada = true;
        hayPunto = false;
    }
}
