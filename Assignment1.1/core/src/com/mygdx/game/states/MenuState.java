package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyHeliGame;

public class MenuState extends State{

    private Texture background, playButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.png");
        playButton = new Texture("button.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0,0, 0, 0, MyHeliGame.WIDTH, MyHeliGame.HEIGHT);
        sb.draw(playButton, (MyHeliGame.WIDTH/2) - (playButton.getWidth()/2), MyHeliGame.HEIGHT/2);
        sb.end();
    }

    @Override
    public void dispose(){
        background.dispose();
        playButton.dispose();

    }
}
