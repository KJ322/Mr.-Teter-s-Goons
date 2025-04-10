import java.awt.FlowLayout;
import javax.swing.*;

public class currencyConverterFinal
{
    public static void main(String[] args) 
    {
        //initializing arrays    
        String[] currencies = {
            "Afghan Afghani (AFN)", "Algerian Dinar (DZD)", "Angolan Kwanza (AOA)", "Bahraini Dinar (BHD)",
            "Botswana Pula (BWP)", "Burundian Franc (BIF)", "Cape Verdean Escudo (CVE)", "Central African CFA Franc (XAF)",
            "Comorian Franc (KMF)", "Congolese Franc (CDF)", "Djiboutian Franc (DJF)", "Egyptian Pound (EGP)",
            "Eritrean Nakfa (ERN)", "Ethiopian Birr (ETB)", "Gambian Dalasi (GMD)", "Ghanaian Cedi (GHS)",
            "Guinean Franc (GNF)", "Iranian Rial (IRR)", "Iraqi Dinar (IQD)", "Israeli New Shekel (ILS)",
            "Jordanian Dinar (JOD)", "Kenyan Shilling (KES)", "Kuwaiti Dinar (KWD)", "Lebanese Pound (LBP)",
            "Lesotho Loti (LSL)", "Liberian Dollar (LRD)", "Libyan Dinar (LYD)", "Malagasy Ariary (MGA)",
            "Malawian Kwacha (MWK)", "Mauritanian Ouguiya (MRU)", "Mauritian Rupee (MUR)", "Moroccan Dirham (MAD)",
            "Mozambican Metical (MZN)", "Namibian Dollar (NAD)", "Nigerian Naira (NGN)", "Omani Rial (OMR)",
            "Qatari Riyal (QAR)", "Rwandan Franc (RWF)", "Sao Tomean Dobra (STN)", "Saudi Riyal (SAR)",
            "Seychellois Rupee (SCR)", "Sierra Leonean Leone (SLL)", "Somali Shilling (SOS)", "South African Rand (ZAR)",
            "South Sudanese Pound (SSP)", "Swazi Lilangeni (SZL)", "Syrian Pound (SYP)", "Tunisian Dinar (TND)",
            "U.S. Dollar (USD)", "United Arab Emirates Dirham (AED)", "West African CFA Franc (XOF)", "Zimbabwean Dollar (ZWL)"
        };
        
        double[] rates = {
            71.60, 132.83, 63.8874, 0.376, 56.50, 2965.90, 99.77, 603.00,
            0.385, 1410.25, 177.94, 47.50, 15.00, 8676.76, 568.50, 15.49,
            130.26, 42112.50, 1309.88, 3.70, 0.709, 129.25, 0.30, 89596.90,
            601.00, 46.00, 4.82, 4642.89, 1733.99, 39.88, 64.75, 9.50,
            2686.45, 18.80, 1537.39, 19.2786, 3.64, 3660.86, 22.64, 3.75,
            921.163, 20969.50, 21.00, 18.76, 250.35, 2906.08, 13000.00, 3.06,
            1.00, 3.67, 594.94, 361.90
        };

        //creating the frame
        JFrame frame = new JFrame("Currency Converter");
        frame.setSize(400, 250);
        frame.setLayout(new FlowLayout());

        //creating the panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //creating the text field
        JTextField amountField = new JTextField(10);

        //Dropdown for source currency
        JComboBox<String> sourceCurrencyDropdown = new JComboBox<>(currencies);
        sourceCurrencyDropdown.setSelectedIndex(0);

        //Dropdown for target currency
        JComboBox<String> targetCurrencyDropdown = new JComboBox<>(currencies);
        targetCurrencyDropdown.setSelectedIndex(1);

        panel.add(new JLabel("Amount to Convert:"));
        panel.add(amountField);
        panel.add(new JLabel("From: "));
        panel.add(sourceCurrencyDropdown);
        panel.add(new JLabel("To: "));
        panel.add(targetCurrencyDropdown);
        panel.add(new JLabel(" ")); //makes a space between the button and the dropdown

        //creating the convert button
        JButton convertButton = new JButton("Convert");
        panel.add(convertButton);

        //creating result label
        JLabel resultLabel = new JLabel("");
        panel.add(resultLabel);

        frame.add(panel);
        frame.setVisible(true);

        //performs conversion on button press
        convertButton.addActionListener(e -> 
        {
            String amountStr = amountField.getText();
            double amount;
            try
            {
                amount = Double.parseDouble(amountStr);
            } catch(NumberFormatException ex)
            {
                resultLabel.setText("Invalid amount entered.");
                return;
            }

            String sourceCurrency = (String) sourceCurrencyDropdown.getSelectedItem();
            String targetCurrency = (String) targetCurrencyDropdown.getSelectedItem();

            try
            {
                //performs conversion
                double convertedAmount = convert(amount, sourceCurrency, targetCurrency, rates, currencies);
                JOptionPane.showMessageDialog(null, String.format("%.2f %s is equivalent to %.2f %s", amount, sourceCurrency, convertedAmount, targetCurrency));
            }
            catch (IllegalArgumentException ex)
            {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });
    }
    
    public static double convert(double amount, String sourceCurrency, String targetCurrency, double[] rates, String[] currencies){
        int fromIndex = getCurrencyIndex(sourceCurrency, currencies);
        int toIndex = getCurrencyIndex(targetCurrency, currencies);

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

    public static int getCurrencyIndex(String currency, String[] currencies){
        for (int i = 0; i < currencies.length; i++){
            if (currencies[i].equalsIgnoreCase(currency)) {
                return i;
            }
        }
        return -1;
    }
}
