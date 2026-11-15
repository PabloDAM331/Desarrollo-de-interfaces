
package cafeteria;

/**
 *
 * @author pablo
 */
public class LineaPedido {
    private String cliente;
    private String bebida;
    private String tamano;
    private String extras;
    private int cantidad;
    private double precioUnitario;//Precio total

    public LineaPedido(String cliente, String bebida, String tamano, String extras, int cantidad, double precioUnitario) {
        this.cliente = cliente;
        this.bebida = bebida;
        this.tamano = tamano;
        this.extras = extras;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
    public double getPrecio(){
        return precioUnitario*cantidad;
    }
    @Override
    public String toString(){
         return String.format("%s | %dx %s (%s) - %s | Total: %.2f €", 
                cliente, cantidad, bebida, tamano, extras.isEmpty() ? "Sin extras" : extras, getPrecio());
    }
    
}
