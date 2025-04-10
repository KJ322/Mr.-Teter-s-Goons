import java.util.Scanner;

public class moneyConverter_noAI {
    String[] currencies = {
        "U.S Dollar", "Cape Verdean Escudo", "Iranian Rial", "Malawian Kwacha", "South African Rand", 
        "Israeli New Shekel", "Sierra Leonean Leones", "Iraqi Dinar", "Algerian Dinar", "Moroccan Dirham", 
        "Kenyan Shilling", "Saudi Riyal", "Sao Tomean Dobra", "Djinoutian Franc", "Lebanese Pound", "Zimbabwean Dollar", 
        "Qatari Riyal", "Bahraini Dinar", "Jordanian Dinar", "West African CFA Franc", "Kuwaiti Dinar", "Afghan Afghani", 
        "Burundian Franc", "Malagasy Ariary", "tunisian Dinar", "Eritrean Nafka", "Nigerian Naira", "Ghanaian Cedi", 
        "Libyan Dinar","Mauritanian Ouguiya", "Syrian Pound", "United Arab Emirates Dirham", "Egyptian Pound", "Central African CFA Franc",
        "Namibian Dollar", "Liberian Dollar", "Botswana Pula", "Comorian Franc", "Seychellios Rupee", "Swazi Lilangeni", 
        "Mauritian Rupee", "Ethiopian Birr", "Omani Rial", "Angolan Kwanza", "Congolese Franc", "Gambian Dalasi", "Guinean Franc",
        "Lesotho Loti", "Mozambican Metical", "Rwandan Franc", "Somali Shilling", "South Sudanese Pound", "Sudanezse Pound", "Tanzanian Shilling", 
        "Ugandan Shilling", "Zambian Kwacha", "Yemeni Rial"
    };
    
    double[] rates = {
        1.0, 99.77, 42112.50, 1733.99, 18.76, 3.70, 20969.50, 1309.88, 132.83, 9.50, 129.25, 
        3.75, 22.64, 177.94, 89596.90, 361.90, 3.64, 0.376, 0.709, 594.94, 0.30, 71.60, 2965.90, 
        4642.89, 3.06, 15.00, 1537.39, 15.49, 4.82, 39.88, 13000.00, 3.67, 47.50, 603.00, 18.80, 
        46.00, 56.50, 0.385, 921.163, 2906.08, 64.75, 8676.76, 19.2786, 63.8874, 1410.25, 568.50, 
        130.26, 601.00, 2686.45, 3660.86, 21.00, 250.35
    };
    
    public double convert(double amount, String from, String to){
        int fromIndex = getCurrencyIndex(from);
        int toIndex = getCurrencyIndex(to);

        double rateFrom = rates[fromIndex];
        double rateTo = rates[toIndex];

        double amountInUSD = amount / rateFrom;
        
        double commissionOut;
        
        if (amountInUSD >= 1500){
            commissionOut = 0.975;
        } else {
            commissionOut = 0.95;
        }

        return amountInUSD * rateTo * commissionOut;
    }

    public int getCurrencyIndex(String currency){
        for (int i = 0; i < currencies.length; i++){
            if (currencies[i].equalsIgnoreCase(currency)) {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        moneyConverter_noAI converter = new moneyConverter_noAI();

        // need the following in JOptionPane

        System.out.print("Enter Amount: ");
        double amount = scnr.nextDouble();

        System.out.print("From currency: ");
        String from = scnr.next();

        System.out.print("To currency: ");
        String to = scnr.next();

        try {
            double result = converter.convert(amount, from, to);
            System.out.printf("%.2f %s = %.2f %s%n", amount, from.toUpperCase(), result, to.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}
