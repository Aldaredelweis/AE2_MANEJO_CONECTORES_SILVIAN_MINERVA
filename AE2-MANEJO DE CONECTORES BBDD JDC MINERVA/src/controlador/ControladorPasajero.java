

package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.DaoCocheMySql;
import modelo.persistencia.DaoPasajeroMySql;
import modelo.persistencia.interfaces.DaoPasajero;

public class ControladorPasajero {
	Scanner sc = new Scanner(System.in);
	DaoPasajeroMySql dao = new DaoPasajeroMySql();
	DaoCocheMySql daocoche = new DaoCocheMySql();
	
	public boolean alta(Pasajero p) {
		
		if(p.getNombre().isEmpty() ) {
			return false;
		}
		
		
		dao.alta(p);
		return true;
	}
	
	public boolean  bajaPasajero(int id) {
		
	Pasajero p = dao.obtener(id);
		if(p != null) {
			dao.bajaPasajero(id);
			return true;
		}else {
			return false;
		}
	}

	
	public Pasajero consultarUnPasajero(int id) {

		Pasajero p = dao.obtener(id);
		if(p != null) {
			return p;
		}
		return null;
	}
	
	
	public List<Pasajero> listarPasajeros() {
		/*return ((ControladorPasajero) dao).listar();*/
		return dao.listar();
		
	}
	
	
	public List<Pasajero> listarPasajerosDeUnCoche(int idcoche) {
		DaoCocheMySql daocoche = new DaoCocheMySql();
		Coche c = daocoche.obtener(idcoche);
		if (c == null) {
			return null;
		}else{
			
			return dao.listarPasajerosDeUnCoche(idcoche);
		}
		
		

	}
	
	public boolean quitarCocheAPasajero(int id) {
		Pasajero p = dao.obtener(id);
		if(p != null) {
			dao.quitarCocheAPasajero(id);
			return true;
		}else {
			return false;
		}
	}

	public boolean anadirPasajeroACoche(int idCochePasajero, int idcoche, int idpasajero) {
		Pasajero p = dao.obtener(idpasajero);
		Coche c = daocoche.obtener(idcoche);
		if(p == null) {
			return false;
		}
		if(c == null) {
			return false;
		}
		dao.anadirPasajeroACoche(idCochePasajero, idcoche,idpasajero);
		return true;
	}

	public void cerrar() {
	/*	dao.cerrar();*/
	}
}

