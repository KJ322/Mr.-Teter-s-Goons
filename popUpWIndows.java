import java.awt.FlowLayout;
import javax.swing.*;

public class popUpWIndows 
{
    public static void main(String[] args) 
    {
        //making placeholder arrays
        String[] currencies = {"EUR", "USD", "CAN"};

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
                double convertedAmount = convert(amount, sourceCurrency, targetCurrency);
                resultLabel.setText(String.format("%.2f %s is equivalent to %.2f %s", amount, sourceCurrency, convertedAmount, targetCurrency));
            }
            catch (IllegalArgumentException ex)
            {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });
    }
}