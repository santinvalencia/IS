package com.coreis.game.pantallas;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.coreis.game.MyGdxGame;
import com.coreis.game.clases.Jugador;
import com.coreis.game.utiles.Recursos;
import com.coreis.game.utiles.Render;

import ENUMS.Controles;
import ENUMS.Velocidad;
import HUD.NombreHud;
import HUD.VidaHud;
import entities.Entity;
import entities.Player;
import mundo.GameMap;
import mundo.TileType;
import mundo.TiledGameMap;
public class Juego implements Screen{
	OrthographicCamera cam;
	SpriteBatch batch;	
	GameMap gameMap;
	final MyGdxGame game;
	VidaHud VidaJairo;
	VidaHud VidaCarlitos;
	NombreHud NombreCarlitos;
	NombreHud NombreJairo;
	public static final float SPEED = 120;
	Jugador Carlitos;
	Jugador Jairo;
	Texture fondo1;
	Sound golpear1;
	Sound ost;
	boolean ostBool = false;
	Stage stage;
	
	public Juego(MyGdxGame game) {
        this.game= game;
    }
	@Override
	public void show() {
		batch = Render.batch;
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		//
		cam= new OrthographicCamera();
		cam.setToOrtho(false, w, h);
		cam.update();
		gameMap = new TiledGameMap();
		//
		
		stage = new Stage();
		crearBotonMenu();
		
		
		
		fondo1 = new Texture(Recursos.FONDOJUEGO2);
		VidaJairo = new VidaHud(gameMap.getEntities().get(0));
		VidaCarlitos = new VidaHud(gameMap.getEntities().get(1));
		NombreJairo = new NombreHud(gameMap.getEntities().get(0));
		NombreCarlitos = new NombreHud(gameMap.getEntities().get(1));
		fondo1 = new Texture(Recursos.FONDOJUEGO);
		
	}
	
	@Override
	public void render(float delta) {
		Render.limpiarPantalla();
		sonidoOST();
		VidaJairo.refrescarTexto(gameMap.getEntities().get(0));
		VidaCarlitos.refrescarTexto(gameMap.getEntities().get(1));
		NombreJairo.refrescarTexto(gameMap.getEntities().get(1));
		NombreCarlitos.refrescarTexto(gameMap.getEntities().get(0));
		if (gameMap.getEntities().get(0).getVida()<= 0) {
			game.setScreen(new JuegoTerminado(game, gameMap.getEntities().get(0)));
			ost.stop();
		}else if (gameMap.getEntities().get(1).getVida() <= 0) {
			game.setScreen(new JuegoTerminado(game, gameMap.getEntities().get(1)));
			ost.stop();
		}
		
		quitarVida(gameMap.getEntities().get(1), Keys.V);
		quitarVida(gameMap.getEntities().get(0), Keys.B);
		
		batch.begin();
		batch.draw(fondo1, 0, 0);
			
		batch.end();
		stage.draw();
        stage.act(delta);
		
		
		cam.update();
		gameMap.update(Gdx.graphics.getDeltaTime());
		
		
		
		
		batch.begin();
		gameMap.render(cam, batch);
		VidaJairo.dibujar();
		VidaCarlitos.dibujar();
		NombreJairo.dibujar();
		NombreCarlitos.dibujar();
		
		batch.end();
		
	}
	
	
	public void sonidoOST() {
		if(!ostBool) {
			ost = Gdx.audio.newSound(Gdx.files.internal("sonidos/ost2.ogg"));
			ost.setVolume(100, 25);
			ost.play();
			ostBool = true;
		}
		
	}
	public void quitarVida(Entity e, int a) {
		if(Gdx.input.isKeyJustPressed(a)) {
			e.setVida(e.getVida()-10);
			golpear1 = Gdx.audio.newSound(Gdx.files.internal("sonidos/GOLPEAR1.ogg"));
			golpear1.setVolume(0, 55);
			golpear1.play();
		}
		
	}
	@Override
	public void resize(int width, int height) {
		
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		gameMap.dispose();
		ost.dispose();
	}
	
private void crearBotonMenu() {
		
		Skin skin = new Skin(Gdx.files.internal("makigas/uiskin.json"));
		TextButton menu = new TextButton ("Menu", skin);
		menu.setPosition(520,690);
		menu.setSize(80, 50);
        stage.addActor(menu);
        Gdx.input.setInputProcessor(stage);
        stage.setDebugAll(false);
        menu.addCaptureListener(new InputListener() {
        	@Override
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        		// TODO Auto-generated method stub
        		return true;
        	}
        	@Override
        	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        		super.touchUp(event, x, y, pointer, button);
        		game.setScreen(new PantallaMenu(game));
        		ost.stop();
        	}
        });
		
	}

private void detectarBloque() {
	if (Gdx.input.isTouched()) {
		cam.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
		cam.update();
	}
	if (Gdx.input.justTouched()) {
		Vector3 pos = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		TileType type = gameMap.getTileTypeByLocation(1, pos.x, pos.y);
		
		if (type != null) {
			System.out.println("clickeaste en el tile con id: "+ type.getId()+ " llamado:"+ type.getName());
		}
}
}}
