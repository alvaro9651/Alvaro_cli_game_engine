/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_engine;

/**
 *
 * @author alumno1718_2
 */
public class ObjectCollidesException extends ImpossibleRelocationException {

    public ObjectCollidesException() {
        super();
    }

    public ObjectCollidesException(String exceptiontext) {
        super(exceptiontext);
    }
}
