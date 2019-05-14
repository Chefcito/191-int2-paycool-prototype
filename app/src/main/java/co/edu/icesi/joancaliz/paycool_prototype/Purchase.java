package co.edu.icesi.joancaliz.paycool_prototype;

public class Purchase {
    private int price1, price2, price3, discount, subtotal, total;
    private String code;

    public Purchase() {

    }

    public Purchase(int price1, int price2, int price3, int discount, String code) {
        this.price1 = price1;
        this.price2 = price2;
        this.price3 = price3;
        this.discount = discount;
        this.code = code;

        subtotal = price1 + price2 + price3;
        total = subtotal - discount;
    }

    public int getPrice1() {
        return price1;
    }

    public void setPrice1(int price1) {
        this.price1 = price1;
    }

    public int getPrice2() {
        return price2;
    }

    public void setPrice2(int price2) {
        this.price2 = price2;
    }

    public int getPrice3() {
        return price3;
    }

    public void setPrice3(int price3) {
        this.price3 = price3;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
