package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Helicopter {
    private Vector3 position;
    private Vector3 velocity;
    private static final int GRAVITY = -1;
    private TextureRegion helicopter;
    private boolean lastTouchedX;
    private boolean lastTouchedY;
   // private ArrayList<String> spriteList;
    //private ArrayList<TextureRegion> helicopters;
    private Animation helicopterAnimation;
    private Rectangle bounds;
    private Texture texture;


    public Helicopter(int x, int y){
        position = new Vector3(x,y, 0);
        velocity = new Vector3(0,0,0);
        helicopter = new TextureRegion(new Texture("heli1.png"));
        lastTouchedX = false;
        lastTouchedY = false;
        texture = new Texture("animation3.png");
        helicopterAnimation = new Animation(new TextureRegion(texture), 4,0.5f);
        bounds = new Rectangle(x,y,helicopter.getRegionWidth(), helicopter.getRegionHeight());

    }
    public void update(float dt){
        helicopterAnimation.update(dt);
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
            velocity.add(0, -GRAVITY,0);
        }
        velocity.scl(dt);
        position.add(velocity.x,0,0);
        position.add(0, velocity.y,0);

        //left wall check
        if (position.x < 0) {
            position.x = 4;
            //rotateTexture();
            if (!lastTouchedX){
                rotateTexture();
            }
            lastTouchedX = true;
            //helicopterAnimation.getFrame().flip(true,false);
            velocity.set(-GRAVITY, 0, 0);
            position.add(-velocity.x,0,0);
        }

        //Right wall check
       if(position.x > 350){
            position.x = 340;
            if (lastTouchedX){
                rotateTexture();
           }
            lastTouchedX = false;
            velocity.set(GRAVITY, randomGravity(), 0);
            position.add(randomVelocity(),0,0);
        }
        //Roof check
        if(position.y > 765){
            position.y = 750;
            lastTouchedY = false;
            velocity.set(randomGravity(),GRAVITY,0);
            position.add(0, randomVelocity(), 0);
        }
        //Floor check
        if(position.y < 0){
            position.y = 5;
            lastTouchedY = true;
            velocity.set(randomGravity(), -GRAVITY,0);
            position.add(0, randomVelocity(), 0);
        }

        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);

    }

// generating random gravity for natural movement
    private int randomGravity(){
        ArrayList<Integer> gravity = new ArrayList<Integer>();
        gravity.add(-1);
        gravity.add(1);
        int random = gravity.get(new Random().nextInt(gravity.size()));
        return random;
    }
    // generating random velocity for natural movement
    private int randomVelocity(){
        ArrayList<Integer> velocity = new ArrayList<Integer>();
        for (int i = -5; i < 20; i++){
            velocity.add(i);
        }
        int randomV = velocity.get(new Random().nextInt(velocity.size()));
        return randomV;
    }

    //Rotates the textureRegion
    private void rotateTexture(){
        helicopterAnimation.flipFrame();
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return helicopterAnimation.getFrame();
    }

    public void jump(){
        velocity.y = 250;
    }

    public void move(){
        velocity.x = 200;
    }
    public Rectangle getBounds(){ return bounds; }

    public void dispose(){ texture.dispose(); }

    public float getVelocity(){
        return velocity.x;
    }
    public boolean collides(Rectangle player){
        return player.overlaps(bounds);
    }
    public void reverseDirection(){
        velocity.set(-velocity.x, -velocity.y,0);
    }
}
