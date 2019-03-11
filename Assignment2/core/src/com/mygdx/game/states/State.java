package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


//Interface for the states
public interface State {
    OrthographicCamera cam = new OrthographicCamera();
    Vector3 mouse = new Vector3();
    GameStateManager gsm = new GameStateManager();

    void handleInput();
    void update(float dt);
    void render(SpriteBatch sb);
    void dispose();
    void alert(GameStateManager ctx);

}

