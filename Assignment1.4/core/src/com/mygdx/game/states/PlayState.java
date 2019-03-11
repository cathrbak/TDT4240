package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.MyHeliGame;

import com.mygdx.game.sprites.Ball;
import com.mygdx.game.sprites.Paddle;

import java.util.ArrayList;
import java.util.Random;

public class PlayState extends State {
    private Paddle paddle1;
    private Paddle paddle2;
    private Ball ball;
    private Texture bg;
    BitmapFont font = new BitmapFont();
    Random rand = new Random();
    public int score1;
    public int score2;
    private Texture topWall;
    private Texture midWall;
    private Texture bottomWall;
    private Texture up, down;
    private Rectangle upR, downR, p1,p2,ballR;
    private Vector3 ballBounce;
    public String winner;

    private static Vector3 STARTPOSP1 = new Vector3(14,(MyHeliGame.HEIGHT/2) -  30, 0 );
    private static Vector3 STARTPOSP2 = new Vector3(774, (MyHeliGame.HEIGHT/2) - 30, 0);
    public Vector3 right = new Vector3(10,0,0);
    public Vector3 left = new Vector3(-10,0,0);


    public PlayState(GameStateManager gsm) {
        super(gsm);
        paddle1 = new Paddle(STARTPOSP1.x, STARTPOSP1.y);
        paddle2 = new Paddle(STARTPOSP2.x, STARTPOSP2.y);
        ball = new Ball();
        //her setter man størrelsen på hvor mye man vil se av skjermen
        cam.setToOrtho(false, MyHeliGame.WIDTH, MyHeliGame.HEIGHT);
        bg = new Texture("background2.png");
        topWall = new Texture("GameWall.png");
        bottomWall = new Texture("GameWall.png");
        midWall = new Texture("midWall.png");
        up = new Texture("up.png");
        down = new Texture("down.png");
        upR = new Rectangle(80, 115,50,50);
        downR = new Rectangle(80,45,50,50);
        p1 = new Rectangle(paddle1.getPosition().x,paddle1.getPosition().y,20, 80);
        p2 = new Rectangle(paddle2.getPosition().x,paddle2.getPosition().y,20, 80);
        ballR = new Rectangle(ball.getPosition().x, ball.getPosition().y, 35, 35);
        this.score2 = 0;
        this.score1 = 0;
        this.winner = " ";
    }

    @Override
    protected void handleInput() {
        Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
        cam.unproject(tmp);
        if(upR.contains(tmp.x, tmp.y)){
            this.paddle1.moveUp();
        }
        if(downR.contains(tmp.x, tmp.y)){
            this.paddle1.moveDown();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        collision();
        //dt += Gdx.graphics.getDeltaTime();
        paddle1.update(dt);
        paddle2.update(dt);
        paddle2.follow(ball.getPosition());
        ball.update(dt);
        p1.setPosition(paddle1.getPosition().x, paddle1.getPosition().y);
        p2.setPosition(paddle2.getPosition().x, paddle2.getPosition().y);
        ballR.setPosition(ball.getPosition().x, ball.getPosition().y);
        ballOut();
    }

    private void ballOut(){
        if(score1 < 21 && score2 < 21) {
            if (ball.getPosition().x <= 20) {
                score2 += 1;
                ball.setPosition();
            }
            if (ball.getPosition().x >= 780) {
                score1 += 1;
                ball.setPosition();
            }
        }
        if(score2 == 21){
            this.winner = "Player 2";
            dispose();
            gsm.set(new EndState(this.gsm, this.winner));

        }
        if(score1 == 21){
            this.winner = "Player 2";

        }
    }
    private void collision() {
            if (p1.overlaps(ballR)) {
                ball.addPosition(right);
                ballBounce = new Vector3(-2 * ball.getVelocity().x + randomInt(), -2 * ball.getVelocity().y + randomInt(), 0);
                ball.addVelocity(ballBounce);
            }
            if (p2.overlaps(ballR)) {
                ball.addPosition(left);
                ballBounce = new Vector3(-2 * ball.getVelocity().x + randomInt(), -2 * ball.getVelocity().y + randomInt(), 0);
                ball.addVelocity(ballBounce);
            }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0, MyHeliGame.WIDTH, MyHeliGame.HEIGHT);
        sb.draw(paddle1.getTexture(), paddle1.getPosition().x, paddle1.getPosition().y, 20, 80);
        sb.draw(paddle2.getTexture(), paddle2.getPosition().x, paddle2.getPosition().y, 20,80);
        sb.draw(ball.getTexture(), ball.getPosition().x, ball.getPosition().y, 35, 35);
        sb.draw(topWall, 20,420,100, 750, 760, 5);
        sb.draw(bottomWall, 20, 20, 100, 750, 760, 5);
        sb.draw(midWall, 410, 20, 100, 350, 2, 400);
        font.draw(sb, "Player 1: " + String.valueOf(score1), 20, 450 );
        font.setColor(Color.PINK);
        font.draw(sb, "Player 2: " + String.valueOf(score2), 720, 450);
        sb.draw(down, 80, 45, 50, 50);
        sb.draw(up, 80, 100, 50, 50);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bottomWall.dispose();
        topWall.dispose();
        midWall.dispose();
        up.dispose();
        down.dispose();
        font.dispose();
        paddle1.getTexture().getTexture().dispose();
        paddle2.getTexture().getTexture().dispose();
        ball.getTexture().getTexture().dispose();

    }


    private int randomInt(){
        return (int) (Math.random() *100 + 1);

    }










}
