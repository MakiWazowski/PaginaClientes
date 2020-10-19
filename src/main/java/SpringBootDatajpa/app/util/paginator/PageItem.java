package SpringBootDatajpa.app.util.paginator;

//clase para representar cada una de las pagians con un atributo para indicar si es o no la pagina actual

public class PageItem {

	private int numero;
	private boolean actual;

	
	//CONTROLADOR CON CAMPOS
	
	public PageItem(int numero, boolean actual) {
		this.numero = numero;
		this.actual = actual;
	}

	//GET DE CAMPOS

	public int getNumero() {
		return numero;
	}

	public boolean isActual() {
		return actual;
	}
}
