package com.mevo.app.data.model;

import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

public class City {
    @Attribute(name = "alias", required = false)
    public String alias;
    @Attribute(name = "bounds", required = false)
    public String bounds;
    @Attribute(name = "uid", required = false)
    public int id;
    @Attribute(name = "break", required = false)
    public int isBroken;
    @Attribute(name = "lat", required = false)
    public double lat;
    @Attribute(name = "lng", required = false)
    public double lng;
    @Attribute(name = "maps_icon", required = false)
    public String mapsIcon;
    @Attribute(name = "name", required = false)
    public String name;
    @ElementList(inline = true, name = "place", required = false)
    public List<Place> places;
    @Attribute(name = "num_places", required = false)
    public int placesNum;
    @Attribute(name = "refresh_rate", required = false)
    public int refreshRate;
    @Attribute(name = "zoom", required = false)
    public int zoom;
}
