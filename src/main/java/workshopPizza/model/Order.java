package workshopPizza.model;

import java.util.UUID;

public class Order {
    private Pizza pizza;
    private Delivery delivery;
    private String id;
    private double totalCost;
    private double pizzaCost;
    
    public Order() {
    }
    public Order(Pizza pizza, Delivery delivery) {
        this.pizza = pizza;
        this.delivery = delivery;
        this.id = generateId(8);
    }
    public Pizza getPizza() {
        return pizza;
    }
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
    public Delivery getDelivery() {
        return delivery;
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public double getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    public double getPizzaCost() {
        return pizzaCost;
    }
    public void setPizzaCost(double pizzaCost) {
        this.pizzaCost = pizzaCost;
    }

    
    public String generateId(int length) {
        return UUID.randomUUID().toString().substring(0, length);
    }

    
}
