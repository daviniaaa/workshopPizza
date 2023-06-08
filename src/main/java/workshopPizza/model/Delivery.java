package workshopPizza.model;

public class Delivery {
    private String name;
    private String address;
    private long phone;
    private boolean isRush;
    private String comments;
    
    public Delivery() {
    }

    public Delivery(String name, String address, long phone, boolean isRush, String comments) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isRush = isRush;
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public boolean isRush() {
        return isRush;
    }

    public void setRush(boolean isRush) {
        this.isRush = isRush;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    
}
