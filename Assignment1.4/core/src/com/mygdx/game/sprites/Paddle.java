package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyHeliGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Paddle {
    private Vector3 position;
    private Vector3 velocity;
    private TextureRegion paddle;


    public Paddle(float x, float y){
        position = new Vector3(x,y, 0);
        velocity = new Vector3(0,0,0);
        paddle = new TextureRegion(new Texture("paddle.png"));
    }

    //TODO: få paddle til å bevege seg i forhold til hvor musen peker, likt som heli2
    // counte treff på ball hvis man mister ballen
    //pass på at paddelen holder seg innenfor sitt område: sett en bredde som i hele spillet: heli1
    public void update(float dt){
        /*if (position.x < 0) {
            position.add(3, 0, 0);
            // lastTouched = true;
        } else if (position.x > MyHeliGame.WIDTH - paddle.getTexture().getWidth()) {
            position.add(-3, 0, 0);
            // lastTouched = false;
        }
*/
        if (position.y < 20) {
            position.add(0, 3, 0);
        } else if (position.y >= 342) {
            position.add(0,-3 , 0);
        }

    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return paddle;
    }
    public void follow(Vector3 ballPos){
        position.y = ballPos.y;
    }

    public void moveUp(){
        position.y += 2;
    }

    public void moveDown(){
        position.y -= 2;
    }
   // public Rectangle getBounds(){ return bounds; }


    public float getVelocity(){
        return velocity.x;
    }

    public void reverseDirection(){
        velocity.set(-velocity.x, -velocity.y,0);
    }
}
