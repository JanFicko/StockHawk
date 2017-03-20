package com.udacity.stockhawk.data;

public class Stock {

    private String symbolName;
    private String stockPrice;
    private String stockChange;

    public Stock(String symbolName, String stockPrice, String stockChange) {
        this.symbolName = symbolName;
        this.stockPrice = stockPrice;
        this.stockChange = stockChange;
    }


    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public String getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(String stockPrice) {
        this.stockPrice = stockPrice;
    }

    public String getStockChange() {
        return stockChange;
    }

    public void setStockChange(String stockChange) {
        this.stockChange = stockChange;
    }
}
