package model;

public class SkaterDetails {
    private String name;
    private String nation;
    private String type;

    public SkaterDetails(String type, String name, String nation) {
        this.type = type;
        this.name = name;
        this.nation = nation;
        EventLog.getInstance().logEvent(new Event("Added skater category: " + type + ", name: " + name
                + ", nationality: " + nation));
    }

    //getter

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getNation() {
        return nation;
    }
}
