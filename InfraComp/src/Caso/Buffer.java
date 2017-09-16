package Caso;

import java.util.LinkedList;

public class Buffer {

	/**Número máximo de mensajes que puede recibir el buffer*/
	private int maximoBuff;

	/**Los clientes que faltan por procesar*/
	private int clientesFaltan;

	/**número de mensajes agregados a la lista*/
	private int mensajesAgregados;

	/**La cola donde se van guardando los mensjaes*/
	private LinkedList<Mensaje> buffer;


	public Buffer(int maximoBuff, int numClientes) {
		this.maximoBuff = maximoBuff;
		buffer = new LinkedList<Mensaje>();
		mensajesAgregados = 0;
		clientesFaltan = numClientes;
	}

	public synchronized void insertarMensaje(Mensaje elMensaje) throws InterruptedException{
		
		//Se añade un nuevo mensaje a "la cola" 
		buffer.add(elMensaje);

		System.out.println("Se ingresó un mensaje al buffer. Info del Mensaje: "+elMensaje.darMensaje());
		mensajesAgregados++;
		System.out.println("holss");

	}

	public synchronized void sacarMensaje() {
		Mensaje mensajeSacado = buffer.removeFirst();
		synchronized(mensajeSacado) {
			int mensajeSinProcesar = mensajeSacado.darMensaje();
			int resultado=mensajeSacado.responderSolicitud();
			System.out.println("Se ha procesado el mensaje con info: " +mensajeSinProcesar +". El resultado es: "+ resultado);
		}
		notifyAll();
	}

	public int darMensajesEnBuffer() {
		if( mensajesAgregados!=0)
			return mensajesAgregados--;
		return mensajesAgregados;
	}

	public synchronized void clienteAtendido() {
		clientesFaltan--;
	}

	public int getMaximoBuff() {
		return maximoBuff;
	}

	public int getClientesFaltan() {
		return clientesFaltan;
	}

	/**Retorna true si no hay cupo en la lista de mensajes. False de lo contrario.*/
	public boolean noHayCupo() {
		return buffer.size() == maximoBuff;
	}



}
