package SpringBootDatajpa.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

//generates de java <T>
public class PageRender <T> {

	//ATRIBUTOS
	
	private String url;
	
	//listado paginable de clientes tipo generic
	private Page<T> page;
	
	private int totalPaginas;
	
	private int numeroElementosPorPagina;
	
	private int paginaActual;
	
	private List<PageItem> paginas;

	//CONSTRUCTOR GENERADO CON LOS CAMPOS 
	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PageItem>();
		
		//size declarado en el listar del controlador
		numeroElementosPorPagina = page.getSize();
		
		totalPaginas = page.getTotalPages();
		
		//defaultValue declarada en el listar del controlador
		paginaActual = page.getNumber() + 1;
		
		//calculo por rangos para dibujar el paginador 
		int desde, hasta;
		if(totalPaginas <= numeroElementosPorPagina) {
			desde = 1;
			hasta = totalPaginas;
		}else {
			if(paginaActual <= numeroElementosPorPagina/2){
				desde = 1;
				hasta = numeroElementosPorPagina;
			}else if (paginaActual >= totalPaginas - numeroElementosPorPagina) {
				desde = totalPaginas - numeroElementosPorPagina + 1;
				hasta = numeroElementosPorPagina;
			}else {
				desde = paginaActual - numeroElementosPorPagina/2;
				hasta = numeroElementosPorPagina;
			}
		}
		
		//con un for vamos a recorrer el hasta y vamos a empezar a llenar las paginas con cada uno de sus item
		//son las paginas que vamos a mostrar en el paginador
		for(int i=0; i < hasta; i++) {
			paginas.add(new PageItem(desde+i, paginaActual == desde+i));
		}
	}

	//GETTER DE LOS CAMPOS 
	
	public String getUrl() {
		return url;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}

	//creamos getter para saber si es la primera pagina 
	public boolean isFirst() {
		return page.isFirst();
	}
	
	//creamos getter para saber si es la ultima pagina 
	public boolean isLast() {
		return page.isLast();
	}
	
	//creamos getter para saber si hay pagina siguiente
	public boolean isHasNext() {
		return page.hasNext();
	}		

	//creamos getter para saber si hay pagina anterior
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}		

		
}
