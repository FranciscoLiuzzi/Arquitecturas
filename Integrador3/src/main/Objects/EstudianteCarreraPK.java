package main.Objects;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.ManyToOne;

public class EstudianteCarreraPK implements Serializable{
	@ManyToOne
    private Estudiante estudiante;
    @ManyToOne
    private Carrera carrera;

    public EstudianteCarreraPK(Estudiante estudiante, Carrera carrera) {
        this.estudiante = estudiante;
        this.carrera = carrera;
    }

    public EstudianteCarreraPK() {
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        EstudianteCarreraPK pk = (EstudianteCarreraPK) o;
        return Objects.equals( estudiante, pk.estudiante ) &&
                Objects.equals( carrera, pk.carrera );
    }

    @Override
    public int hashCode() {
        return Objects.hash( estudiante, carrera );
    }
}
