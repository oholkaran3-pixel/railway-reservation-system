package com.railway;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class App extends JFrame {
    private final Color navy = new Color(20, 55, 100);
    private final Color blue = new Color(40, 110, 190);
    private final Color background = new Color(242, 247, 252);
    private final String url =
            "jdbc:mysql://localhost:3306/railway_reservation?useSSL=false&serverTimezone=UTC";

    public App() {
        setTitle("Railway Reservation System");
        setSize(1000, 650);
        setMinimumSize(new Dimension(850, 550));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(background);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(navy);
        header.setBorder(new EmptyBorder(22, 28, 22, 28));

        JLabel title = new JLabel("RAILWAY RESERVATION SYSTEM");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 25));

        JLabel subtitle = new JLabel("Your journey starts here");
        subtitle.setForeground(new Color(220, 232, 245));

        JPanel heading = new JPanel();
        heading.setOpaque(false);
        heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));
        heading.add(title);
        heading.add(Box.createVerticalStrut(7));
        heading.add(subtitle);
        header.add(heading, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        JPanel sidebar = new JPanel();
        sidebar.setBackground(Color.WHITE);
        sidebar.setBorder(new EmptyBorder(25, 16, 25, 16));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(220, 0));

        JLabel menu = new JLabel("MAIN MENU");
        menu.setFont(new Font("Arial", Font.BOLD, 13));
        menu.setForeground(Color.GRAY);
        sidebar.add(menu);
        sidebar.add(Box.createVerticalStrut(22));

        JButton home = createButton("Dashboard");
        JButton trains = createButton("Search Trains");
        JButton booking = createButton("Book Ticket");
        JButton myTickets = createButton("My Tickets");
        JButton cancel = createButton("Cancel Ticket");

        for (JButton b : new JButton[]{home, trains, booking, myTickets, cancel}) {
            sidebar.add(b);
            sidebar.add(Box.createVerticalStrut(12));
        }
        add(sidebar, BorderLayout.WEST);

        JPanel content = new JPanel(new BorderLayout(15, 18));
        content.setBackground(background);
        content.setBorder(new EmptyBorder(28, 30, 28, 30));

        JLabel welcome = new JLabel("Welcome to Railway Express");
        welcome.setFont(new Font("Arial", Font.BOLD, 24));
        welcome.setForeground(navy);

        JLabel description = new JLabel(
                "Search trains, book tickets, and manage your reservations.");

        JPanel intro = new JPanel();
        intro.setOpaque(false);
        intro.setLayout(new BoxLayout(intro, BoxLayout.Y_AXIS));
        intro.add(welcome);
        intro.add(Box.createVerticalStrut(10));
        intro.add(description);

        JPanel cards = new JPanel(new GridLayout(1, 2, 18, 18));
        cards.setOpaque(false);
        cards.add(createCard("TRAIN SCHEDULE",
                "View available trains and journey details.", "Search Trains"));
        cards.add(createCard("RESERVATIONS",
                "Book and manage your railway tickets.", "Book Ticket"));

        content.add(intro, BorderLayout.NORTH);
        content.add(cards, BorderLayout.CENTER);
        add(content, BorderLayout.CENTER);

        home.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Welcome to Railway Reservation System!"));
        trains.addActionListener(e -> loadTrains());
        booking.addActionListener(e -> bookTicket());
        myTickets.addActionListener(e -> showMyTickets());
        cancel.addActionListener(e -> cancelTicket());
    }

    private JButton createButton(String text) {
        JButton b = new JButton(text);
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setForeground(navy);
        b.setBackground(Color.WHITE);
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(13, 12, 13, 12));
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }

    private JPanel createCard(String heading, String description, String buttonText) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 230, 240)),
                new EmptyBorder(24, 22, 24, 22)));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(heading);
        title.setFont(new Font("Arial", Font.BOLD, 17));
        title.setForeground(blue);

        JTextArea text = new JTextArea(description);
        text.setFont(new Font("Arial", Font.PLAIN, 14));
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setOpaque(false);

        JButton action = new JButton(buttonText);
        action.setUI(new BasicButtonUI());
        // Button styling fix: strong contrast and consistent sizing.
        action.setFont(new Font("Arial", Font.BOLD, 14));
        action.setBackground(new Color(40, 110, 190));
        action.setForeground(Color.WHITE);
        action.setOpaque(true);
        action.setContentAreaFilled(true);
        action.setOpaque(true);
        action.setContentAreaFilled(true);
        action.setBorderPainted(true);
        action.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(25, 85, 150), 1),
                new EmptyBorder(8, 18, 8, 18)));
        action.setFocusPainted(false);
        action.setAlignmentX(Component.CENTER_ALIGNMENT);
        action.setCursor(new Cursor(Cursor.HAND_CURSOR));
        action.setPreferredSize(new Dimension(150, 40));
        action.setMinimumSize(new Dimension(150, 40));
        action.setMaximumSize(new Dimension(180, 40));
        action.addActionListener(e -> {
            if (buttonText.equals("Search Trains")) loadTrains();
            else bookTicket();
        });

        card.add(title);
        card.add(Box.createVerticalStrut(18));
        card.add(text);
        card.add(Box.createVerticalStrut(25));
        card.add(action);
        return card;
    }

    private String readPassword() {
        String p = System.getenv("RAILWAY_DB_PASSWORD");
        if (p == null || p.trim().isEmpty()) {
            throw new IllegalStateException(
                    "Database password missing. Set RAILWAY_DB_PASSWORD in PowerShell before running the app.");
        }
        return p;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, "root", readPassword());
    }

    private void loadTrains() {
        String sql = "SELECT t.train_number, t.train_name, t.source_station, " +
                "t.destination_station, t.departure_time, t.arrival_time, " +
                "t.fare, t.total_seats - (SELECT COUNT(*) FROM reservations r " +
                "WHERE r.train_id=t.train_id AND r.status='CONFIRMED' " +
                "AND r.journey_date >= CURRENT_DATE) AS available " +
                "FROM trains t ORDER BY t.train_number";

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            String[] columns = {"Train Number", "Train Name", "From", "Destination",
                    "Departure", "Arrival", "Available Seats*", "Fare (Rs.)"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override public boolean isCellEditable(int row, int column) { return false; }
            };
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("train_number"), rs.getString("train_name"),
                        rs.getString("source_station"), rs.getString("destination_station"),
                        rs.getString("departure_time"), rs.getString("arrival_time"),
                        Math.max(0, rs.getInt("available")), rs.getBigDecimal("fare")
                });
            }
            JTable table = new JTable(model);
            table.setRowHeight(30);
            table.setAutoCreateRowSorter(true);
            table.getTableHeader().setBackground(navy);
            table.getTableHeader().setForeground(Color.WHITE);
            JScrollPane scroll = new JScrollPane(table);
            scroll.setPreferredSize(new Dimension(850, 350));
            JPanel panel = new JPanel(new BorderLayout(0, 12));
            panel.setBorder(new EmptyBorder(12, 12, 12, 12));
            panel.add(new JLabel("Available Trains (* availability is an overall estimate; confirm for journey date)"),
                    BorderLayout.NORTH);
            panel.add(scroll, BorderLayout.CENTER);
            JOptionPane.showMessageDialog(this, panel,
                    "Railway Express | Train Schedule", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private static class TrainOption {
        int id;
        String number, name, source, destination;
        double fare;

        TrainOption(int id, String number, String name, String source,
                    String destination, double fare) {
            this.id = id;
            this.number = number;
            this.name = name;
            this.source = source;
            this.destination = destination;
            this.fare = fare;
        }

        @Override public String toString() {
            return number + " - " + name + " (" + source + " to " + destination
                    + ") - Rs. " + fare;
        }
    }

    private JComboBox<TrainOption> getTrains() throws SQLException {
        JComboBox<TrainOption> combo = new JComboBox<>();
        String sql = "SELECT train_id, train_number, train_name, source_station, " +
                "destination_station, fare FROM trains ORDER BY train_number";
        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                combo.addItem(new TrainOption(
                        rs.getInt("train_id"), rs.getString("train_number"),
                        rs.getString("train_name"), rs.getString("source_station"),
                        rs.getString("destination_station"), rs.getDouble("fare")));
            }
        }
        return combo;
    }

    private void bookTicket() {
        try {
            JTextField name = new JTextField();
            JTextField age = new JTextField();
            JComboBox<String> gender = new JComboBox<>(new String[]{"Male", "Female", "Other"});
            JTextField phone = new JTextField();
            JTextField email = new JTextField();
            JTextField date = new JTextField(LocalDate.now().plusDays(1).toString());
            JComboBox<TrainOption> train = getTrains();

            if (train.getItemCount() == 0) {
                JOptionPane.showMessageDialog(this, "No trains found.");
                return;
            }

            JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
            form.add(new JLabel("Passenger name:")); form.add(name);
            form.add(new JLabel("Age:")); form.add(age);
            form.add(new JLabel("Gender:")); form.add(gender);
            form.add(new JLabel("Phone (use same phone for My Tickets):")); form.add(phone);
            form.add(new JLabel("Email (optional):")); form.add(email);
            form.add(new JLabel("Journey date (YYYY-MM-DD):")); form.add(date);
            form.add(new JLabel("Select train:")); form.add(train);

            int result = JOptionPane.showConfirmDialog(this, form, "Book Railway Ticket",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) return;

            String passengerName = name.getText().trim();
            String phoneNumber = phone.getText().trim();
            String ageText = age.getText().trim();
            String emailText = email.getText().trim();
            int passengerAge;
            LocalDate journeyDate;

            if (passengerName.isEmpty() || phoneNumber.isEmpty() || ageText.isEmpty()) {
                throw new IllegalArgumentException("Name, age and phone number are required.");
            }
            if (!phoneNumber.matches("[0-9+ -]{7,15}")) {
                throw new IllegalArgumentException("Enter a valid phone number (7–15 digits/symbols).");
            }
            try {
                passengerAge = Integer.parseInt(ageText);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Age must be a whole number.");
            }
            if (passengerAge < 1 || passengerAge > 120) {
                throw new IllegalArgumentException("Age must be between 1 and 120.");
            }
            try {
                journeyDate = LocalDate.parse(date.getText().trim());
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException("Enter journey date as YYYY-MM-DD.");
            }
            if (journeyDate.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Journey date cannot be in the past.");
            }

            TrainOption selected = (TrainOption) train.getSelectedItem();
            if (selected == null) throw new IllegalArgumentException("Please select a train.");

            String pnr = createPnr();
            int seat;
            try (Connection con = connect()) {
                con.setAutoCommit(false);
                try {
                    int totalSeats;
                    try (PreparedStatement ps = con.prepareStatement(
                            "SELECT total_seats FROM trains WHERE train_id = ? FOR UPDATE")) {
                        ps.setInt(1, selected.id);
                        try (ResultSet rs = ps.executeQuery()) {
                            if (!rs.next()) throw new SQLException("Train no longer exists.");
                            totalSeats = rs.getInt("total_seats");
                        }
                    }

                    boolean[] occupied = new boolean[totalSeats + 1];
                    try (PreparedStatement ps = con.prepareStatement(
                            "SELECT seat_number FROM reservations WHERE train_id = ? " +
                                    "AND journey_date = ? AND status = 'CONFIRMED'")) {
                        ps.setInt(1, selected.id);
                        ps.setDate(2, Date.valueOf(journeyDate));
                        try (ResultSet rs = ps.executeQuery()) {
                            while (rs.next()) {
                                int s = rs.getInt("seat_number");
                                if (s >= 1 && s <= totalSeats) occupied[s] = true;
                            }
                        }
                    }
                    seat = -1;
                    for (int i = 1; i <= totalSeats; i++) {
                        if (!occupied[i]) { seat = i; break; }
                    }
                    if (seat == -1) throw new SQLException("No seats available for " + journeyDate + ".");

                    int passengerId;
                    try (PreparedStatement ps = con.prepareStatement(
                            "INSERT INTO passengers (full_name, age, gender, phone, email) " +
                                    "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                        ps.setString(1, passengerName);
                        ps.setInt(2, passengerAge);
                        ps.setString(3, (String) gender.getSelectedItem());
                        ps.setString(4, phoneNumber);
                        if (emailText.isEmpty()) ps.setNull(5, Types.VARCHAR);
                        else ps.setString(5, emailText);
                        ps.executeUpdate();
                        try (ResultSet keys = ps.getGeneratedKeys()) {
                            if (!keys.next()) throw new SQLException("Could not save passenger details.");
                            passengerId = keys.getInt(1);
                        }
                    }

                    try (PreparedStatement ps = con.prepareStatement(
                            "INSERT INTO reservations (pnr_number, passenger_id, train_id, journey_date, " +
                                    "seat_number, status) VALUES (?, ?, ?, ?, ?, 'CONFIRMED')")) {
                        ps.setString(1, pnr);
                        ps.setInt(2, passengerId);
                        ps.setInt(3, selected.id);
                        ps.setDate(4, Date.valueOf(journeyDate));
                        ps.setInt(5, seat);
                        ps.executeUpdate();
                    }
                    con.commit();
                } catch (Exception ex) {
                    con.rollback();
                    throw ex;
                } finally {
                    con.setAutoCommit(true);
                }
            }

            JOptionPane.showMessageDialog(this,
                    "BOOKING CONFIRMED!\n\nPNR: " + pnr +
                            "\nPassenger: " + passengerName +
                            "\nTrain: " + selected.number + " - " + selected.name +
                            "\nJourney date: " + journeyDate +
                            "\nSeat number: " + seat +
                            "\nFare: Rs. " + selected.fare +
                            "\n\nKeep your PNR and phone number to view/cancel this ticket.",
                    "Ticket Confirmed", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void showMyTickets() {
        JTextField phoneField = new JTextField();
        JPanel form = new JPanel(new GridLayout(0, 1, 6, 6));
        form.add(new JLabel("Enter the phone number used when booking:"));
        form.add(phoneField);
        int answer = JOptionPane.showConfirmDialog(this, form, "Find My Tickets",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (answer != JOptionPane.OK_OPTION) return;

        String phone = phoneField.getText().trim();
        if (phone.isEmpty()) {
            showError(new IllegalArgumentException("Phone number is required."));
            return;
        }

        String sql = "SELECT r.pnr_number, t.train_number, t.train_name, t.source_station, " +
                "t.destination_station, r.journey_date, r.seat_number, r.status " +
                "FROM reservations r JOIN passengers p ON p.passenger_id=r.passenger_id " +
                "JOIN trains t ON t.train_id=r.train_id WHERE p.phone=? ORDER BY r.booking_date DESC";
        try (Connection con = connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) {
                String[] columns = {"PNR", "Train No.", "Train Name", "From", "To",
                        "Journey Date", "Seat", "Status"};
                DefaultTableModel model = new DefaultTableModel(columns, 0) {
                    @Override public boolean isCellEditable(int row, int column) { return false; }
                };
                while (rs.next()) {
                    model.addRow(new Object[]{rs.getString("pnr_number"),
                            rs.getString("train_number"), rs.getString("train_name"),
                            rs.getString("source_station"), rs.getString("destination_station"),
                            rs.getDate("journey_date"), rs.getInt("seat_number"), rs.getString("status")});
                }
                if (model.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No tickets found for that phone number.",
                            "My Tickets", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                JTable table = new JTable(model);
                table.setRowHeight(26);
                JScrollPane scroll = new JScrollPane(table);
                scroll.setPreferredSize(new Dimension(850, 300));
                JOptionPane.showMessageDialog(this, scroll, "My Tickets", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private String createPnr() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
    }

    private void cancelTicket() {
        JTextField pnrField = new JTextField();
        JTextField phoneField = new JTextField();
        JPanel form = new JPanel(new GridLayout(0, 1, 6, 6));
        form.add(new JLabel("PNR number:"));
        form.add(pnrField);
        form.add(new JLabel("Phone number used when booking:"));
        form.add(phoneField);

        int answer = JOptionPane.showConfirmDialog(this, form, "Cancel Ticket",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (answer != JOptionPane.OK_OPTION) return;

        String pnr = pnrField.getText().trim().toUpperCase();
        String phone = phoneField.getText().trim();
        if (pnr.isEmpty() || phone.isEmpty()) {
            showError(new IllegalArgumentException("Both PNR and phone number are required."));
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Cancel ticket " + pnr + "?", "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        String sql = "UPDATE reservations r JOIN passengers p ON p.passenger_id=r.passenger_id " +
                "SET r.status='CANCELLED' WHERE r.pnr_number=? AND p.phone=? AND r.status='CONFIRMED'";
        try (Connection con = connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pnr);
            ps.setString(2, phone);
            int updated = ps.executeUpdate();
            if (updated == 1) {
                JOptionPane.showMessageDialog(this, "Ticket cancelled successfully.\nPNR: " + pnr,
                        "Cancellation Successful", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "No confirmed ticket matched that PNR and phone number.",
                        "Cancellation Not Completed", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void showError(Exception ex) {
        String message = ex.getMessage() == null ? ex.toString() : ex.getMessage();
        JOptionPane.showMessageDialog(this, message,
                "Railway Reservation System", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) { }
            new App().setVisible(true);
        });
    }
}