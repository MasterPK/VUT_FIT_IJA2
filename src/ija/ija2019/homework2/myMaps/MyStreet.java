package ija.ija2019.homework2.myMaps;

import ija.ija2019.homework2.maps.Coordinate;
import ija.ija2019.homework2.maps.Stop;
import ija.ija2019.homework2.maps.Street;

import java.util.ArrayList;
import java.util.List;

public class MyStreet implements Street {
    private String id;
    private List<Coordinate> coordinates;
    private List<Stop> stops;

    public MyStreet(String id, Coordinate[] coordinates) {
        this.id = id;
        this.coordinates = new ArrayList<>();
        for (Coordinate coordinate : coordinates) {
            this.coordinates.add(coordinate);
        }
        this.stops = new ArrayList<>();
    }

    /**
     * Vrátí identifikátor ulice.
     *
     * @return Identifikátor ulice.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Vrátí seznam souřadnic definujících ulici. První v seznamu je vždy počátek a poslední v seznamu konec ulice.
     *
     * @return Seznam souřadnic ulice.
     */

    public List<Coordinate> getCoordinates() {
        return this.coordinates;
    }

    /**
     * Vrátí seznam zastávek na ulici.
     *
     * @return Seznam zastávek na ulici. Pokud ulize nemá žádnou zastávku, je seznam prázdný.
     */
    public List<Stop> getStops() {
        return this.stops;
    }

    /**
     * Přidá do seznamu zastávek novou zastávku.
     *
     * @param stop Nově přidávaná zastávka.
     * @return
     */
    public boolean addStop(Stop stop) {
        if (stop == null)
            return false;

        if (this.coordinates.size() <= 1)
            return false;

        for (int i = 0; i < this.coordinates.size() - 1; i++) {
            int x1 = this.coordinates.get(i).getX();
            int y1 = this.coordinates.get(i).getY();

            int x2 = this.coordinates.get(i + 1).getX();
            int y2 = this.coordinates.get(i + 1).getY();


            if (x1 == x2 && x1==stop.getCoordinate().getX()) {
                if (y1 < y2) {
                    if (stop.getCoordinate().getY() >= y1 && stop.getCoordinate().getY() <= y2) {
                        this.stops.add(stop);
                        stop.setStreet(this);
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if (stop.getCoordinate().getY() <= y1 && stop.getCoordinate().getY() >= y2) {
                        this.stops.add(stop);
                        stop.setStreet(this);
                        return true;
                    } else {
                        return false;
                    }
                }
            }

            if (y1 == y2 && y1==stop.getCoordinate().getY()) {
                if (x1 < x2) {
                    if (stop.getCoordinate().getX() >= x1 && stop.getCoordinate().getX() <= x2) {
                        this.stops.add(stop);
                        stop.setStreet(this);
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if (stop.getCoordinate().getX() <= x1 && stop.getCoordinate().getX() >= x2) {
                        this.stops.add(stop);
                        stop.setStreet(this);
                        return true;
                    } else {
                        return false;
                    }
                }

            }

            /*int diffX1 = coordinates.get(i).diffX(coordinates.get(i + 1));
            int diffY1 = coordinates.get(i).diffY(coordinates.get(i + 1));

            //y=ax+b
            //soustava dvou rovnic o dvou neznamych
            //y1=x1*a+b;
            //y2=x2*a+b;

            //y2=x2*a+b; *(-1)
            y2 *= -1;
            x2 *= -1;

            //x2-x1
            int x2_x1 = x2 - x1;

            //y2-y1
            int y2_y1 = y2 - y1;

            double a = (double) y2_y1 / x2_x1;
            double b = a*x1 + y1;

            //dosazeni bodu na kontrolu

            int x = stop.getCoordinate().getX();
            int y = stop.getCoordinate().getY();
            if(y == ((a * x) + b))
            {

                return true;
            }*/

        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Street street = (Street) obj;
        if (this.id.equals(street.getId())) {

            if (!this.coordinates.equals(street.getCoordinates()))
                return false;


            if (!this.stops.equals(street.getStops()))
                return false;


            return true;
        }
        return false;
    }

    @Override
    public Coordinate begin() {
        return this.coordinates.get(0);
    }

    @Override
    public Coordinate end() {
        return this.coordinates.get(this.coordinates.size() - 1);
    }

    @Override
    public boolean follows(Street s) {
        List<Coordinate> coordinatesA = this.coordinates;
        List<Coordinate> coordinatesB = s.getCoordinates();

        for (Coordinate coordinateA : coordinatesA) {
            for (Coordinate coordinateB : coordinatesB) {
                if (coordinateA.equals(coordinateB))
                    return true;
            }
        }
        return false;
    }


}
