package org.academiadecodigo.hackathon.foxtrot.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.academiadecodigo.hackathon.foxtrot.GameMap;

public abstract class Entity {

    protected Vector2 pos;
    protected EntityType type;
    protected float velocityY = 0;
    protected GameMap map;
    protected boolean grounded = false;
    protected long timeSinceLastMove;
    protected boolean isDead = false;

    public Entity(float x, float y, EntityType type, GameMap map) {

        this.pos = new Vector2(x, y);
        this.type = type;
        this.map = map;
    }
    public void update(float deltaTime, float gravity) {

        float newY = pos.y;

        this.velocityY += gravity * deltaTime * getWeight();
        newY += this.velocityY * deltaTime;

        checkCollision(newY);
    }

    protected void checkCollision(float newY) {

        if (map.doesRectCollideWithMap(pos.x, newY, getWidth(), getHeight(), this instanceof Coffin)) {

            if (velocityY < 0) {

                this.pos.y = (float) Math.floor(pos.y);
                grounded = true;
            }

            this.velocityY = 0;

        } else {

            this.pos.y = newY;
            grounded = false;
        }
    }

    public abstract void render(SpriteBatch batch);

    protected abstract void move(float deltaTime);

    protected void moveX(float amount) {

        float newX = pos.x + amount;

        if (!map.doesRectCollideWithMap(newX, pos.y, getWidth(), getHeight(), this instanceof Coffin)) {
            this.pos.x = newX;
        }
    }

    public Vector2 getPos() {
        return pos;
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }


    public EntityType getType() {
        return type;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public boolean isDead() {
        return isDead;
    }

    public int getWidth() {
        return type.getWidth();
    }

    public int getHeight() {
        return type.getHeight();
    }

    public float getWeight() {
        return type.getWeight();
    }

    public float setX(float x) {
        return pos.x = x;
    }

    public float setY(float y) {
        return pos.y = y;
    }

}
