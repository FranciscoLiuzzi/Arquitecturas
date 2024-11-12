package main.Services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import main.DTOs.FacturaDTO;
import main.DTOs.NFacturaDTO;
import main.Objects.Factura;
import main.Repositories.FacturaRepository;

@Service("FacturaService")
public class FacturaService{
	
	@Autowired
	private FacturaRepository facturaRepository;
		
	@Transactional(readOnly = true)
	public List<FacturaDTO> findAll() {
		return this.facturaRepository.findAll().stream().map(FacturaDTO::new).toList();
	}

	@Transactional(readOnly = true)
	public FacturaDTO findById(Long id) {
		return this.facturaRepository.findById(id).map(FacturaDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de factura invalido: " + id));
	}
	
	@Transactional
	public FacturaDTO save(NFacturaDTO entity) {
		return new FacturaDTO(this.facturaRepository.save(new Factura(entity)));
	}

	@Transactional
	public void delete(Long id) {
		facturaRepository.delete(facturaRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de factura invalido: " + id)));
	}

	@Transactional
	public void update(Long id, FacturaDTO entity) {
		Factura Factura = facturaRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de factura invalido: " + id));
		Factura.setFacturaFecha(entity.getFacturaFecha());
		Factura.setMonto(entity.getMonto());
		Factura.setDescripcion(entity.getDescripcion());
		facturaRepository.save(Factura);
	}

	@Transactional(readOnly = true)
	public Double getFacturacion(String fechaDesde, String fechaHasta) {
		checkValidDateFormat(fechaDesde);
		checkValidDateFormat(fechaHasta);
		return this.facturaRepository.getFacturacion(fechaDesde, fechaHasta);
	}

	

	private void checkValidDateFormat(String fecha) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate.parse(fecha, formatter);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Invalid date format: " + fecha);
		}
	}
	
}