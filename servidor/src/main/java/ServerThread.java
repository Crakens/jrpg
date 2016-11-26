package principal.cs;		
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import principal.Constantes;
import principal.GestorPrincipal;
import principal.peticiones.*;

public class ServerThread extends Thread{
	private Socket s;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Server sv;	

	public ServerThread(Socket s, Server sv) {
		super();
		this.sv = sv;
		this.s = s;
		try {
			this.oos = new ObjectOutputStream(s.getOutputStream());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problema generando el OOS","Error en crear ServerThread", JOptionPane. ERROR_MESSAGE);
		}
		try {
			this.ois = new ObjectInputStream(s.getInputStream());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problema generando el OIS","Error en crear ServerThread", JOptionPane. ERROR_MESSAGE);
		}
	}

	@Override
	public void run() {
		while(true){
			Mensaje mjeIn = null;
			try {
				mjeIn = (Mensaje) ois.readObject();
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "El thread del sv no encontró la clase Mensaje al hacer readObject","Error en run ServerThread", JOptionPane. ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "IOException en el thread del server al recibir un mensaje","Error en run ServerThread", JOptionPane. ERROR_MESSAGE);
			}
			switch (mjeIn.getCodigo()){
			case CodigoPeticion.LOGEO:
				PeticionLogueo petLog = (PeticionLogueo) mjeIn.getObj();
				try {
					oos.writeObject(new Mensaje(sv.getConexionBD().login(petLog.getUsuario(), new String(petLog.getPassword())),null));		//manda mje con el código que devuelva el intento de login en la BD
					oos.flush();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "OOException en el thread del server en intento de login","Error en run ServerThread", JOptionPane. ERROR_MESSAGE);
				}
				break;
				
			case CodigoPeticion.REGISTRO:
				PeticionRegistro petReg = (PeticionRegistro) mjeIn.getObj();
				try {
					oos.writeObject(new Mensaje(sv.getConexionBD().registro(petReg.getUsuario(), new String(petReg.getPassword()), petReg.getEmail()),null));
					oos.flush();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "OOException en el thread del server en intento de registrarse","Error en run ServerThread", JOptionPane. ERROR_MESSAGE);
				}
				break;
				
			case CodigoPeticion.PONER_EN_MAPA_JUGADOR:
				int aux = this.sv.getListaUsuariosMapa1().size();
				this.sv.agregarAListaDeMapa1(oos);
				try {
					if(this.sv.getListaUsuariosMapa1().size() == aux+1)
						oos.writeObject(new Mensaje(CodigoPeticion.PONER_EN_MAPA_JUGADOR_CORRECTO,null));
					else
						oos.writeObject(new Mensaje(CodigoPeticion.PONER_EN_MAPA_JUGADOR_INCORRECTO,null));
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "OOException en el thread del server al intentar poner al gador en el mapa","Error en run ServerThread", JOptionPane. ERROR_MESSAGE);
				}
				
				/*
			 	case CodigoPeticion.LEVANTAR_MAPA:
				PeticionLevantarMapa petMap = (PeticionLevantarMapa) mjeIn.getObj();
				try {
					try{
						GestorPrincipal gp=new GestorPrincipal(petMap.getTitulo(),petMap.getAncho(), petMap.getAlto());
						gp.iniciarJuego();
						gp.iniciarBuclePrincipal();
						oos.writeObject(new Mensaje(CodigoPeticion.LEVANTAR_MAPA_CORRECTO ,null));
					}catch(Exception e){
						oos.writeObject(new Mensaje(CodigoPeticion.LEVANTAR_MAPA_INCORRECTO  ,null));
					}
					oos.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				*/

			}
		}
	}
		
}