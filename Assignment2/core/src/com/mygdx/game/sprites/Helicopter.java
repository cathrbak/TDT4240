package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyHeliGame;

public final class Helicopter {
    private static final Helicopter INSTANCE = new Helicopter();
    private Vector3 position;
    private Vector3 velocity;
    private static final int GRAVITY = -1;
    private TextureRegion helicopter;

    public Helicopter(){
        position = new Vector3(50,300, 0);
        velocity = new Vector3(0,0,0);
        helicopter = new TextureRegion(new Texture("heli1.png"));

    }

    public static Helicopter getInstance(){
        return INSTANCE;
    }

    public void update(float dt) {
        if (position.x < 0) {
            position.set(0, position.y, position.z);
        } else if (position.x > MyHeliGame.WIDTH - helicopter.getTexture().getWidth()) {
            position.add(-3, 0, 0);
        }
        if (position.y < 0) {
            position.add(0, 3, 0);
        } else if (position.y > (MyHeliGame.HEIGHT - 2 * helicopter.getTexture().getHeight())) {
            position.add(0, -3, 0);
        }
    }

    //Rotates sprite when hitting a wall
    private void rotateTexture(){
        helicopter.flip(true, false);
    }

    public Vector3 getPosition() {
        return position;
    }

    public String getPosString(){
        String pos= "[";
        String x = Double.toString(Math.floor(position.x));
        String y = Double.toString(Math.floor(position.y));
        pos += x + " , " + y + "]";
        return pos;
    }

    public TextureRegion getTexture() {
        return helicopter;
    }


    public void moveLeft(){
        position.x -= 4;

    }
    public void moveRight(){
        position.x += 4;

    }
    public void moveUp(){
        position.y += 4;
    }
    public void moveDown(){ position.y -= 4; }
}
