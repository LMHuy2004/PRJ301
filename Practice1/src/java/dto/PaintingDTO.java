package dto;

public class PaintingDTO {
    private String id, creator, description;
    private int height, width;
    private double price;
    private boolean status;

    public PaintingDTO() {
    }

    public PaintingDTO(String id, String creator, String description, int height, int width, double price, boolean status) {
        this.id = id;
        this.creator = creator;
        this.description = description;
        this.height = height;
        this.width = width;
        this.price = price;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}

