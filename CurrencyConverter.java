import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CurrencyConverter {
    // A map to store currency exchange rates against USD
    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        // Initialize with some example currencies and their exchange rates against USD
        exchangeRates.put("USD", 1.0); // USD to USD
        exchangeRates.put("CVE", 99.77); // EUR to USD
        exchangeRates.put("IRR", 42112.50); // GBP to USD
        exchangeRates.put("MWK", 1733.99); // INR to USD
        exchangeRates.put("ZAR", 18.76); // JPY to USD
        exchangeRates.put("ILS", 3.70);
        exchangeRates.put("SLL", 20969.50);
        exchangeRates.put("IQD", 1309.88);
        exchangeRates.put("DZD", 132.83);
        exchangeRates.put("MAD", 9.50);
        exchangeRates.put("KES", 129.25);
        exchangeRates.put("SAR", 3.75);
        exchangeRates.put("STN", 22.64);
        exchangeRates.put("DJF", 177.94);
        exchangeRates.put("LBP", 89596.90);
    }

    public static double convert(double amount, String fromCurrency, String toCurrency) {
        // Check if the provided currencies are valid
        if (!exchangeRates.containsKey(fromCurrency) || !exchangeRates.containsKey(toCurrency)) {
            throw new IllegalArgumentException("Invalid currency code.");
        }

        // Convert the amount to USD first
        double amountInUSD = amount / exchangeRates.get(fromCurrency);

        // Convert the USD amount to the target currency
        return amountInUSD * exchangeRates.get(toCurrency);
    }

    public static void addCurrency(String currencyCode, double rateToUSD) {
        exchangeRates.put(currencyCode, rateToUSD);
    }

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new FlowLayout());

        // Create a panel for the input fields
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create dropdown for source currency
        String[] currencies = exchangeRates.keySet().toArray(new String[0]);
        JComboBox<String> fromCurrencyComboBox = new JComboBox<>(currencies);
        fromCurrencyComboBox.setSelectedIndex(0);  // Default selection

        // Create dropdown for target currency
        JComboBox<String> toCurrencyComboBox = new JComboBox<>(currencies);
        toCurrencyComboBox.setSelectedIndex(1);  // Default selection for target currency

        // Create text field for entering amount
        JTextField amountField = new JTextField(10);

        // Add components to the panel
        panel.add(new JLabel("Amount to Convert:"));
        panel.add(amountField);
        panel.add(new JLabel("From Currency:"));
        panel.add(fromCurrencyComboBox);
        panel.add(new JLabel("To Currency:"));
        panel.add(toCurrencyComboBox);

        // Create convert button
        JButton convertButton = new JButton("Convert");
        panel.add(convertButton);

        // Create result label
        JLabel resultLabel = new JLabel("");
        panel.add(resultLabel);

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);

        // Handle the conversion when the button is clicked
        convertButton.addActionListener(e -> {
            String amountStr = amountField.getText();
            double amount;
            try {
                amount = Double.parseDouble(amountStr);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid amount entered.");
                return;
            }

            String fromCurrency = (String) fromCurrencyComboBox.getSelectedItem();
            String toCurrency = (String) toCurrencyComboBox.getSelectedItem();

            try {
                // Perform the conversion
                double convertedAmount = convert(amount, fromCurrency, toCurrency);
                resultLabel.setText(String.format("%.2f %s is equivalent to %.2f %s", amount, fromCurrency, convertedAmount, toCurrency));
            } catch (IllegalArgumentException ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });
    }
}
