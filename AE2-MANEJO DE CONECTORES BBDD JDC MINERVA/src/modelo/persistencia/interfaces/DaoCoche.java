package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;
//Esta interfaz define un CRUD para el objeto coche
//es decir, las operaciones b�sicas que podemos hacer con una entidad
//Create
//Read
//Update
//Delete
public interface DaoCoche {
	public boolean alta(Coche c);
	public boolean baja(int id);
	public boolean modificar(Coche c);
	public Coche obtener(int id);
	public List<Coche> listar();
}



