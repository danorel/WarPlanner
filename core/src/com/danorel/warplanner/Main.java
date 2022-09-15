package com.danorel.warplanner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private Sprite player1;
	private Sprite player2;
	private Music rainMusic;
	private OrthographicCamera camera;
	private Vector3 touch;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		batch = new SpriteBatch();

		touch = new Vector3();

		Texture player1Texture = new Texture(Gdx.files.internal("battle-machine-player-1.jpg"));
		player1 = new Sprite(player1Texture, 0, 0, 50, 50);

		Texture player2Texture = new Texture(Gdx.files.internal("battle-machine-player-2.jpg"));
		player2 = new Sprite(player2Texture,  0, 0, 50, 50);
		player2.setX(60);

		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		rainMusic.play();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		if (Gdx.input.isTouched()) {
			touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touch);
			player1.setX(touch.x - player1.getWidth() / 2);
			player1.setY(touch.y - player1.getWidth() / 2);
			player2.setX(800 - touch.x - player2.getWidth() / 2);
			player2.setY(480 - touch.y - player2.getWidth() / 2);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			float absoluteDiff = 25 * Gdx.graphics.getDeltaTime();
			float numericalDiff = Gdx.input.isKeyPressed(Input.Keys.LEFT) ? -absoluteDiff : absoluteDiff;
			player1.setX(player1.getX() + numericalDiff);
			player2.setX(player2.getX() - numericalDiff);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			float absoluteDiff = 25 * Gdx.graphics.getDeltaTime();
			float numericalDiff = Gdx.input.isKeyPressed(Input.Keys.DOWN) ? -absoluteDiff : absoluteDiff;
			player1.setY(player1.getY() + numericalDiff);
			player2.setY(player2.getY() - numericalDiff);
		}
		batch.draw(player1, player1.getX(), player1.getY());
		batch.draw(player2, player2.getX(), player2.getY());
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
