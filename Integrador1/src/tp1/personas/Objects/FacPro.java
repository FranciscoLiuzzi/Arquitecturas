package tp1.personas.Objects;

public class FacPro {
	private Integer idFac;
	private Integer idPro;
	private Integer cant;
	
	public FacPro(Integer idFac, Integer idPro, Integer cant) {
		this.idFac = idFac;
		this.idPro = idPro;
		this.cant = cant;
	}
	
	@Override
	public String toString() {
		return "ID Factura: " + this.getIdFac() + ", ID Producto: " + this.getIdPro() + ", Cantidad: " + this.getCant() + "";
	}
	
	public Integer getCant() {
		return cant;
	}

	public void setCant(Integer cant) {
		this.cant = cant;
	}

	public Integer getIdFac() {
		return idFac;
	}

	public Integer getIdPro() {
		return idPro;
	}
}
