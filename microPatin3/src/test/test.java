package test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import main.DTOs.PatinDTO;
import main.Objects.Patin;
import main.Services.PatinService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest no lo toma??
class PatinServiceTest {
	
	@Autowired
	private PatinService service;

	@Test
	void findById() {
		Patin Patin12 = new Patin();
		Patin12.setPatinId(12);
		Patin12.setX("-37.326162");
		Patin12.setY("-59.139982");
		Patin12.setEstado("ocupado");
		Patin12.setKm(318.42);
		Patin12.setUso(911);
		Patin12.setPausa(10);
		PatinDTO Patin12DTO = new PatinDTO(Patin12);
		PatinDTO responsePatinDTO = service.findById((long)12);
		assertAll(
				() -> assertNotNull(responsePatinDTO),
				() -> assertEquals(Patin12DTO.getPatinId(),responsePatinDTO.getPatinId()),
				() -> assertEquals(Patin12DTO.getX(),responsePatinDTO.getX()),
				() -> assertEquals(Patin12DTO.getY(),responsePatinDTO.getY()),
				() -> assertEquals(Patin12DTO.getEstado(),responsePatinDTO.getEstado()),
				() -> assertEquals(Patin12DTO.getKm(),responsePatinDTO.getKm()),
				() -> assertEquals(Patin12DTO.getUso(),responsePatinDTO.getUso()),
				() -> assertEquals(Patin12DTO.getPausa(),responsePatinDTO.getPausa())
		);
	}

	@Test
	void getPatinsCercanos() {
		Patin Patin12 = new Patin();
		Patin12.setPatinId(12);
		Patin12.setX("-37.326162");
		Patin12.setY("-59.139982");
		Patin12.setEstado("ocupado");
		Patin12.setKm(318.42);
		Patin12.setUso(911);
		Patin12.setPausa(10);
		Patin Patin13 = new Patin();
		Patin13.setPatinId(13);
		Patin13.setX("-37.326392");
		Patin13.setY("-59.136656");
		Patin13.setEstado("ocupado");
		Patin13.setKm(706.11);
		Patin13.setUso(178);
		Patin13.setPausa(479);
		Patin Patin17 = new Patin();
		Patin17.setPatinId(17);
		Patin17.setX("-37.327996");
		Patin17.setY("-59.140619");
		Patin17.setEstado("disponible");
		Patin17.setKm(336.99);
		Patin17.setUso(882);
		Patin17.setPausa(448);
		Patin Patin20 = new Patin();
		Patin20.setPatinId(20);
		Patin20.setX("-37.328605");
		Patin20.setY("-59.136094");
		Patin20.setEstado("mantenimiento");
		Patin20.setKm(304.43);
		Patin20.setUso(22);
		Patin20.setPausa(131);
		List<Patin> Patins = new ArrayList<>();
		Patins.add(Patin12);
		Patins.add(Patin13);
		Patins.add(Patin17);
		Patins.add(Patin20);
		List<Patin> PatinsCercanos = service.getPatinesCercanos(-37.327754, -59.138998);
		assertEquals(Patins,PatinsCercanos);
	}
}