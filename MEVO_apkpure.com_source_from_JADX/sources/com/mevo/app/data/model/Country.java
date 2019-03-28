package com.mevo.app.data.model;

import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "country")
public class Country {
    @ElementList(inline = true, name = "city", required = false)
    public List<City> cities;
    @Attribute(name = "country", required = false)
    public String country;
    @Attribute(name = "country_name", required = false)
    public String countryName;
    @Attribute(name = "show_bike_type_groups", required = false)
    public int doesShowBikeTypeGroups;
    @Attribute(name = "show_bike_types", required = false)
    public int doesShowBikeTypes;
    @Attribute(name = "show_free_racks", required = false)
    public int doesShowFreeRacks;
    @Attribute(name = "show_racks_amount", required = false)
    public int doesShowRacksAmount;
    @Attribute(name = "domain", required = false)
    public String domain;
    @Attribute(name = "hotline", required = false)
    public String hotline;
    @Attribute(name = "uid", required = false)
    public double id;
    @Attribute(name = "lat", required = false)
    public double lat;
    @Attribute(name = "lng", required = false)
    public double lng;
    @Attribute(name = "name", required = false)
    public String name;
    @Attribute(name = "policy", required = false)
    public String policyUrl;
    @Attribute(name = "terms", required = false)
    public String termsUrl;
    @Attribute(name = "website", required = false)
    public String websiteUrl;
    @Attribute(name = "zoom", required = false)
    public int zoom;
}
