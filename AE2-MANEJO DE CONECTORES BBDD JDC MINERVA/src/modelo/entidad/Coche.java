package modelo.entidad;

public class Coche {
	
	private int id;
    private String marca;
    private String modelo;
    private int añoFabricacion;
    private int km;
    
    
	public Coche(int id, String marca, String modelo, int añoFabricacion, int km) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.añoFabricacion = añoFabricacion;
		this.km = km;
	}
	public Coche() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getAñoFabricacion() {
		return añoFabricacion;
	}
	public void setAñoFabricacion(int añoFabricacion) {
		this.añoFabricacion = añoFabricacion;
	}
	public int getKm() {
		return km;
	}
	public void setKm(int km) {
		this.km = km;
	}
	@Override
	public String toString() {
		return "Coche [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", añoFabricacion=" + añoFabricacion
				+ ", km=" + km + "]";
	}
	

	}

    


