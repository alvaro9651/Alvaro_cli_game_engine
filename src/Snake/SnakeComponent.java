/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import GameEngine.Composite2dGameObjectComponent;

/**
 *
 * @author alvaro9650
 */
public class SnakeComponent extends Composite2dGameObjectComponent {

    public SnakePartType part;

    public SnakeComponent(Snake parent) {
        super(parent);
    }
}
