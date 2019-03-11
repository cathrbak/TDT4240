package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import java.util.Random;

public class Helicopter {
    private Vector3 position, velocity;
    private static final int GRAVITY = -1;
    private TextureRegion helicopter;
    private boolean lastTouchedX, lastTouchedY;

    public Helicopter(int x, int y){
        position = new Vector3(x,y, 0);
        velocity = new Vector3(0,0,0);
        helicopter = new TextureRegion(new Texture("heli1.png"));
        lastTouchedX = false;
        lastTouchedY = false;
    }

    public void update(float dt){
        if(position.x > 0 && !lastTouchedX && position.x < 125){
            velocity.add(GRAVITY, 0, 0);
        }
        if(position.x > 0 && position.x < 125 && lastTouchedX){
            velocity.add(-GRAVITY, 0, 0);
        }
        if(position.y > 0 && !lastTouchedY && position.y < 300){
            velocity.add(0, GRAVITY, 0);
        }
        if(position.y > 0 && lastTouchedY && position.y < 300){
            velocity.add(0, -GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(velocity.x,0,0);
        position.add(0, velocity.y, 0);

        // Left wall check
        if (position.x < 0) {
            position.x = 4;
            rotateTexture();
            lastTouchedX = true;
            velocity.set(-GRAVITY, GRAVITY, 0);
            position.add(-velocity.x,0 ,0);
        }

       // Right-wall check
       if(position.x > 320){
            position.x = 310;
            rotateTexture();
            lastTouchedX = false;
            velocity.set(GRAVITY, -GRAVITY, 0);
            position.add(velocity.x,0,0);
        }

        // Checking the roof
        if (position.y > 780){
            position.y = 750;
            lastTouchedY = false;
            velocity.set(GRAVITY, GRAVITY, 0);
            position.add(0, velocity.y, 0);
        }

        // Floor check
        if (position.y < 0){
            position.y = 5;
            lastTouchedY = true;
            velocity.set(-GRAVITY, -GRAVITY, 0);
            position.add(0, -velocity.y, 0);
        }
         velocity.scl(1/dt); 
    }

    // Rotates the sprite when hitting a wall
    private void rotateTexture(){
        helicopter.flip(true, false);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return helicopter;
    }

    public void moveUp(){
        position.y += 4;
    }

    public void moveDown(){
        position.y -= 4;
    }

}
