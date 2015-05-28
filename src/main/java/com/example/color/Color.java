package com.example.color;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Color {
    private static final Pattern colorPattern = Pattern.compile("#(\\p{XDigit}\\p{XDigit})(\\p{XDigit}\\p{XDigit})(\\p{XDigit}\\p{XDigit})") ;

    private final char red ;
    private final char green ;
    private final char blue ;

    public Color( final int red, final int green, final int blue ) {
        if( ! validate( red, green, blue )) {
            throw new IllegalArgumentException( String.format( "(%s,%s,%s) components should be in the range 0..255", red, green, blue )) ;
        }
        this.red = (char) red ;
        this.green = (char) green ;
        this.blue = (char) blue ;
    }

    public static Color parse( final String colorString ) {
        final Matcher matcher = colorPattern.matcher( colorString ) ;
        if( matcher.matches()) {
            final MatchResult matchResult = matcher.toMatchResult() ;
            final int red   = Integer.parseInt(matchResult.group(1), 16) ;
            final int green = Integer.parseInt( matchResult.group(2), 16 ) ;
            final int blue  = Integer.parseInt( matchResult.group(3), 16 ) ;
            return new Color( red, green, blue ) ;
        } else {
            throw new IllegalArgumentException( String.format("(%s) - invalid color string format. Expecting #xxxxxx", colorString )) ;
        }
    }

    public int getRed() {
        return  red ;
    }

    public int getGreen() {
        return green ;
    }

    public int getBlue() {
        return blue ;
    }

    public String format() {
        return String.format("#%02x%02x%02x", (int)red, (int)green, (int)blue ) ;
    }

    public double distance( final Color other ) {
        return Math.sqrt( squareDistance( other )) ;
    }

    public int squareDistance( final Color other ) {
        final int redDistance = this.red - other.red ;
        final int greenDistance = this.green - other.green ;
        final int blueDistance = this.blue - other.blue ;
        return redDistance * redDistance + greenDistance * greenDistance + blueDistance * blueDistance ;
    }

    private boolean validate( final int... components ) {
        for(  final int component : components ) {
            if( component < 0 || component > 255 ) {
                return false ;
            }
        }
        return true ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Color color = (Color) o;

        if (getRed() != color.getRed()) return false;
        if (getGreen() != color.getGreen()) return false;
        return getBlue() == color.getBlue();

    }

    @Override
    public int hashCode() {
        int result = getRed();
        result = 31 * result + getGreen();
        result = 31 * result + getBlue();
        return result;
    }
}
