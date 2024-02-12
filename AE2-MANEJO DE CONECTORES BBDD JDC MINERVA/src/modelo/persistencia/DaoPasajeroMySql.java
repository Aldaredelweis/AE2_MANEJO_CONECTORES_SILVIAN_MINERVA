package modelo.persistencia;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoPasajero;

public class DaoPasajeroMySql implements DaoPasajero{

	private Connection conexion;
		
	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:3306/coche";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean alta(Pasajero unpasajero) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into pasajeros (ID, NOMBRE, EDAD, PESO)"
				+ " values(?,?,?,?)";
		try {
			//preparamos la query con valores parametrizables(?)
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, unpasajero.getId());
			ps.setString(2, unpasajero.getNombre());
			ps.setInt(3, unpasajero.getEdad());
			ps.setDouble(4, unpasajero.getPeso());
			
			
			int numeroFilasAfectadas = ps.executeUpdate();
			
			if(numeroFilasAfectadas == 0)
				alta  = false;
			
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + unpasajero);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
	
	}

	@Override
	public boolean bajaPasajero(int id) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		String query = "delete from pasajeros where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			//sustituimos la primera interrgante por la id
			ps.setInt(1, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			borrado = false;
			System.out.println("baja -> No se ha podido dar de baja"
					+ " el id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado; 
	}
	@Override
	
	public boolean modificar(Pasajero unpasajero) {
		if(!abrirConexion()){
			return false;
		}
		boolean modificado = true;
		String query = "update coches set NOMBRE=?, EDAD=?, "
				+ "AÑO PESO=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, unpasajero.getNombre());
			ps.setInt(2, unpasajero.getEdad());
			ps.setDouble(3, unpasajero.getPeso());
			ps.execute();
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				modificado = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("modificar -> error al modificar el "
					+ " pasajero " + unpasajero);
			modificado = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return modificado;
	}

	@Override
	public Pasajero obtener(int id) {
		if(!abrirConexion()){
			return null;
		}		
		Pasajero pasajero = null;
		
		String query = "select ID, NOMBRE, EDAD, PESO from pasajeros "
				+ "where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getInt(4));
			
			}
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el "
					+ "pasajero con id" + pasajero);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return pasajero;
	}

	@Override
	public List<Pasajero> listar() {
		if(!abrirConexion()){
			return null;
		}		
		List<Pasajero> listaPasajeros	= new ArrayList<>();
		
		String query = "select ID, NOMBRE,EDAD, PESO from PASAJEROS";
				
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getInt(4));
			
			listaPasajeros.add(pasajero);
			}
		
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener las "
					+ "personas");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return listaPasajeros;
	}
	
	public List<Pasajero> listarPasajerosDeUnCoche(int idcoche) {
		if(!abrirConexion()){
			return null;
		}		
		List<Pasajero> listaPasajeros	= new ArrayList<>();
		
		String query = "SELECT pasajeros.* FROM `coche_pasajeros`, pasajeros "
				+ "WHERE coche_pasajeros.idPasajero = pasajeros.id "
				+ "and coche_pasajeros.idCoche = ?;";
				
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idcoche);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getInt(4));
				Coche coche = new Coche(idcoche, "", "", -1, 0);
				pasajero.setCoche(coche);
			
				listaPasajeros.add(pasajero);
			}
		
		} catch (SQLException e) {
			System.out.println("listarPasajerosDeUnCoche -> error al obtener las "
					+ "personas del coche");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return listaPasajeros;
	}

	public boolean anadirPasajeroACoche(int id, int idcoche, int idpasajero) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into coche_pasajeros (id, idCoche, idPasajero)"
				+ " values(?,?,?)";
		try {
			//preparamos la query con valores parametrizables(?)
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			ps.setInt(2, idcoche);
			ps.setInt(3, idpasajero);
			
			
			int numeroFilasAfectadas = ps.executeUpdate();
			
			if(numeroFilasAfectadas == 0)
				alta  = false;
			
		} catch (SQLException e) {
			System.out.println("alta -> Error al asignar un pasajero a un coche");
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
		
	}

	public boolean quitarCocheAPasajero(int id) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		String query = "delete from coche_pasajeros where idPasajero = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			//sustituimos la primera interrgante por la id
			ps.setInt(1, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			borrado = false;
			System.out.println("quitar Coche A Pasajero -> No se ha podido quitar Coche A Pasajero"
					+ " el id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado; 
		
	}
	


	}

	
