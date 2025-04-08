import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class Currency_Project_McNeill
{
    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static 
    {
        // Initialize currencies and their exchange rates against USD
        exchangeRates.put("USD", 1.0); // USD to USD

        //Louis Smelcer
        exchangeRates.put("CVE", 99.77); // CVS to USD
        exchangeRates.put("IRR", 42112.50); // IRR to USD
        exchangeRates.put("MWK", 1733.99); // MWK to USD
        exchangeRates.put("ZAR", 18.76); // ZAR to USD
        exchangeRates.put("ILS", 3.70); // ILS to USD
        exchangeRates.put("SLL", 20969.50); // SLL to USD
        exchangeRates.put("IQD", 1309.88); // IQD to USD
        exchangeRates.put("DZD", 132.83); // DZD to USD
        exchangeRates.put("MAD", 9.50); // MAD to USD
        exchangeRates.put("KES", 129.25); // KES to USD
        exchangeRates.put("SAR", 3.75); // SAR to USD
        exchangeRates.put("STN", 22.64); // STN to USD
        exchangeRates.put("DJF", 177.94); // DJF to USD
        exchangeRates.put("LBP", 89596.90); // LBP to USD

        //Katherine Jackson
        exchangeRates.put("ZWL", 361.90); // ZWL to USD
        exchangeRates.put("QAR", 3.64); // QAR to USD
        exchangeRates.put("BHD", 0.376); // BHD to USD
        exchangeRates.put("JOD", 0.709); // JOD to USD
        exchangeRates.put("XOF", 594.94); // XOF to USD
        exchangeRates.put("KWD", 0.30); // KWD to USD
        exchangeRates.put("AFN", 71.60); // AFN to USD
        exchangeRates.put("BIF", 2965.90); // BIF to USD
        exchangeRates.put("MGA", 4642.89); // MGA to USD
        exchangeRates.put("TND", 3.06); // TND to USD
        exchangeRates.put("ERN", 15.00); // ERN to USD
        exchangeRates.put("NGN", 1537.39); // NGN to USD
        exchangeRates.put("GHS", 15.49); // GHS to USD
        exchangeRates.put("LYD", 4.82); // LYD to USD

        //Isaiah McNeill
        exchangeRates.put("AOA", 921.163);// AOA to USD
        exchangeRates.put("CDF", 2906.08);// CDF to USD
        exchangeRates.put("GMD", 64.75);// GMD to USD
        exchangeRates.put("GNF", 8676.76);// GNF to USD
        exchangeRates.put("LSL", 19.2786);// LSL to USD
        exchangeRates.put("MZN", 63.8874);// MZN to USD
        exchangeRates.put("RWF", 1410.25);// RWF to USD
        exchangeRates.put("SOS", 568.50);// SOS to USD
        exchangeRates.put("SSP", 130.26);// SSP to USD
        exchangeRates.put("SDG", 601.00);// SDG to USD
        exchangeRates.put("TZS", 2686.45);// TZS to USD
        exchangeRates.put("UGX", 3660.86);// UGX  to USD
        exchangeRates.put("ZMW", 21.00);// ZMW to USD
        exchangeRates.put("YER", 250.35);// YER to USD

    }

    public static double convert(double amount, String fromCurrency, String toCurrency) 
    {
        double amountInUSD = amount / exchangeRates.get(fromCurrency);
        return amountInUSD * exchangeRates.get(toCurrency);
    }

    public static void main(String[] args) 
    {
        //array that stores the different currencies from the hash map
        String[] currencies = exchangeRates.keySet().toArray(new String[0]);
        
        String fromCurrency = (String) JOptionPane.showInputDialog(null, "Select the current currency:", "Currency Converter", JOptionPane.QUESTION_MESSAGE, null, currencies, currencies[0]);
        
        String toCurrency = (String) JOptionPane.showInputDialog(null, "Select the currency you're converitng to:", "Currency Converter", JOptionPane.QUESTION_MESSAGE, null, currencies, currencies[1]);
        
        String amountStr = JOptionPane.showInputDialog("Enter amount to convert:");
        
        //input validation
        try 
        {
            double amount = Double.parseDouble(amountStr);
            double convertedAmount = convert(amount, fromCurrency, toCurrency);
            
            JOptionPane.showMessageDialog(null, String.format("%.2f %s is equivalent to %.2f %s", amount, fromCurrency, convertedAmount, toCurrency));

            
        } 
        catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(null, "Invalid number entered.", "Error", JOptionPane.ERROR_MESSAGE);
        } 
        catch (NullPointerException e) 
        {
            JOptionPane.showMessageDialog(null, "Operation canceled.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /*public static String findFee(String rateStr)
    {
        rateStr = JOptionPane.showInputDialog( "Enter the fee:");

        
    }*/
}