package com.system.fridges.models.transferObjects.orderObjects;

import com.system.fridges.models.entities.AutoOrder;

import java.util.ArrayList;
import java.util.List;

public class AutoOrderRequest {

    public String access_token;

    public String phone;

    public String id;

    public String first_name;

    public String email;

    public String address;

    public String delivery_date;

    public List<ProductRequest> products;

    public AutoOrderRequest(AutoOrder autoOrder) {
        String dayOfMonth = changeFormOfDay(autoOrder.getDateDelivery().getDayOfMonth());

        this.phone = autoOrder.getAccess().getUser().getPhoneNumber().replaceAll("[^0-9]", "");
        this.id = Integer.toString(autoOrder.getOrderId());
        this.first_name = autoOrder.getAccess().getUser().getSurname();
        this.email = autoOrder.getAccess().getUser().getEmail();
        this.address = autoOrder.getAccess().getFridge().getOffice().toString();
        this.delivery_date = dayOfMonth + "-" + autoOrder.getDateDelivery().getMonthValue() + "-" + autoOrder.getDateDelivery().getYear();
        this.products = convertToProductRequest(autoOrder);
    }

    private String changeFormOfDay(int dayOfMonth) {
        String changedDayOfMonth;
        if (dayOfMonth < 10) {
            changedDayOfMonth = "0" + dayOfMonth;
        } else {
            changedDayOfMonth = String.valueOf(dayOfMonth);
        }
        return changedDayOfMonth;
    }

    private List<ProductRequest> convertToProductRequest(AutoOrder autoOrder) {
        List<ProductRequest> productRequests = new ArrayList<>();
        ProductRequest productRequest = new ProductRequest();
        productRequest.count = autoOrder.getNumber();
        productRequests.add(productRequest);
        return productRequests;
    }

    static class ProductRequest {
        public int api_id = 6350;

        public int count;

        public int price = 15;
    }
}




