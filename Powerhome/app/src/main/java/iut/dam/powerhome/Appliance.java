package iut.dam.powerhome;

public class Appliance {
    int id;
    String name;
    String reference;
    int wattage;
    int icone;
    public Appliance(int id, String name, String reference, int wattage) {
        this.id = id;
        this.name = name;
        this.reference = reference;
        this.wattage = wattage;

        this.icone = getIcone(name);
    }

    private int getIcone(String name) {
        String nameLower = name.toLowerCase();

        if (nameLower.contains("lave") || nameLower.contains("laver")) {
            return R.drawable.ic_machine_a_laver;
        } else if (nameLower.contains("aspirateur")) {
            return R.drawable.ic_aspirateur;
        } else if (nameLower.contains("fer") || nameLower.contains("repasser")) {
            return R.drawable.ic_fer_a_repasser;
        } else if (nameLower.contains("clim") || nameLower.contains("air")) {
            return R.drawable.ic_climatiseur;
        } else {
            return R.drawable.ic_launcher_foreground;
        }
    }
}