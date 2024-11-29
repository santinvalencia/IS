package com.coreis.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.coreis.game.MyGdxGame;
import com.coreis.game.utiles.Render;

import HUD.NombreHud;
import entities.Entity;

public class JuegoTerminado implements Screen{
	SpriteBatch batch;	
	MyGdxGame game;
	Texture fondoFinal;
	Stage stage;
	Sound songGameOver;
	Boolean gameOverBool = false;
	NombreHud ganadorHud;
	Entity e;
	public JuegoTerminado(MyGdxGame game, Entity e) {
		this.game = game;
		batch = Render.batch;
		this.e = e;
	}
	@Override
	public void show() {
		fondoFinal = new Texture("fondos/gameover.png");
		ganadorHud = new NombreHud(e, "asd");
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla();
		
		ganadorHud.refrescarTexto(e);
		sonidoCreditos();
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			songGameOver.stop();
			game.setScreen(new PantallaMenu(game));
		}
		
		batch.begin();
		batch.draw(fondoFinal, 0, 0);
		
		batch.end();
		batch.begin();
		ganadorHud.dibujar();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		
	}

	public void sonidoCreditos() {
		if(!gameOverBool) {
			songGameOver = Gdx.audio.newSound(Gdx.files.internal("sonidos/victoria.ogg"));
			songGameOver.setVolume(100, 25);
			songGameOver.play();
			gameOverBool = true;
		}
	}

}
