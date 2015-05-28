package com.example.color;

import com.opencsv.CSVReader;

import java.io.*;
import java.util.*;

public class ColorTable {
    private final Map< Color, Collection< String >> colorNames ;

    public ColorTable( final File file ) throws Exception {
        this( new FileInputStream( file )) ;
    }

    public ColorTable( final InputStream stream ) throws Exception {
        this( new InputStreamReader( stream )) ;
    }

    public ColorTable( final Reader data ) throws Exception {
        colorNames = new HashMap<>() ;
        final CSVReader csvReader = new CSVReader( data ) ;
        String[] nextLine ;
        while(( nextLine = csvReader.readNext()) != null ) {
            final String name = nextLine[0] ;
            final Color color = Color.parse( nextLine[1] ) ;
            Collection<String> existing = colorNames.putIfAbsent(color, new LinkedList<>(Collections.singleton(name))) ;
            if( existing != null ) {
                existing.add( name ) ;
            }
        }
    }

    public List<Color> getClosest( final Color sample ) {
        List<Color> found ;
        int layer = 0 ;
        do {
            found = peelLayer( sample, layer++ ) ;
        } while( found.isEmpty() && layer < 256 ) ;

        return found ;
    }

    private List<Color> peelLayer( final Color centre, final int size ) {
        final List<Color> found = new ArrayList<>() ;
        final int redCentre   = centre.getRed() ;
        final int greenCentre = centre.getGreen() ;
        final int blueCentre  = centre.getBlue() ;
        final int redMax   = normalize( redCentre + size ) ;
        final int redMin   = normalize( redCentre - size ) ;
        final int greenMax = normalize( greenCentre + size ) ;
        final int greenMin = normalize( greenCentre - size ) ;
        final int blueMax  = normalize( blueCentre + size ) ;
        final int blueMin  = normalize( blueCentre - size ) ;
        for( int red = redMin; red <= redMax; red++) {
            for( int green = greenMin; green <= greenMax ; green++ ) {
                for( int blue = blueMin; blue <= blueMax ; blue ++) {
                    if( red == redMax || red == redMin || blue == blueMax || blue == blueMin || green == greenMax || green == greenMin ) {
                        final Color cell = new Color(red, green, blue);
                        if (colorNames.containsKey(cell)) found.add(cell);
                    }
                }
            }
        }
        return found ;
    }

    private int normalize( int component ) {
        component = component < 0 ? 0 : component ;
        component = component > 255 ? 255 : component ;
        return  component ;
    }
}
