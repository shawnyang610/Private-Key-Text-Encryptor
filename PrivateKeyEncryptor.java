/*
  Basic Synchronous text encryptor and decryptor
  supports all languages and symbols(within Unicode domain)
  Author Shawn Yang
  Nov 2016
*/
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.UIManager;

public class PrivateKeyEncryptor extends JFrame {

   private JPanel contentPane;
   private final ButtonGroup buttonGroup = new ButtonGroup();
   private static JButton button, button2;
   private static JTextArea text;
   private static JTextArea text2;
   private static char[] content;
   private static int[] passkey;
   private static char[] encryptedContent;
   private static char[] decryptedContent;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               PrivateKeyEncryptor frame = new PrivateKeyEncryptor();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public PrivateKeyEncryptor() {
      setTitle("Shawn Yang's EZ Text Encryptor");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 530, 400);
      contentPane = new JPanel();
      contentPane.setName("Shawn Yang's Simple Text Encryptor");
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      GridBagLayout gbl_contentPane = new GridBagLayout();
      gbl_contentPane.columnWidths = new int[]{52,52,52,52,52,52,52,52,52,52};
      gbl_contentPane.rowHeights = new int[]{0,0,0,0,0,0,0,0,0,0,0,0};
      gbl_contentPane.columnWeights = new double[]{Double.MIN_VALUE};
      gbl_contentPane.rowWeights = new double[]{0,0,0,0.0,0,0,0,0,0,1};
      contentPane.setLayout(gbl_contentPane);
      
      JLabel label = new JLabel("1.Select Mode -> 2.Input Text/Code and Passkey -> 3.Encrypt/Decrypt!");
      label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
      label.setForeground(new Color(0, 0, 0));
      GridBagConstraints gbc_label = new GridBagConstraints();
      gbc_label.gridwidth = 10;
      gbc_label.insets = new Insets(0, 0, 5, 0);
      gbc_label.gridx = 0;
      gbc_label.gridy = 0;
      contentPane.add(label, gbc_label);
      
      JRadioButton radioButton = new JRadioButton("Encrypt Mode");
      radioButton.setFont(new Font("Lucida Grande", Font.BOLD, 13));
      radioButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
           button.setText("Encrypt!");
           button.setForeground(Color.RED);
         }
      });
      radioButton.setDoubleBuffered(true);
      radioButton.setSelected(true);
      buttonGroup.add(radioButton);
      GridBagConstraints gbc_radioButton = new GridBagConstraints();
      gbc_radioButton.gridwidth = 3;
      gbc_radioButton.insets = new Insets(0, 0, 5, 5);
      gbc_radioButton.gridx = 2;
      gbc_radioButton.gridy = 1;
      contentPane.add(radioButton, gbc_radioButton);
      
      JRadioButton radioButton_1 = new JRadioButton("Decrypt Mode");
      radioButton_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
      radioButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            button.setText("Decrypt!");
            button.setForeground(Color.GREEN);
         }
      });
      radioButton_1.setDoubleBuffered(true);
      buttonGroup.add(radioButton_1);
      GridBagConstraints gbc_radioButton_1 = new GridBagConstraints();
      gbc_radioButton_1.gridwidth = 3;
      gbc_radioButton_1.insets = new Insets(0, 0, 5, 5);
      gbc_radioButton_1.gridx = 5;
      gbc_radioButton_1.gridy = 1;
      contentPane.add(radioButton_1, gbc_radioButton_1);
      
      JSeparator separator = new JSeparator();
      separator.setRequestFocusEnabled(false);
      separator.setVerifyInputWhenFocusTarget(false);
      separator.setFocusTraversalKeysEnabled(false);
      GridBagConstraints gbc_separator = new GridBagConstraints();
      gbc_separator.fill = GridBagConstraints.BOTH;
      gbc_separator.gridwidth = 10;
      gbc_separator.insets = new Insets(0, 0, 5, 0);
      gbc_separator.gridx = 0;
      gbc_separator.gridy = 2;
      contentPane.add(separator, gbc_separator);
      
      JLabel label_1 = new JLabel("Enter Passkey:");
      label_1.setFont(new Font("Lucida Grande", Font.BOLD, 12));
      GridBagConstraints gbc_label_1 = new GridBagConstraints();
      gbc_label_1.anchor = GridBagConstraints.WEST;
      gbc_label_1.gridheight = 2;
      gbc_label_1.gridwidth = 2;
      gbc_label_1.insets = new Insets(0, 5, 5, 5);
      gbc_label_1.gridx = 0;
      gbc_label_1.gridy = 3;
      contentPane.add(label_1, gbc_label_1);
      
      text = new JTextArea (2, 8);
      JScrollPane scrollPane = new JScrollPane(text);
      text.setDoubleBuffered(true);
      text.setLineWrap(true);
      GridBagConstraints gbc_text=new GridBagConstraints();
      gbc_text.insets = new Insets(0, 0, 5, 10);
      gbc_text.fill = GridBagConstraints.BOTH;
      gbc_text.gridheight = 2;
      gbc_text.gridwidth = 8;
      gbc_text.gridx=2;
      gbc_text.gridy=3;
      contentPane.add(scrollPane, gbc_text);
      
      JLabel lblEnterText = new JLabel("Enter Text:");
      lblEnterText.setFont(new Font("Lucida Grande", Font.BOLD, 12));
      GridBagConstraints gbc_lblEnterText = new GridBagConstraints();
      gbc_lblEnterText.anchor = GridBagConstraints.WEST;
      gbc_lblEnterText.gridwidth = 2;
      gbc_lblEnterText.insets = new Insets(0, 5, 5, 5);
      gbc_lblEnterText.gridx = 0;
      gbc_lblEnterText.gridy = 7;
      contentPane.add(lblEnterText, gbc_lblEnterText);
      
      text2 = new JTextArea(5,8);
      JScrollPane scrollPane2 = new JScrollPane(text2);
      GridBagConstraints gbc_text2=new GridBagConstraints();
      gbc_text2.gridx=2;
      gbc_text2.gridy=5;
      gbc_text2.gridwidth=8;
      gbc_text2.gridheight=5;
      gbc_text2.fill=GridBagConstraints.BOTH;
      gbc_text2.insets=new Insets(10, 0, 5, 10);
      contentPane.add(scrollPane2, gbc_text2);
      
      JSeparator separator_1 = new JSeparator();
      GridBagConstraints gbc_separator_1 = new GridBagConstraints();
      gbc_separator_1.fill = GridBagConstraints.BOTH;
      gbc_separator.fill = GridBagConstraints.HORIZONTAL;
      gbc_separator_1.gridwidth = 10;
      gbc_separator_1.insets = new Insets(0, 0, 0, 0);
      gbc_separator_1.gridx = 0;
      gbc_separator_1.gridy = 10;
      contentPane.add(separator_1, gbc_separator_1);
      
      button = new JButton("Encrypt!");
      button.setForeground(Color.RED);
      button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            content=inputContent();
            passkey=inputPasskey();
            if (e.getActionCommand()=="Encrypt!"){
               encryptedContent=encryptContent(content, passkey);
               text2.setForeground(Color.red);
               display(encryptedContent);
            }
            if (e.getActionCommand()=="Decrypt!"){
               decryptedContent=decryptContent(content, passkey);
               text2.setForeground(Color.green);
               display(decryptedContent);
            }
         }
      });
      button.setFont(new Font("Lucida Grande", Font.BOLD, 13));
      GridBagConstraints gbc_button = new GridBagConstraints();
      gbc_button.fill = GridBagConstraints.HORIZONTAL;
      gbc_button.gridx=2;
      gbc_button.gridy=11;
      gbc_button.gridwidth=3;
      contentPane.add(button, gbc_button);
      
      button2 = new JButton("Reset");
      button2.setForeground(UIManager.getColor("Button.light"));
      button2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            text2.setText("");
            text2.setForeground(Color.black);
         }
      });
      button2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
      GridBagConstraints gbc_button2 = new GridBagConstraints();
      gbc_button2.gridx=6;
      gbc_button2.gridy=11;
      gbc_button2.gridwidth=3;
      gbc_button2.fill=GridBagConstraints.HORIZONTAL;
      contentPane.add(button2, gbc_button2);
   }
   
   //Arithmetic methods
   
   /**
    * 
    */
   private static char[] inputContent(){
      String word = text2.getText();
      int charLength= word.length();
      char[] characters = new char[charLength];
      for (int i=0; i<charLength; i++) {
         characters[i]=word.charAt(i);        
      }
      return characters;
   }
   /**
    * 
    * @return
    */
   private static int[] inputPasskey() {
      String word = text.getText();
      int passkeyLength= word.length();
      int[] passkey = new int[passkeyLength];
      for (int i=0; i<passkeyLength; i++) {
         passkey[i]=(int)word.charAt(i);
      }
      return passkey;
   }
   /**
    * 
    * @param characters
    * @param passkey
    * @return
    */
   private static char[] encryptContent(char[] characters, int[] passkey) {
      int charLength = characters.length;
      int passkeyLength= passkey.length;
      for (int i=0; i<charLength; i++) {
         characters[i]= (char) ((int)(characters[i])-passkey[i%passkeyLength]);
      }
      return characters;
   }
   /**
    * 
    * @param characters
    * @param passkey
    * @return
    */
   private static char[] decryptContent(char[] characters, int[] passkey) {
      int charLength = characters.length;
      int passkeyLength= passkey.length;
      for (int i=0; i<charLength; i++) {
         characters[i]= (char) ((int)(characters[i])+passkey[i%passkeyLength]);
      }
      return characters;
   }
   /**
    * 
    * @param characters
    */
   private static void display (char[] characters) {
      text2.setText("");
      int charLength = characters.length;
      for (int i=0; i<charLength; i++){
         text2.append(Character.toString(characters[i]));
      }
   }

}
