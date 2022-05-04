package br.com.agi.domain;

import java.util.List;

public class Sales {

    private String salesId;
    private String salesManName;
    private List<Item> items;

    public Sales(String salesId, String salesManName, List<Item> items) {
        this.salesId = salesId;
        this.salesManName = salesManName;
        this.items = items;
    }

    public String getSalesId() { return salesId; }
    public void setSalesId(String salesId) { this.salesId = salesId; }
    public String getSalesManName() { return salesManName; }
    public void setSalesManName(String salesManName) { this.salesManName = salesManName; }
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }

    @Override
    public String toString() {
        return "Sales{" +
                "salesId='" + salesId + '\'' +
                ", salesManName='" + salesManName + '\'' +
                ", items=" + items +
                '}';
    }
}
