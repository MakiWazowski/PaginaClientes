package SpringBootDatajpa.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import SpringBootDatajpa.app.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
	
	//metodo para contar las paginas (paginador)
	public Page<Cliente> findAll(Pageable pageable);
	
	//metodo para guardar nuevos clientes en la base de datos
	public void save(Cliente cliente);
	
	//metodo para buscar cliente por id
	public Cliente findOne(Long id);
	
	//metodo para eliminar por id 
	public void delete(Long id);
	
}
