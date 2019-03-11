package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyHeliGame;

import java.util.ArrayList;
import java.util.Random;

public class Ball {
        private Vector3 position;
        private Vector3 velocity;
        private TextureRegion ball;
        public boolean ballOut;


        public Ball(){
            position = new Vector3(400,240, 0);
            velocity = new Vector3(250,0,0);
            ball = new TextureRegion(new Texture("Beach-Ball-PNG.png"));
        }
        public void update(float dt){
            //bounce of top and bottom
            if (position.y < 20) {
                position.add(0,3,0);
                velocity.add(0, -2*velocity.y,0);
            }
            else if (position.y > 385){
                position.add(0,-3,0);
                velocity.add(0, -2*velocity.y,0);
            }
            position.add(velocity.x * dt, velocity.y*dt, 0);
        }

        public Vector3 getPosition() {
            return position;
        }

        public void addPosition(Vector3 vec){
            position.add(vec);
        }

        public void addVelocity(Vector3 vec){
            this.velocity.add(vec);
        }
        public TextureRegion getTexture() {
            return ball;
        }

        public Vector3 getVelocity(){
            return velocity;
        }
        public void setPosition(){
            position = new Vector3(400,240, 0);
        }

}
