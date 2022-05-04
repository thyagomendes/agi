package br.com.agi.domain;

public class Item {

    private Long id;
    private int qnt;
    private float price;

    public Item(Long id, int qnt, float price) {
        this.id = id;
        this.qnt = qnt;
        this.price = price;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getQnt() { return qnt; }
    public void setQnt(int qnt) { this.qnt = qnt; }
    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }
}
