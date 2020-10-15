package SpringBootDatajpa.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import SpringBootDatajpa.app.models.dao.IClienteDao;
import SpringBootDatajpa.app.models.entity.Cliente;

//los metodos son los mismos ya que es una fachada, implementa el patron de diseño facade.

@Service
public class ClienteServiceImplement implements IClienteService{

	
	//inyectamos el clienteDao
	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	//transactional sin read only ya que es de escritura --> vamos a insertar un nuevo registro
	@Transactional
	public void save(Cliente cliente) {
		// TODO Auto-generated method stub
		clienteDao.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		// orElse para retornar el objeto entity encontrado o bien null si esta vacio
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		clienteDao.deleteById(id);
	}

}
