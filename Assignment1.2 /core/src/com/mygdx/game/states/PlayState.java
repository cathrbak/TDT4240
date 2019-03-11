package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyHeliGame;

import com.mygdx.game.sprites.Helicopter;


import com.badlogic.gdx.math.Rectangle;


public class PlayState extends State {
    private Helicopter helicopter;
    private Texture bg;
    private Texture up;
    private Texture down;
    private Texture left;
    private Texture right;

    private Rectangle upR;
    private Rectangle downR;
    private Rectangle leftR;
    private Rectangle rightR;
    BitmapFont font = new BitmapFont();

    public PlayState(GameStateManager gsm) {
        super(gsm);
        helicopter = new Helicopter();
        //her setter man størrelsen på hvor mye man vil se av skjermen
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
    protected void handleInput() {
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
        //right.setPosition(110,30);
       // left.setPosition(50,30);
       // up.setPosition(80,50);
       // down.setPosition(0,30);


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
