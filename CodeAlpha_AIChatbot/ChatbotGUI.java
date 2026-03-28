package CodeAlpha_AIChatbot;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class ChatbotGUI {

    private JFrame frame;
    private JPanel chatPanel;
    private JTextField inputField;
    private ChatEngine engine;

    public ChatbotGUI() {
        engine = new ChatEngine("CodeAlpha_AIChatbot/faq.txt");

        frame = new JFrame("AI Chatbot");
        frame.setSize(500, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Chat Panel (vertical layout)
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatPanel.setBackground(new Color(230, 230, 230));

        JScrollPane scrollPane = new JScrollPane(chatPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Bottom input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(Color.WHITE);

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputField.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton sendButton = new JButton("Send");
        sendButton.setBackground(new Color(37, 211, 102));
        sendButton.setForeground(Color.WHITE);

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());

        frame.setVisible(true);
    }

    private void sendMessage() {
        String userText = inputField.getText().trim();
        if (userText.isEmpty()) return;

        addMessage(userText, true);

        String response = engine.getResponse(userText);
        addMessage(response, false);

        inputField.setText("");
    }

    private void addMessage(String text, boolean isUser) {
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());
        messagePanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        messagePanel.setOpaque(false);

        JLabel messageLabel = new JLabel("<html><p style='width: 200px'>" + text + "</p></html>");
        messageLabel.setOpaque(true);
        messageLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

        if (isUser) {
            messageLabel.setBackground(new Color(37, 211, 102)); // green
            messageLabel.setForeground(Color.WHITE);
            messagePanel.add(messageLabel, BorderLayout.EAST);
        } else {
            messageLabel.setBackground(Color.WHITE); // bot message
            messageLabel.setForeground(Color.BLACK);
            messagePanel.add(messageLabel, BorderLayout.WEST);
        }

        chatPanel.add(messagePanel);
        chatPanel.revalidate();

        // Auto scroll
        JScrollBar vertical = ((JScrollPane) chatPanel.getParent().getParent()).getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatbotGUI::new);
    }
}