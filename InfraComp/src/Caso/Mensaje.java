package Caso;

public class Mensaje {
	/**Número que el cliente envía. El mensaje*/
	private int elMensaje;
	/**El buffer al cual va a parar el mensaje*/
	private Buffer buffer;
	
	public Mensaje(int pMensaje, Buffer pBuffer) {
		elMensaje = pMensaje;
		buffer = pBuffer;
	}
	
	/**Retorna el mensaje*/
	public int darMensaje() {
		return elMensaje;
	}
	
	public void insertar() throws InterruptedException{
		buffer.insertarMensaje(this);
		
		synchronized(this) {
			wait();
		}
	}
	
	public int responderSolicitud() {
		elMensaje ++;
		notify();
		return elMensaje;
	}
	public int getelMensaje() {
		return elMensaje;
	}

}
