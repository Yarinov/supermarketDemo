package com.yarinov.supermarket;

public class Item {

    private String uuid, name, price;

    public Item(String uuid, String name, String price) {
        this.uuid = uuid;
        this.name = name;
        this.price = price;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
