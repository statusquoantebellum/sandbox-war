package com.example.color;

import org.junit.Assert;
import org.junit.Test;

public class ColorTest {

    @Test
    public void parse_color_ok() {
        final Color parsed = Color.parse("#c0FF12");
        Assert.assertEquals(0xc0, parsed.getRed());
        Assert.assertEquals(0xff, parsed.getGreen());
        Assert.assertEquals(0x12, parsed.getBlue());
    }

    @Test
    public void color_distance_ok() {
        final Color black = new Color(0,0,0) ;
        final Color gray  = new Color(127,127,127) ;
        final Color white = new Color(254,254,254) ;
        final double blackGrayDistance = black.distance( gray ) ;
        final double grayWhiteDistance = gray.distance( white ) ;
        Assert.assertTrue( blackGrayDistance == grayWhiteDistance );
    }
}
