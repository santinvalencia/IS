package com.coreis.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.coreis.game.MyGdxGame;
import com.coreis.game.utiles.Render;

public class Credits implements Screen{
	public static final float SPEED = 120;
	SpriteBatch batch;	
	MyGdxGame game;
	Texture creditos;
	Stage stage;
	Sound songCreditos;
	Boolean songCreditosBool = false;
	public Credits(MyGdxGame game) {
        this.game= game;
        
    }
	
	@Override
	public void show() {
		batch = Render.batch;
		creditos = new Texture("fondos/CREDITOS.png");
		stage = new Stage();
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla();
		sonidoCreditos();
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.setScreen(new PantallaMenu(game));
    		songCreditos.stop();
		}
		batch.begin();
		batch.draw(creditos, 0, 0);
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
		if(!songCreditosBool) {
			songCreditos = Gdx.audio.newSound(Gdx.files.internal("sonidos/creditosSong.ogg"));
			songCreditos.setVolume(100, 25);
			songCreditos.play();
			songCreditosBool = true;
		}
	}


}
