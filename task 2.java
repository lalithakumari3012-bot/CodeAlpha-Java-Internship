package com.codealpha;
import java.util.HashMap;
import java.util.Scanner;
class Stock {
    String symbol;
    double price;
    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}
class User {
    String name;
    double balance;
    HashMap<String, Integer> portfolio;
    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        portfolio = new HashMap<>();
    }
    public void buyStock(Stock stock, int quantity) {
        double cost = stock.price * quantity;
        if (balance >= cost) {
            balance -= cost;
            portfolio.put(stock.symbol,
                    portfolio.getOrDefault(stock.symbol, 0) + quantity);
            System.out.println("Stock Purchased Successfully!");
        } else {
            System.out.println("Insufficient Balance!");
        }
    }
    public void sellStock(Stock stock, int quantity) {
        int owned = portfolio.getOrDefault(stock.symbol, 0);
        if (owned >= quantity) {
            balance += stock.price * quantity;
            portfolio.put(stock.symbol, owned - quantity);
            System.out.println("Stock Sold Successfully!");
        } else {
            System.out.println("Not Enough Shares!");
        }
    }
    public void displayPortfolio() {
        System.out.println("\n===== PORTFOLIO =====");
        if (portfolio.isEmpty()) {
            System.out.println("No Stocks Owned");
        }
        for (String stock : portfolio.keySet()) {
            System.out.println(stock + " : "
                    + portfolio.get(stock) + " shares");
        }
        System.out.println("Balance: ₹" + balance);
    }
}
public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Stock> market = new HashMap<>();
        market.put("TCS", new Stock("TCS", 3500));
        market.put("INFY", new Stock("INFY", 1500));
        market.put("RELIANCE", new Stock("RELIANCE", 2800));
        User user = new User("Investor", 100000);
        int choice;
        do {
            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("\n===== MARKET DATA =====");
                    for (Stock stock : market.values()) {
                        System.out.println(stock.symbol
                                + " : ₹" + stock.price);
                    }
                    break;
                case 2:
                    System.out.print("Enter Stock Symbol: ");
                    String buySymbol = sc.next();
                    System.out.print("Enter Quantity: ");
                    int buyQty = sc.nextInt();
                    if (market.containsKey(buySymbol)) {
                        user.buyStock(
                                market.get(buySymbol),
                                buyQty);
                    } else {
                        System.out.println("Invalid Stock!");
                    }
                    break;
                case 3:
                    System.out.print("Enter Stock Symbol: ");
                    String sellSymbol = sc.next();
                    System.out.print("Enter Quantity: ");
                    int sellQty = sc.nextInt();
                    if (market.containsKey(sellSymbol)) {
                        user.sellStock(
                                market.get(sellSymbol),
                                sellQty);
                    } else {
                        System.out.println("Invalid Stock!");
                    }
                    break;
                case 4:
                    user.displayPortfolio();
                    break;
                case 5:
                    System.out.println("Thank You!");
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        } while (choice != 5);
        sc.close();
    }
}