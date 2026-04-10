package iut.dam.powerhome;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Habitat {
    private int id;
    private String resident;
    private int floor;
    private double area;
    private List<Appliance> appliances;
    public Habitat(int id, String residentName, int floor, double area) {
        this.id = id;
        this.resident = residentName;
        this.floor = floor;
        this.area = area;
        this.appliances = new ArrayList<>();
    }

    public void addAppliance(Appliance appliance) {
        this.appliances.add(appliance);
    }

    public static List<Habitat> getList(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Habitat>>(){}.getType();
        return gson.fromJson(json, type);
    }

    public int getId() {return id;}
    public String getName(){return resident;}
    public int getFloor() {return floor;}
    public double getArea() {return area;}
    public List<Appliance> getAppliances() {
        return appliances;
    }

}