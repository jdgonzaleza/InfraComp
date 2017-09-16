package Caso;

public class Servidor extends Thread {

	/**Buffer al cual va a retirar los mensajes*/
	private Buffer buff;

	public Servidor(Buffer buff) {
		this.buff= buff;
	}

	public void run() {
		while(buff.getClientesFaltan()>0) {
			synchronized(this) {
				if(buff.darMensajesEnBuffer()==0) {
					try {
						wait();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}else {
					notify();
					buff.sacarMensaje();
				}

			}
		}
	}
}
