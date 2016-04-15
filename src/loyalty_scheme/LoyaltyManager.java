/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loyalty_scheme;

import domain.Database;
import domain.Observer;
import domain.Order;

/**
 *
 * @author Admin
 */
public class LoyaltyManager implements Observer{
    
    public LoyaltyManager() {
        LoyaltyScheme testAccount = new LoyaltyScheme();
        Database db = new Database();
        db.persist(testAccount);
    }
   public static void main(String[] args) {
       LoyaltyManager test = new LoyaltyManager();
   }
   

    @Override
    public void update(Order order) {
        Database db = new Database();
        LoyaltyScheme loyaltyAccount = db.retrieveLoyaltyAccount(order.getOrderOwner().getLoyaltyAccount());
        int points = loyaltyAccount.getLoyaltyPoints() + order.getTotalPoints();
        db.updateLoyaltyPoints(loyaltyAccount.getAccountNumber(), points);       
    }
    
    public int getLoyaltyPoints(String accountNo) {
        Database db = new Database();
        LoyaltyScheme loyaltyAccount = db.retrieveLoyaltyAccount(accountNo);
        return loyaltyAccount.getLoyaltyPoints();
    }
}
