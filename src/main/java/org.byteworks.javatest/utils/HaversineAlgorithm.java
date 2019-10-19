package org.byteworks.javatest;
/**
* Java implementation of the the Haversine Algorithm
* The Haversine Algorithm is used to calculate the distance between two GPS coordinates.
* It is used in this app to calculate the distance between the vendor kitchen and office location.
* For extensibility purpose, if the vendor starts providing home delivery option, it can be used to
* calculate the distance between the vendor kitchen and developer home and subsequently the logistic fee.
* @author  Enokela Acheme Paul
* @email    achemepaulenokela@gmail.com
* @version 1.0
*/

public class HaversineAlgorithm {

    static final double _eQuatorialEarthRadius = 6378.1370D;
    static final double _d2r = (Math.PI / 180D);

    //get distance in meter
    public static double distanceInM(double lat1, double long1, double lat2, double long2) {
        return (double) (1000D * distantanceInKm(lat1, long1, lat2, long2));
    }

    //get distance in Km
    public static double distantanceInKm(double lat1, double long1, double lat2, double long2) {
        double dlong = (long2 - long1) * _d2r;
        double dlat = (lat2 - lat1) * _d2r;
        double a = Math.pow(Math.sin(dlat / 2D), 2D) + Math.cos(lat1 * _d2r) * Math.cos(lat2 * _d2r)
                * Math.pow(Math.sin(dlong / 2D), 2D);
        double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
        double d = _eQuatorialEarthRadius * c;

        return d;
    }

}