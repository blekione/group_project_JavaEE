package loyalty_scheme;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LoyaltyScheme {
    
    @Id
    String accountNumber;
    int loyaltyPoints;
    
    public LoyaltyScheme() {
        
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
}
