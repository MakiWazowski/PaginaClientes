package SpringBootDatajpa.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import SpringBootDatajpa.app.models.entity.Cliente;

//metodos que tiene que implementar la clase dao
// <Cliente, Long> -->  dato generico y el tipo de dato de nuestra llave primaria
//sustituimos extends CrudRepository por PagingAndSortingRepository que hereda del crud repository, para el paginador
public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>{


}
