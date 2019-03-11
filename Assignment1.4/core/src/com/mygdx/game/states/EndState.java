package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.MyHeliGame;


public class EndState extends State {

    private TextureRegion bg;
    public String winner;
    BitmapFont font = new BitmapFont();

    protected EndState(GameStateManager gsm, String winner) {
        super(gsm);
        this.winner = winner;
        bg = new TextureRegion(new Texture("background2.png"));
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0, MyHeliGame.WIDTH, MyHeliGame.HEIGHT);
        font.draw(sb, winner + " won the game, Congratulations", 10,240);
        font.setColor(Color.PINK);
        font.getData().setScale(3);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
