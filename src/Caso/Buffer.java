package Caso;

import java.util.LinkedList;

public class Buffer {

	/**Número máximo de mensajes que puede recibir el buffer*/
	private int maximoBuff;
	
	/**número de mensajes agregados a la lista*/
	private int mensajesAgregados;

	/**La cola donde se van guardando los mensjaes*/
	private LinkedList<Mensaje> buffer;


	public Buffer(int maximoBuff, int numClientes) {
		this.maximoBuff = maximoBuff;
		buffer = new LinkedList<Mensaje>();
		mensajesAgregados = 0;
	}

	/**Inserta el mensaje (dormido) al final de la lista de mensajes que se tiene.*/
	public synchronized void insertarMensaje(Mensaje elMensaje) throws InterruptedException{

		if(mensajesAgregados<maximoBuff) {
			notifyAll();
		}
		buffer.add(elMensaje);
		mensajesAgregados++;

	}
	/**Se saca la cabeza de la fila de mensajes. Si no hay mensajes en la lista entonces se duerme el buffer.*/
	public synchronized Mensaje sacarMensaje() throws InterruptedException {

		if(mensajesAgregados>1) {
			notifyAll();
		}
		while(mensajesAgregados==0) {
			wait();
		}
		Mensaje mensajeEnviar = buffer.removeFirst();
		mensajesAgregados--;
		return mensajeEnviar;

	}
	/*Número de mensajes en el buffer*/
	public synchronized int darMensajesEnBuffer() {
		return mensajesAgregados;
	}
	/**Retorna true si no hay cupo en la lista de mensajes. False de lo contrario.*/
	public boolean noHayCupo() {
		return buffer.size() == maximoBuff;
	}




}
