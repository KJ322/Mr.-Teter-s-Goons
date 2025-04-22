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
             "Jordanian Dinar (JOD)", "Kenyan Shilling (KES)", "Kuwaiti Dinar (KWD)", "Kyrgyzstan Som (KGS)", 
             "Lebanese Pound (LBP)", "Lesotho Loti (LSL)", "Liberian Dollar (LRD)", "Libyan Dinar (LYD)", 
             "Malagasy Ariary (MGA)", "Malawian Kwacha (MWK)", "Mauritanian Ouguiya (MRU)", "Mauritian Rupee (MUR)", 
             "Moroccan Dirham (MAD)", "Mozambican Metical (MZN)", "Namibian Dollar (NAD)", "Nigerian Naira (NGN)", 
             "Omani Rial (OMR)", "Pakistani Rupee (PKR)", "Qatari Riyal (QAR)", "Rwandan Franc (RWF)", 
             "Saudi Riyal (SAR)", "Sao Tomean Dobra (STN)", "Seychellois Rupee (SCR)", "Sierra Leonean Leone (SLL)", 
             "Somali Shilling (SOS)", "South African Rand (ZAR)", "South Sudanese Pound (SSP)", "Sudanese Pound (SDG)", 
             "Swazi Lilangeni (SZL)", "Syrian Pound (SYP)", "Tajikistan Somoni (TJS)", "Tanzanian Shilling (TZS)", 
             "Turkish Lira (TRY)", "Turkmenistan Manat (TMT)", "Tunisian Dinar (TND)", "U.S. Dollar (USD)", 
             "Ugandan Shilling (UGX)", "United Arab Emirates Dirham (AED)", "Uzbekistan Som (UZS)", 
             "West African CFA Franc (XOF)", "Yemeni Rial (YER)", "Zambian Kwacha (ZMW)", "Zimbabwean Dollar (ZWL)"
        };
        
        double[] rates = {
            71.60, 132.83, 19.2786, 0.376, 18.80, 2965.90, 99.77, 603.00, 56.50, 63.8874, 177.94, 47.50, 
            15.00, 2906.08, 63.8874, 15.49, 601.00, 42112.50, 1309.88, 3.70, 0.709, 129.25, 0.30, 84.36, 
            89596.90, 130.26, 46.00, 4.82, 2965.90, 1733.99, 39.88, 64.75, 9.50, 1410.25, 18.76, 1537.39, 
            0.385, 280.00, 3.64, 601.00, 3.75, 22.64, 56.50, 20969.50, 2686.45, 18.76, 3660.86, 21.00, 
            0.385, 13000.00, 10.938, 250.35, 36.68, 3.50, 3.06, 1.0, 568.50, 3.67, 12930.00, 594.94, 36.68, 361.90
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
                // question to play again
                int response = JOptionPane.showConfirmDialog(null, "Do you want to convert another amount?", "Play Again", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                amountField.setText("");
                sourceCurrencyDropDown.setSelectedIndex(0);
                targetCurrencyDropDown.setSelectedIndex(1);
                resultLabel.setText("");
            } else {
                frame.dispose(); // closes the window
            }
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
