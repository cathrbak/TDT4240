package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyHeliGame;
import com.mygdx.game.sprites.Helicopter;


public class PlayState implements State {
    private Helicopter helicopter;
    private Texture bg, up, down, left, right;
    private Rectangle upR, downR, leftR, rightR;
    private BitmapFont font = new BitmapFont();
    GameStateManager gsm;

    public PlayState(GameStateManager gsm) {
        helicopter = new Helicopter();
        this.gsm = gsm;
        //Set the size of the screen
        cam.setToOrtho(false, 480.0F, 800.0F);
        bg = new Texture("background.png");
        up = new Texture("up.png");
        down = new Texture("down.png");
        left = new Texture("left.png");
        right = new Texture("right.png");
        downR = new Rectangle(80, 45, 50, 50);
        upR = new Rectangle(80, 115, 50, 50);
        leftR = new Rectangle(45, 80, 50, 50);
        rightR = new Rectangle(115, 80, 50, 50);
    }
    @Override
    public void alert(GameStateManager ctx){
        System.out.println("PlayState...");
    }

    @Override
    public void handleInput() {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            cam.unproject(tmp);
                if(rightR.contains(tmp.x, tmp.y)){
                    this.helicopter.moveRight();
                }
                if(leftR.contains(tmp.x, tmp.y)){
                    this.helicopter.moveLeft();
                }
                if(upR.contains(tmp.x, tmp.y)){
                    this.helicopter.moveUp();
                }
                if(downR.contains(tmp.x, tmp.y)){
                    this.helicopter.moveDown();
                }
    }

    @Override
    public void update(float dt) {
        handleInput();
        helicopter.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0, MyHeliGame.WIDTH, MyHeliGame.HEIGHT);
        sb.draw(helicopter.getTexture(), helicopter.getPosition().x, helicopter.getPosition().y);
        font.draw(sb, helicopter.getPosString(), 20, 780);
        sb.draw(down, 80, 45, 50, 50);
        sb.draw(up, 80, 115, 50, 50);
        sb.draw(left, 45, 80, 50, 50);
        sb.draw(right, 115, 80, 50, 50);
        sb.end();
    }

    @Override
    public void dispose() {
    }
}
