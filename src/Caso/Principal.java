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
			int numCli = Integer.parseInt(datos.getProperty("numCli"));
			int numServi = Integer.parseInt(datos.getProperty("numServi"));
			String [] losMensajes = datos.getProperty("nMensajes").split(";");
			int [] numMensajes = new int [losMensajes.length];
			System.out.println("Parámetros:");
			System.out.println("Número de servidores: "+ numServi);
			System.out.println("Número de clientes: "+numCli);
			int c;
			for(int i =0; i<losMensajes.length ; i++)
			{
				c=Integer.parseInt(losMensajes[i]);
				numMensajes[i] = c;
				System.out.println("Cantidad de mensajes del cliente "+(i+1)+": "+c);
			}
			int  tamBuffer =Integer.parseInt(datos.getProperty("tamBuffer"));
			System.out.println("Tamaño del buffer: "+tamBuffer);
			System.out.println("================================================================================================");
			System.out.println("Ejecución:");
			Buffer buffer = new Buffer(tamBuffer, numCli);
			for (int i = 0; i < numServi; i++)
			{
				Servidor serv = new Servidor( buffer);
				serv.start();
			}
			for (int i = 0; i < numCli; i++)
			{
				Cliente cli = new Cliente ((i+1),numMensajes [i], buffer);
				cli.start();
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
