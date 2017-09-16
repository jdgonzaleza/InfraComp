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
		Random ran = new Random();
		while(numMensajes>0) {
			int mensajito = ran.nextInt(5);
			Mensaje mensaje = new Mensaje(mensajito, buff);
			//Si el buffer está ocupado, el cliente se queda en espera activa.(Listo para ejecutar)
			if(buff.noHayCupo()) yield();
			try {
				mensaje.insertar();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			numMensajes--;
		}
		buff.clienteAtendido();
		System.out.println("El cliente con id: "+ id+ " ya fue atendido.");
	}
	

}
