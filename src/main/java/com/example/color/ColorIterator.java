package com.example.color;

import java.util.Iterator;

public class ColorIterator implements Iterator<Integer>, Iterable<Integer> {
    private final int n ;
    private final double step ;
    private transient int position ;

    public ColorIterator( final int n ) {
        this.n = n ;
        this.step = 256.0 / ( n - 1 );
        this.position = 0 ;
    }

    public Iterator<Integer> iterator() {
        return this;
    }

    public boolean hasNext() {
        return position < n ;
    }

    public Integer next() {
        final int value = (int) ( step * ( position++ )) ;
        return value > 255 ? 255 : value ;
    }
}

