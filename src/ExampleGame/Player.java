/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExampleGame;

import GameEngine.Coordinate;
import GameEngine.Field;
import GameEngine.GameObject;
import GameEngine.ImpossibleLocationAddException;
import GameEngine.ImpossibleLocationRemoveException;
import GameEngine.LogLevel;
import GameEngine.MoveType;
import GameEngine.ObjectCollidesException;
import GameEngine.OutOfBoundsException;
import alvaro_tools.MathCustomFuncs;
import GameEngine.OutOfBoundsMoveType;
import GameEngine.Speed;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A player for the example game
 *
 * @author alvaro9650
 */
public class Player extends GameObject {

    Integer move_points;
    Integer points;

    /**
     * Creates a player for the example game
     *
     * @param character the character that represents the player
     * @param field The field where the player is located
     * @author alvaro9650
     */
    public Player(char character, Field field) {
        super(field);
        this.objecttype = "Player";
        this.speed = new Speed(0, 0);
        this.height = 1;
        this.character = character;
        this.outofboundsmovetype = OutOfBoundsMoveType.Farest;
        this.move_points = 10;
        this.points = 0;
        this.movetype = MoveType.Teleport;
        this.loglevel = LogLevel.Verbose;
        do {
            this.location = new Coordinate(MathCustomFuncs.random(0, playingfield.size.x - 1).intValue(), MathCustomFuncs.random(0, playingfield.size.y - 1).intValue());
            try {
                this.playingfield.addGameObject(this);
            } catch (ImpossibleLocationAddException ex) {
                Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
                continue;
            } catch (ObjectCollidesException ex) {
                System.out.println("player collide");
            } catch (OutOfBoundsException ex) {
                System.out.println("player out of bounds");
            }
            break;
        } while (true);
    }

    /**
     * Moves the player and calculates move points
     *
     * @param speed The speed for this turn
     * @throws GameEngine.ImpossibleLocationRemoveException
     * @throws GameEngine.ImpossibleLocationAddException
     * @author alvaro9650
     */
    public void move(Speed speed) throws ImpossibleLocationRemoveException, ImpossibleLocationAddException {
        this.move_points -= Math.abs(speed.x);
        this.move_points -= Math.abs(speed.y);
        if (this.move_points < 0) {
            System.out.println("You want to move too fast so you wont move and you wont accumulate move points");
        } else {
            this.speed = speed;
            this.updateLocation();
            this.stop();
            this.move_points += 10;
        }
    }

    /**
     * Function to add custom actions when colliding against a determined object
     * or object type
     *
     * @param collisionreceiver The object that receives the collision
     * @author alvaro9650
     */
    @Override
    public void giveCollision(GameObject collisionreceiver) {
        super.giveCollision(collisionreceiver);
        switch (collisionreceiver.objectidentifier) {
            default:
                break;
        }
        switch (collisionreceiver.objecttype) {
            case "Ball":
                this.addPoint();
                break;
            default:
                break;
        }
    }
    /**
     * Adds a point to the player
     *
     * @author alvaro9650
     */
    public void addPoint(){
        this.points += 1;
    }
    @Override
    public void log() {
        super.log();
        StringBuilder object_log = new StringBuilder();
        switch (this.loglevel) {
            case Verbose:
                object_log.append("\nmove_points = ");
                object_log.append(this.move_points);
                object_log.append("\npoints = ");
                object_log.append(this.points);
            case DrawRelated:
            case MoveRelated:
            case OutOfBoundsRelated:
            case CollisionRelated:
            case PositionRelated:
            case None:
        }
    }
}