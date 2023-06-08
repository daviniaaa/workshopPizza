package workshopPizza.service;

import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import workshopPizza.model.Order;
import workshopPizza.model.Pizza;

@Service
public class OrderService {
    public Order calculateCost(Order order) {
        double cost = 0;
        String p = order.getPizza().getPizza();
        switch(p) {
            // case "bella", "marinara", "spianatacalabrese":
            //     cost += 30;
            //     break;
            case "margherita":
                cost += 22;
                break;
            case "trioformaggio":
                cost += 25;
                break;
            default:
                cost += 30;
        }

        String s = order.getPizza().getSize();
        switch(s) {
            // case "small":
            //     cost *= 1;
            //     break;
            case "medium":
                cost *= 1.2;
                break;
            case "large":
                cost *= 1.5;
                break;
            default:
                cost *= 1;
        }

        cost *= order.getPizza().getQuantity();

        order.setPizzaCost(cost);

        if (order.getDelivery().isRush()) {
            cost += 2;
        }

        order.setTotalCost(cost);

        return order;
    }

    public JsonObject toJSON(Order order) {
        return Json.createObjectBuilder()
            .add("orderId", order.getId())
            .add("name", order.getDelivery().getName())
            .add("address", order.getDelivery().getAddress())
            .add("phone", order.getDelivery().getPhone())
            .add("rush", order.getDelivery().isRush())
            .add("comments", order.getDelivery().getComments())
            .add("pizza", order.getPizza().getPizza())
            .add("size", order.getPizza().getSize())
            .add("quantity", order.getPizza().getQuantity())
            .add("total", order.getTotalCost())
            .build();
    }
}
