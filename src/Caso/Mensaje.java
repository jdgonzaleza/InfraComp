package Caso;

public class Mensaje {
	/**Número que el cliente envía. El mensaje*/
	private int elMensajeOriginal;
	
	/**El número que se responde*/
	private int elMensajeRespuesta;
	/**El buffer al cual va a parar el mensaje*/
	private Buffer buffer;

	public Mensaje(int pMensaje, Buffer pBuffer) {
		elMensajeOriginal = pMensaje;
		buffer = pBuffer;
		elMensajeRespuesta = 0;
	}

	/**Retorna el mensaje Original*/
	public synchronized int darMensaje() {
		return elMensajeOriginal;
	}
	
	/**insertar() se encarga de enviar el mensaje al buffer para que después pueda ser atendido por un servidor.
	   Así mismo duerme al mensaje para que el seridor puedar llegar y procesar el mensaje para desués volverlo a mandar al 
	   cliente.*/
	public synchronized int insertar() throws InterruptedException{

		buffer.insertarMensaje(this);
		while(elMensajeRespuesta == 0) {
			wait();
		}
		return elMensajeRespuesta;
	}
	/**Se encarga de coger el mensaje procesado por el servidor y lo asigna al mensaje de Respuesta (al dormido). 
	   Despierta al mensaje.*/
	public synchronized void responderSolicitud(int respuesta) {
		elMensajeRespuesta = respuesta;
		notify();
	}
}
