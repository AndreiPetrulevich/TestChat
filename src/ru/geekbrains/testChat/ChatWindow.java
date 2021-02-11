package ru.geekbrains.testChat;
import netscape.javascript.JSObject;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChatWindow extends JFrame {
    private JTextPane chatPane;
    private JButton sendButton;
    private JTextField inputField;

    public ChatWindow() {
        setTitle("Tovarish Polkovnik");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 600);
        setMinimumSize(new Dimension(300, 100));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);

        chatPane = ChatWindow.createChatPane();
        mainPanel.add(ChatWindow.wrapChatPane(chatPane));

        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        inputField = new JTextField();
        sendButton = new JButton("Send");
        mainPanel.add(ChatWindow.createSendPanel(inputField, sendButton));

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        setVisible(true);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        inputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 10) {
                    sendMessage();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        inputField.requestFocus();
    }

    private void sendMessage() {
        chatPane.setText(chatPane.getText() + inputField.getText() + "\n");
        inputField.setText("");
    }

    // Helpers

    private static JTextPane createChatPane() {
        JTextPane chatPane = new JTextPane();
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setForeground(attributeSet, Color.black);
        chatPane.setCharacterAttributes(attributeSet, true);

        return chatPane;
    }

    private static JComponent wrapChatPane(JTextPane chatPane) {
        JScrollPane scrollPane = new JScrollPane(chatPane);

        JPanel borderLayoutPanel = new JPanel(new BorderLayout());
        borderLayoutPanel.add(scrollPane, BorderLayout.CENTER);

        return borderLayoutPanel;
    }

    private static JComponent createSendPanel(JTextField inputField, JButton sendButton) {
        JPanel sendPanel = new JPanel();
        sendPanel.setLayout(new BoxLayout(sendPanel, BoxLayout.X_AXIS));
        sendPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));

        sendPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        sendPanel.add(inputField);
        sendPanel.add(sendButton);

        sendPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        return sendPanel;
    }
}
