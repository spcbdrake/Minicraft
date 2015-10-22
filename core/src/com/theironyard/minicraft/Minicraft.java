package com.theironyard.minicraft;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Minicraft extends ApplicationAdapter {
    final int WIDTH = 100;
    final int HEIGHT = 100;

    SpriteBatch batch;
    TextureRegion down, up, right, left;
    FitViewport viewport;
    float time = 0;

    float x = 0;
    float y = 0;
    float xVelocity = 0;
    float yVelocity = 0;

    final float MAX_VELOCITY = 100;


    @Override
    public void create () {
        batch = new SpriteBatch();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Texture tiles = new Texture("tiles.png");
        TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
        down = grid[6][0];
        up = grid[6][1];
        right = grid[6][2];
        left = grid[6][3];
        right = grid[6][3];
        left = new TextureRegion(right);
        left.flip(true, false);
    }

    @Override
    public void render () {
        move();
        draw();
    }
    @Override
    public void resize(int width, int height){
        viewport.update(width, height);
    }

    void move() {
        if (x >= 0 && y >= 0) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                yVelocity = MAX_VELOCITY;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                yVelocity = MAX_VELOCITY * -1;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                xVelocity = MAX_VELOCITY;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                xVelocity = MAX_VELOCITY * -1;
            }
        }
        float oldX = x;
        float oldY = y;
        x += xVelocity * Gdx.graphics.getDeltaTime();
        y += yVelocity * Gdx.graphics.getDeltaTime();

        if (x< 0 || x > (Gdx.graphics.getWidth()-WIDTH)) {
            x = oldX;
        }
        if (y < 0 || y > (Gdx.graphics.getHeight()-HEIGHT)) {
            y = oldY;
        }



        xVelocity *= 0.7;
        yVelocity *= 0.7;

    }

    void draw() {
        time += Gdx.graphics.getDeltaTime();

        TextureRegion img;
        if (Math.round(yVelocity) > 0) {
            img = up;
        }
        else if (Math.round(yVelocity) < 0){
            img = down;
        }
         else if (Math.round(xVelocity) > 0) {
            img = right;
        }
        else if (Math.round(xVelocity) < 0) {
            img= left;
        }
        else {
            img = down;
        }


        Gdx.gl.glClearColor( 0, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, x, y, WIDTH, HEIGHT);
        batch.end();

    }
}
