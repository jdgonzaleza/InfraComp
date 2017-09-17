package Caso;

import java.util.Random;

public class Cliente extends Thread {

	/**identificación del cliente*/
	private int id;

	/**número de mensajes*/
	private int numMensajes;

	/**Buffer a donde va a conectarse el cliente para mandar el mensaje*/
	private Buffer buff;


	public Cliente(int id, int numMensajes, Buffer buff) {
		this.id = id;
		this.numMensajes = numMensajes;
		this.buff = buff;
	}


	public void run() {

		for(int i = 0; i<numMensajes;i++) {
			Random ran = new Random();
			int infoMensaje = ran.nextInt(5);
			Mensaje mensaje = new Mensaje(infoMensaje, buff);
			while(buff.noHayCupo())yield();
			try {
				int respuesta = mensaje.insertar();
				synchronized (System.class) {
					System.out.println("El cliente con id: "+ id+ " ha enviado un mensaje");
					System.out.println("El mensaje era: "+ mensaje.darMensaje());
					System.out.println("La respuesta es: "+ respuesta);
					System.out.println("\n");

				}

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}


}
