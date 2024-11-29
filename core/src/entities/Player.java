package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import mundo.GameMap;

public class Player extends Entity {
	private static final int SPEED = 200;
	private static final int JUMP_VELOCITY = 5;
	
	
	
	Texture image;
	
	public Player(float x , float y, EntityType type , GameMap map, String img) {
		super(x,y,map);
		super.type = type;
		this.image = new Texture(img);
	} 

	
	
	
	
	@Override
	public void update(float deltaTime, float gravity) {
		if (super.type == EntityType.CARLITOS) {
			if (Gdx.input.isKeyPressed(Keys.SPACE) && grounded)
				this.velocityY += JUMP_VELOCITY * getWeight();
			else if (Gdx.input.isKeyPressed(Keys.SPACE) && !grounded && this.velocityY > 0)
				this.velocityY += JUMP_VELOCITY * getWeight() * deltaTime;
			
			super.update(deltaTime, gravity);//Apply gravity
			
			if (Gdx.input.isKeyPressed(Keys.A))
				moveX(-SPEED * deltaTime);
			
			if (Gdx.input.isKeyPressed(Keys.D))
				moveX(SPEED * deltaTime);
		}
		if (super.type == EntityType.ECHEVERRI) {
			if (Gdx.input.isKeyPressed(Keys.L) && grounded)
				this.velocityY += JUMP_VELOCITY * getWeight();
			else if (Gdx.input.isKeyPressed(Keys.L) && !grounded && this.velocityY > 0)
				this.velocityY += JUMP_VELOCITY * getWeight() * deltaTime;
			
			super.update(deltaTime, gravity);//Apply gravity
			
			if (Gdx.input.isKeyPressed(Keys.LEFT))
				moveX(-SPEED * deltaTime);
			
			if (Gdx.input.isKeyPressed(Keys.RIGHT))
				moveX(SPEED * deltaTime);
		}
		
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
	}





	
	

}
