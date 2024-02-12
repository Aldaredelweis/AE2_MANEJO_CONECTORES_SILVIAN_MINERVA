package vista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controlador.ControladorCoche;
import controlador.ControladorPasajero;
import modelo.entidad.Coche;
import modelo.entidad.Pasajero;

public class Inicio {
	
	private static Scanner sc = new Scanner(System.in);
	private static ControladorCoche controladorCoche = new ControladorCoche();
	private static ControladorPasajero controladorPasajero = new ControladorPasajero();
	
	public static void main(String[] args) {

		String marca, modelo, nombre;
		int km, año, id, edad;
		double peso;
		Coche coche;
		Pasajero pasajero;
		boolean exito;

		sc = new Scanner(System.in);
		int opc;
		
		
		do {
			pintamenu();
			opc = sc.nextInt();
			sc.nextLine();
			switch (opc) {
			case 1:
				// crear coche
				System.out.println("Dime los datos del coche:");
				System.out.println("Dime el id del coche");
				id = sc.nextInt();
				System.out.println("Dime marca:");
				marca = sc.nextLine();
				sc.nextLine();
				System.out.println("Dime modelo:");
				modelo = sc.nextLine();
				System.out.println("Dime año de fabricacion:");
				año = sc.nextInt();
				System.out.println("Dime kilometros que tiene:");
				km = sc.nextInt();
				coche = new Coche(id, marca, modelo, año, km);
				exito = controladorCoche.altaCoche(coche);
				if (exito != false) {
					System.out.println("Error al crear");
				} else {
					System.out.println("El coche se ha creado con éxito");
					
				}
				
				break;
			
			case 2:
				listarCoches();
				System.out.println("Dime el id del coche a borrar:");
				id = sc.nextInt();
				exito = controladorCoche.borrarCoche(id);
				if (exito == false) {
					System.out.println("El coche indicado no existe");
				} else {
					System.out.println("El coche se ha borrado");
				}
				break;
		
			case 3:
				listarCoches();
				System.out.println("Dime el id del coche a consultar:");
				id = sc.nextInt();
				coche = controladorCoche.consultarCoche(id);
				if (coche != null) {
					System.out.println("Datos del coche solicitado");
					System.out.println(coche);
				} else {
					System.out.println("El coche indicado no existe");
				}

				break;
				
			case 4:
				listarCoches();
				modificarCoche();
				System.out.println("Dime los datos del coche que quieres modificar:");
				System.out.println("Dime el ID del coche:");
				id = sc.nextInt();
				sc.nextLine();
				System.out.println("Dime marca:");
				marca = sc.nextLine();
				System.out.println("Dime modelo:");
				modelo = sc.nextLine();
				System.out.println("Dime año de fabricacion:");
				año = sc.nextInt();
				System.out.println("Dime kilometros que tiene:");
				km = sc.nextInt();
				coche = new Coche(id, marca, modelo, año, km);
				int respuesta = controladorCoche.modificarCoche(coche);
				if (respuesta == 1) {
					System.out.println("El coche indicado no existe");
				} else if (respuesta == 2) {
					System.out.println("No debe dejar la marca o el modelo vacios");
				} else {
					System.out.println("El coche se ha modificado");
				}

				break;
				
			case 5:
				listarCoches();
				break;
				
			case 6:
				// crear pasajero
				System.out.println("Dime los datos del pasajero:");
				System.out.println("Dime un id");
				id=sc.nextInt();
				sc.nextLine();
				System.out.println("Dime nombre:");
				
				nombre = sc.nextLine();

				System.out.println("Dime su edad:");
				edad = sc.nextInt();
				System.out.println("Dime su peso:");
				peso = sc.nextInt();

				pasajero = new Pasajero(id,nombre,edad,peso);
				exito = controladorPasajero.alta(pasajero);
				if (exito == false) {
					System.out.println("El pasajero no puede tener el nombre vacio");
				} else {
					System.out.println("El pasajero se ha creado");
				}
				
				break;
			
			case 7:
				listarPasajeros();
				System.out.println("Dime el id del pasajero a borrar:");
				id = sc.nextInt();

				exito = controladorPasajero.bajaPasajero(id);
				if (exito == true) {
					System.out.println("El pasajero se ha borrado");
				} else {
					System.out.println("El pasajero indicado no existe");
				}

				break;

			case 8:
				listarPasajeros();
				System.out.println("Dime el id del pasajero a consultar:");
				id = sc.nextInt();
				pasajero = controladorPasajero.consultarUnPasajero(id);
				if (pasajero != null) {
					System.out.println("Datos del pasajero solicitado:");
					System.out.println(pasajero);
				} else {
					System.out.println("El pasajero indicado no existe");
				}
				break;
			case 9:
				listarPasajeros();
				break;
				

			case 10:
				System.out.println("Dime el id de la asignacion coche/pasajero:");
				int idCochePasajero = sc.nextInt();

				listarPasajeros();
				System.out.println("Dime el id del pasajero:");
				int idpasajero = sc.nextInt();
				listarCoches();
				System.out.println("Dime el id del coche deonde se desea añadir el pasajero:");
				int idcoche = sc.nextInt();
				exito = controladorPasajero.anadirPasajeroACoche(idCochePasajero, idcoche, idpasajero);
				if (exito == true) {
					System.out.println("Pasajero asociado correctamente");
				} else {
					System.out.println("No existe el id del coche o el del pasajero");
				}
				break;
			case 11:
				listarPasajeros();
				System.out.println("Dime el id del pasajero al que se desea sacar de su coche:");
				id = sc.nextInt();

				exito = controladorPasajero.quitarCocheAPasajero(id);
				if (exito == true) {
					System.out.println("Pasajero sacado de su coche correctamente");
				} else {
					System.out.println("El pasajero indicado no existe");
				}
				break;
				
			case 12:
				listarCoches();
				System.out.println("Dime el ID del coche:");
				id = sc.nextInt();
				List<Pasajero> listapasajeros = controladorPasajero.listarPasajerosDeUnCoche(id);
				if (listapasajeros == null) {
					System.out.println("El id del coche no es correcto:");
				} else {
					if (listapasajeros.isEmpty()) {
						System.out.println("Ese coche no tiene pasajeros");
					} else {
						System.out.println("Lista de pasajeros existentes:");
						for (Pasajero p : listapasajeros) {
							System.out.println(p);
						}
					}

				}
			
			break;
			
			case 0:
				System.out.println("...adios...");
						
				break;
		
			}
		
		} while (opc != 0);
		
		sc.close();

	}

	private static void modificarCoche() {
		// TODO Auto-generated method stub
		
	}

	public static void pintamenu() {
		System.out.println("\n========= MENU ========");
		System.out.println(" 1.- Alta coche");
		System.out.println(" 2.- Borrar coche");
		System.out.println(" 3.- Consultar coche");
		System.out.println(" 4.- Modificar coche");
		System.out.println(" 5.- Listado coches");
		System.out.println("    -------------------");
		System.out.println(" 6.- Alta pasajero");
		System.out.println(" 7.- Borrar pasajero");
		System.out.println(" 8.- Consultar un pasajero por su id");
		System.out.println(" 9.- Listar todos los pasajeros ");
		System.out.println("10.- Añadir un pasajero a un coche");
		System.out.println("11.- Quitar un pasajero de su coche");
		System.out.println("12.- Listar los pasajeros de un coche");
		System.out.println("    -------------------");
		System.out.println("0.- Terminar");
		System.out.println("\n    Indique la opcion deseada:");

	}

public static void listarCoches() {
		List<Coche> lista = controladorCoche.listarCoches();
		System.out.println("\nListado de coches existentes:");
		for (Coche c : lista) {
			System.out.println(c);
		}
	}

	public static void listarPasajeros() {

		List<Pasajero> listap = controladorPasajero.listarPasajeros();
		System.out.println("\nListado de pasajeros existentes:");
		for (Pasajero p : listap) {
			System.out.println(p);
		}
	}

}

