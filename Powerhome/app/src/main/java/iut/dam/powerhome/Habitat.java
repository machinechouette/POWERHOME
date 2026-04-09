package iut.dam.powerhome;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Habitat {
    int id;
    String resident;
    int floor;
    double area;
    List<Appliance> appliances;
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

    public List<Appliance> getAppliances() {
        return appliances;
    }


    public static Habitat getFromJson(String json) {
        Gson gson = new Gson();
        Habitat obj = gson.fromJson(json, Habitat.class);
        return obj;
    }
    public static List<Habitat> getList(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Habitat>>(){}.getType();
        return gson.fromJson(json, type);
    }

}