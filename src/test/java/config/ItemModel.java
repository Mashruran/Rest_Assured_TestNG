package config;

public class ItemModel {
    private String amount;
    private String itemName;
    private String month;
    private String purchaseDate;
    private int quantity;
    private String remarks;

    public String getAmount() {
        return amount;
    }

    public String getItemName() {
        return itemName;
    }

    public String getMonth() {
        return month;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public ItemModel(){

    }
}
