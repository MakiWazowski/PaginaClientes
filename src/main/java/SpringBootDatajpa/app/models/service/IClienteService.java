package SpringBootDatajpa.app.models.service;

import java.util.List;
import SpringBootDatajpa.app.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
	
	//metodo para guardar nuevos clientes en la base de datos
	public void save(Cliente cliente);
	
	//metodo para buscar cliente por id
	public Cliente findOne(Long id);
	
	//metodo para eliminar por id 
	public void delete(Long id);
	
}
