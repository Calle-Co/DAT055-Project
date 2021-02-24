package customer.booking;

import javax.swing.*;

import admin.AdminView;

import java.awt.*;

public class test {

    private static JPanel adView = new JPanel();
    private static JTextArea text = new JTextArea();
    public static void main(String[] args) {
        BookingView v = new BookingView();
        BookingController c = new BookingController(v);
        initAdmin();
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(v, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JFrame adminLook = new JFrame();
        adminLook.setLayout(new BorderLayout());
        adminLook.add(adView, BorderLayout.CENTER);
        adminLook.pack();
        adminLook.setVisible(true);
        adminLook.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }

    private static void initAdmin() {
        text.setText("Tja");
        text.setPreferredSize(new Dimension(200,600));
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        adView.add(text);
        adView.setPreferredSize(new Dimension(200,600));
        adView.setVisible(true);
    }

    public static void updateDisplay(String s) {
        text.setText(s);
        adView.revalidate();
        adView.repaint();
    }
}
