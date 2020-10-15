package SpringBootDatajpa.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import SpringBootDatajpa.app.models.entity.Cliente;

//metodos que tiene que implementar la clase dao
// <Cliente, Long> -->  dato generico y el tipo de dato de nuestra llave primaria
public interface IClienteDao extends CrudRepository<Cliente, Long>{


}
