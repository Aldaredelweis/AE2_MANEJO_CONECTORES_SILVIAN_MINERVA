package modelo.persistencia;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.interfaces.DaoCoche;

public class DaoCocheMySql implements DaoCoche{

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
	public boolean altaCoche(Coche c) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into coche (ID,MARCA ,MODELO,AÑO FABRICACION,KM) "
				+ " values(?,?,?,?,?)";
		try {
			//preparamos la query con valores parametrizables(?)
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1,c.getId());
			ps.setString(2, c.getMarca());
			ps.setString(3, c.getModelo());
			ps.setInt(4, c.getAñoFabricacion());
			ps.setInt(5, c.getKm());
			
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				alta  = false;
			
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + c);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
	
	}

	@Override
	public boolean baja(int id) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		String query = "delete from coche where id = ?";
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
	public boolean modificar(Coche c) {
		if(!abrirConexion()){
			return false;
		}
		boolean modificado = true;
		String query = "update coches set MARCA=?, MODELO=?, "
				+ "ANIO=? KM ID=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1,c.getId());
			ps.setString(2, c.getMarca());
			ps.setString(3, c.getModelo());
			ps.setInt(4, c.getAñoFabricacion());
			ps.setInt(5, c.getKm());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				modificado = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("modificar -> error al modificar el "
					+ " coche " + c);
			modificado = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return modificado;
	}

	@Override
	public Coche obtener(int id) {
		if(!abrirConexion()){
			return null;
		}		
		Coche coche = null;
		
		String query = "select ID,MARCA,MODELO,ANIO, KM from COCHE "
				+ "where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMarca(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setAñoFabricacion(rs.getInt(4));
				coche.setKm(rs.getInt(5));
			}
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el "
					+ "coche con id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return coche;
	}

	@Override
	public List<Coche> listar() {
		if(!abrirConexion()){
			return null;
		}		
		List<Coche> listaCoches = new ArrayList<>();
		
		String query = "select ID,MODELO,MARCA,ANIO,KM from COCHE";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();	
			while (rs.next())
			{
			Coche coche = new Coche();
			coche.setId(rs.getInt(1));
			coche.setMarca(rs.getString(2));
			coche.setModelo(rs.getString(3));
			coche.setAñoFabricacion(rs.getInt(4));
			coche.setKm(rs.getInt(5));
			
			listaCoches.add(coche);
			}
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener los "
					+ "coches");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return listaCoches;
	}

	}
