package com.mute.winter.winterkit.utility.helper;

/**
 * @author dkoller
 * @since 13.03.2017
 *
 * Image Placeholder/Mockup
 */
public class LoremPizza {

    /**
     * Oh, Magie
     * Oh, Pizza so fein
     *
     * gib Höhe und Breite
     * der Pizza ein
     *
     * @return Image of Pizza with given width and height
     */
    public static String getPizza(int width, int height){
        String url = "http://lorempizza.com/%1$s/%2$s";
        return String.format(url, width, height);
    }

    public static String getPizzaLarge(){
        return getPizza(500, 500);
    }

    public static String getPizzaSmall(){
        return getPizza(300, 300);
    }
}
