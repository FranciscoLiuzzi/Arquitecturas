package main.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import java.io.Serializable;
import java.util.Objects;

@Data
public class CuentaUsuarioID implements Serializable {

    @JoinColumn(name="usuario_id")    
	@ManyToOne(cascade = CascadeType.PERSIST)	
    private Usuario usuario;
    
	@ManyToOne(cascade = CascadeType.PERSIST)	
    @JoinColumn(name="cuenta_id")
    private Cuenta cuenta;
	
	//CONSTRUCTORES
	
    public CuentaUsuarioID(Usuario usuario, Cuenta cuenta) {
        this.usuario = usuario;
        this.cuenta = cuenta;
    }

    public CuentaUsuarioID() {
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        CuentaUsuarioID pk = (CuentaUsuarioID) o;
        return Objects.equals( usuario, pk.usuario ) &&
                Objects.equals( cuenta, pk.cuenta );
    }

    @Override
    public int hashCode() {
        return Objects.hash( usuario, cuenta );
    }
}