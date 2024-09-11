package tp1.personas.Objects;

public class Factura {
	private Integer idFac;
	private Integer idCli;
	
	public Factura(Integer idFac, Integer idCli) {
		this.idCli = idCli;
		this.idFac = idFac;
	}
	
	@Override
	public String toString() {
		return "ID Factura: " + this.getIdFac() + ", ID Cliente: " + this.getIdCli() + "";
	}
	
	public Integer getIdFac() {
		return idFac;
	}

	public Integer getIdCli() {
		return idCli;
	}
}
