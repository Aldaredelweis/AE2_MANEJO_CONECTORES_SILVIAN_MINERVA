package modelo.persistencia.interfaces;

import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Pasajero;

public interface DaoPasajero {

		public boolean alta(Pasajero p);
		public boolean bajaPasajero(int id);
		public boolean modificar(Pasajero p);
		public Pasajero obtener(int id);
		public List<Pasajero>listar();
		
			
		}
	