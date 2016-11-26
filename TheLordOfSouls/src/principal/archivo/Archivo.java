package principal.archivo;

import java.io.FileInputStream;
import java.util.Properties;

import principal.Constantes;

public class Archivo {

	public String obternerIp() {
		String ip="";
		try{
		Properties p = new Properties();
		p.load(new FileInputStream(Constantes.RUTA_ARCHIVO_IP));

		ip=p.getProperty("IP");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ip;
	}
}
