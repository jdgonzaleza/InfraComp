package Caso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Principal {

	public static void main(String [] args)
	{
		Properties datos= new Properties();
		File f = new File ("./data/caso1.properties");
		try 
		{
			FileInputStream in= new FileInputStream(f);
			datos.load(in);
			int nClientes = Integer.parseInt(datos.getProperty("numCli"));
			int nServidores = Integer.parseInt(datos.getProperty("numServi"));
			String [] nC = datos.getProperty("nMensajes").split(";");
			int [] numMensajes = new int [nC.length];
			System.out.println("Parámetros:");
			System.out.println("Número de servidores: "+ nServidores);
			System.out.println("Número de clientes: "+nClientes);
			int c;
			for(int i =0; i<nC.length ; i++)
			{
				c=Integer.parseInt(nC[i]);
				numMensajes[i] = c;
				System.out.println("Cantidad de mensajes del cliente "+(i+1)+": "+c);
			}
			int  tamBuffer =Integer.parseInt(datos.getProperty("tamBuffer"));
			System.out.println("Tamaño del buffer: "+tamBuffer);
			System.out.println("================================================================================================");
			System.out.println("Ejecución:");
			Buffer buffer = new Buffer(tamBuffer, nClientes);

			for (int i = 0; i < nClientes; i++)
			{
				new Cliente ((i+1),numMensajes [i], buffer).start();
			}
			for (int i = 0; i < nServidores; i++)
			{
				new Servidor( buffer).start();
			}


		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}



	}

}
