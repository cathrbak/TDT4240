package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyHeliGame;
import com.mygdx.game.sprites.Helicopter;

public class PlayState extends State{
    private Helicopter helicopter;
    private Texture bg;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        helicopter = new Helicopter(50, 300);
        //her setter man størrelsen på hvor mye man vil se av skjermen
        cam.setToOrtho(false, MyHeliGame.WIDTH, MyHeliGame.HEIGHT);
        bg = new Texture("background.png");
    }

        @Override
    protected void handleInput() {


    }

    @Override
    public void update(float dt) {
        handleInput();
        helicopter.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth/2), 0);
        sb.draw(helicopter.getTexture(), helicopter.getPosition().x, helicopter.getPosition().y);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
