package cn.caohangwei.cimit.exception;

/**
 * throw overflow in the bucket exception.
 *
 * @author PinuoC
 */
public class OutOfBucketException extends RuntimeException{
    public OutOfBucketException(String s){super(s);}
}
