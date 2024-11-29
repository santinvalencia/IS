package HUD;

import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.coreis.game.clases.Jugador;
import com.coreis.game.utiles.Colores;
import com.coreis.game.utiles.EstiloFuente;

import ENUMS.Controles;
import Interfaces.HUD;
import entities.Entity;
import entities.EntityType;

public class VidaHud{

	private Stage stage;
	private ScreenViewport vw;
	private Table tabla, contenedor;
	private Label etiqueta;
	private Label.LabelStyle estiloFuente, estiloPeligro, fuenteAdvertencia;
	

	public VidaHud(Entity e) {
	    crearFuentes(); // Primero crear las fuentes
	    crearActores(e); // Después de crear las fuentes
	    poblarStage(e);
	    refrescarTexto(e);
	    stage.setDebugAll(false);
	}

	
	public void refrescarTexto(Entity e) {
		etiqueta.setText(e.getVida());
		
	}
	
	
	
	public void crearActores(Entity e) {
		vw = new ScreenViewport();
		stage = new Stage(vw);
		tabla = new Table();
		if(e.getType() == EntityType.CARLITOS ) {
			tabla.setPosition(stage.getWidth()/stage.getWidth()+50, stage.getHeight()-50);;
		}
		if(e.getType() == EntityType.ECHEVERRI) {
			tabla.setPosition(stage.getWidth()-100, stage.getHeight()-50);
		}
		contenedor = new Table();
		etiqueta = new Label(""+e.getVida(), estiloFuente);
		
	}

	
	
	public void poblarStage(Entity e) {
		stage.addActor(tabla);
		tabla.add(contenedor).size(100,100);
		contenedor.add(etiqueta).size(100,100);
		
		
	}

	
	public void dibujar() {
		stage.draw();//Dibujar el hud
		
	}

	
	
	public void crearFuentes() {
		estiloFuente = EstiloFuente.generarFuente(50, Colores.VERDE, false);
	}

}