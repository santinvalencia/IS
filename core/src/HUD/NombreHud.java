package HUD;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.coreis.game.clases.Jugador;
import com.coreis.game.utiles.Colores;
import com.coreis.game.utiles.EstiloFuente;

import ENUMS.Controles;
import Interfaces.HUD;
import entities.Entity;

public class NombreHud{

	private Stage stage;
	private ScreenViewport vw;
	private Table tabla, contenedor;
	private Label etiqueta;
	private Label.LabelStyle estiloFuente, estiloPeligro, fuenteAdvertencia;
	
	
	public NombreHud(Entity e) {
		crearFuentes(); // Primero crear las fuentes
	    crearActores(e); // Después de crear las fuentes
	    poblarStage(e);
	    refrescarTexto(e);
	    stage.setDebugAll(false);
	}
	public NombreHud(Entity e, String a) {
		crearFuentes(); // Primero crear las fuentes
	    crearActores2(e); // Después de crear las fuentes
	    poblarStage(e);
	    refrescarTexto(e);
	    stage.setDebugAll(false);
	}

	public void crearFuentes() {
		estiloFuente = EstiloFuente.generarFuente(50, Colores.BLANCO, false);
		
	}

	public void crearActores(Entity e) {
		vw = new ScreenViewport();
		stage = new Stage(vw);
		tabla = new Table();
		if(e.getType().getId() == "Carlitos") {
			tabla.setPosition(stage.getWidth()/stage.getWidth()+300, stage.getHeight()-50);
		}
		if(e.getType().getId() == "Echeverri") {
			tabla.setPosition(stage.getWidth()-200,stage.getHeight()-50);
		}
		contenedor = new Table();
		etiqueta = new Label(""+e.getType().getId(), estiloFuente);
		
		
	}
	public void crearActores2(Entity e) {
		vw = new ScreenViewport();
		stage = new Stage(vw);
		tabla = new Table();
		tabla.setPosition(625, stage.getHeight()-350);
		contenedor = new Table();
		etiqueta = new Label(""+e.getType().getId(), estiloFuente);
		
		
	}

	public void poblarStage(Entity e) {
		stage.addActor(tabla);
		tabla.add(contenedor).size(300,100);
		contenedor.add(etiqueta).size(300,100);
		
		
	}

	public void dibujar() {
		stage.draw();
	}

	public void refrescarTexto(Entity e) {
		etiqueta.setText(e.getType().getId());
		
	}

}
