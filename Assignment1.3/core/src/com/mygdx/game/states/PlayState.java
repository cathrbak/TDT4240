package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.MyHeliGame;

import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Helicopter;

import java.util.ArrayList;
import java.util.Random;

public class PlayState extends State {
    private Helicopter helicopter;
    private Bird bird;
    private Texture bg;
    BitmapFont font = new BitmapFont();
    Random rand = new Random();


    public PlayState(GameStateManager gsm) {
        super(gsm);
        helicopter = new Helicopter(randomStartPosX(), randomStartPosY());
        bird = new Bird(randomStartPosX(),randomStartPosY());
        //her setter man størrelsen på hvor mye man vil se av skjermen
        cam.setToOrtho(false, MyHeliGame.WIDTH, MyHeliGame.HEIGHT);
        bg = new Texture("background2.png");
    }

        @Override
    protected void handleInput() {
            if (Gdx.input.justTouched()) {
                helicopter.jump();
            }
            if (Gdx.input.justTouched()){
                helicopter.move();
            }
    }

    @Override
    public void update(float dt) {
        handleInput();
        dt += Gdx.graphics.getDeltaTime();
        helicopter.update(dt);
        bird.update(dt);
        if(bird.collides(helicopter.getBounds())){
            helicopter.reverseDirection();
            bird.reverseDirection();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0, MyHeliGame.WIDTH, MyHeliGame.HEIGHT);
        sb.draw(helicopter.getTexture(), helicopter.getPosition().x, helicopter.getPosition().y);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        //font.draw(sb, String.valueOf(helicopter.getVelocity()),350,200);
        sb.end();
    }

    @Override
    public void dispose() {
    }

    public int randomStartPosX(){
        ArrayList<Integer> positions = new ArrayList<Integer>();
        for(int i = 0; i < 480; i++){
            positions.add(i);
        }
        return positions.get(rand.nextInt(positions.size()));
    }
    public int randomStartPosY(){
        ArrayList<Integer> positions = new ArrayList<Integer>();
        for(int i = 0; 780 > i; i++) {
            positions.add(i);
        }
        return positions.get(rand.nextInt(positions.size()));
    }
}
