public class MenuItem {
    private String name;
    private double price;
    private String type; // Type of food (e.g., Protein, Beans, Dairy, etc.)

    public MenuItem(String name, double price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }
}
