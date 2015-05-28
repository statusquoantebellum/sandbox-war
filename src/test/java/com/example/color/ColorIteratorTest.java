package com.example.color;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ColorIteratorTest {
    private int n ;
    private ColorIterator colorIterator ;

    @Before
    public void setUp() {
        n = 10 ;
        colorIterator = new ColorIterator( n ) ;
    }

    @Test
    public void iterates_n_times() {
        int count = 0 ;
        while( colorIterator.hasNext()){
            colorIterator.next() ;
           count++ ;
        }
        Assert.assertEquals(n, count);
    }

    @Test
    public void iteration_ends_with_255() {
        int color = 0 ;
        for( int i : colorIterator ) {
            color = i ;
        }
        Assert.assertEquals( 255, color );
    }
}
