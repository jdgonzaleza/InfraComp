package Caso;

public class Servidor extends Thread {

	/**Buffer al cual va a retirar los mensajes*/
	private Buffer buff;

	public Servidor(Buffer buff) {
		this.buff= buff;
	}

	public void run() {
		Mensaje mensaje;
		int respuesta;
		while(true) {
			try {
				mensaje=buff.sacarMensaje();
				respuesta = mensaje.darMensaje()+1;
				mensaje.responderSolicitud(respuesta);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}

