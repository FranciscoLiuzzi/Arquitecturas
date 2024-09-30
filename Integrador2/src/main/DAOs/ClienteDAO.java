package main.DAOs;

import java.util.List;

import main.DTOs.ClienteMasFacturas;
import main.Objects.Cliente;

public interface ClienteDAO extends dbDAO<Cliente>{
	List<ClienteMasFacturas> selectClientesQueMasFacturan();
}
