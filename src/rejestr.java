import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;


public class rejestr extends JFrame {

    public static java.util.List<Person> People = new ArrayList<Person>();

    private static JFrame frame = new JFrame();
    private static ImagePanel bgPanel = new ImagePanel();

    private static JTable dataTable = new JTable();
    private static JPanel dataPanel = new JPanel();
    private static JButton Delete = new JButton("Usuń");
    private static JButton Back = new JButton("Powrót");

    private JLabel n_rej = new JLabel();
    private static JTextField n_rej_input = new JTextField();

    private JLabel tytol = new JLabel("Rejestr Łodzi");

    private JLabel owner = new JLabel();
    private static JTextField owner_input = new JTextField();

    private JLabel miej_zacumowania = new JLabel();
    private static String[] opcje = {
            "Port nr. 1",
            "Port nr. 2",
            "Port nr. 3",
            "Port nr. 4",
            "Port nr. 5",
            "Port nr. 6"
    };
    private static JComboBox<String> miejsce_zacumowania_select = new JComboBox<String>(opcje);

    private JLabel wybor_daty = new JLabel();
    private static JDateChooser wybor_daty_input = new JDateChooser();

    private JLabel karta_czlonkowstwa = new JLabel();
    private static ButtonGroup karta_czlonkowstwa_group = new ButtonGroup();
    private JRadioButton karta_czlonkowstwa_radio1 = new JRadioButton();
    private JRadioButton karta_czlonkowstwa_radio2 = new JRadioButton();

    private static JButton zapisz = new JButton("Zapisz");
    private static JButton wyswietl = new JButton("Wyświetl rejestr");

    private Font titleFont = new Font("Serif", Font.BOLD, 25);
    private Font mainFont = new Font("Serif", Font.PLAIN, 20);

    public rejestr() {
        dataPanel.setBounds(0, 0, getWidth(), getHeight());
        dataPanel.setLayout(null);
        dataPanel.setVisible(true);


        Delete.setBounds(20, 385, 200, 50);

        Back.setBounds(230, 385, 200, 50);

        dataPanel.add(Back);
        dataPanel.add(Delete);

        dataTable.setRowHeight(50);


        bgPanel.setLayout(null);

        tytol.setFont(titleFont);
        tytol.setBounds(240, 20, 200, 20);
        tytol.setForeground(Color.WHITE);

        n_rej.setFont(mainFont);
        n_rej.setForeground(Color.WHITE);
        n_rej.setText("Numer rej.");
        n_rej.setBounds(20, 50, 100, 20);
        n_rej_input.setBounds(125, 50, 200, 20);

        owner.setFont(mainFont);
        owner.setForeground(Color.WHITE);
        owner.setText("Właściciel");
        owner.setBounds(20, 100, 100, 20);
        owner_input.setBounds(125, 100, 200, 20);

        miej_zacumowania.setFont(mainFont);
        miej_zacumowania.setForeground(Color.WHITE);
        miej_zacumowania.setText("Miejsce Zacumowania");
        miej_zacumowania.setBounds(20, 150, 200, 20);
        miejsce_zacumowania_select.setBounds(225, 150, 200, 20);

        wybor_daty.setFont(mainFont);
        wybor_daty.setForeground(Color.WHITE);
        wybor_daty.setText("Data");
        wybor_daty.setBounds(20, 200, 200, 20);
        wybor_daty_input.setBounds(125, 200, 200, 20);

        karta_czlonkowstwa.setFont(mainFont);
        karta_czlonkowstwa.setForeground(Color.WHITE);
        karta_czlonkowstwa.setText("Karta członkowstwa");
        karta_czlonkowstwa.setBounds(20, 250, 200, 20);

        karta_czlonkowstwa_group.add(karta_czlonkowstwa_radio1);
        karta_czlonkowstwa_group.add(karta_czlonkowstwa_radio2);

        karta_czlonkowstwa_radio1.setText("Ma");
        karta_czlonkowstwa_radio1.setBounds(200, 250, 50, 20);
        karta_czlonkowstwa_radio2.setText("Nie ma");
        karta_czlonkowstwa_radio2.setBounds(250, 250, 75, 20);
        karta_czlonkowstwa_radio2.setSelected(true);

        zapisz.setBounds(150, 350, 100, 20);
        wyswietl.setBounds(350, 350, 150, 20);

        bgPanel.add(tytol);
        bgPanel.add(zapisz);
        bgPanel.add(wyswietl);
        bgPanel.add(karta_czlonkowstwa);
        bgPanel.add(karta_czlonkowstwa_radio1);
        bgPanel.add(karta_czlonkowstwa_radio2);
        bgPanel.add(wybor_daty);
        bgPanel.add(wybor_daty_input);
        bgPanel.add(miej_zacumowania);
        bgPanel.add(miejsce_zacumowania_select);
        bgPanel.add(owner);
        bgPanel.add(owner_input);
        bgPanel.add(n_rej_input);
        bgPanel.add(n_rej);









        frame.add(bgPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private static void loadData() throws SQLException {
        frame.remove(bgPanel);
        frame.add(dataPanel);
        frame.revalidate();
        frame.repaint();

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rejestr_lodzi", "root", "");

        String sql = "select * from port";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet resultSet = pstmt.executeQuery();

        People.clear();

        while (resultSet.next()){
            Person person = new Person();

            person.Id = resultSet.getInt("Id");
            person.Nr_rej = resultSet.getString("Nr_rej");
            person.Wlasciciel = resultSet.getString("Wlasciciel");
            person.Miejsce_zacumowania = resultSet.getString("Miejsce_zacumowania");
            person.Data = resultSet.getDate("Data");
            person.Karta_czlonkowstwa = resultSet.getBoolean("Karta_czloknowstwa");

            People.add(person);
        }
        resultSet.close();
        pstmt.close();
        conn.close();


        PersonTableModel model = new PersonTableModel(People);

        dataTable = new JTable();

        dataTable.setModel(model);
        dataTable.setRowHeight(50);

        JScrollPane scrollPane = new JScrollPane(dataTable);

        scrollPane.setBounds(0, 0, frame.getWidth(), frame.getHeight() - 100);

        dataPanel.add(scrollPane);
        frame.revalidate();
        frame.repaint();
    }

    private static void sendData(String rejestracja, String Wlasciciel, String MiejsceZacumowania, Date Data, Boolean KartaCzlonkowstwa){
        if(!n_rej_input.getText().isEmpty() && !owner_input.getText().isEmpty() && wybor_daty_input.getDate() != null && KartaCzlonkowstwa != null) {

            if(n_rej_input.getText().length() != 10 || n_rej_input.getText().charAt(3) != '-'){
                JOptionPane.showMessageDialog(null, "Nieprawidłowy numer rejestracyjny", "Rejestracja Łodzi", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {

                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rejestr_lodzi", "root", "");

                String sql = "insert into port(port.Nr_rej, port.Wlasciciel, port.Miejsce_zacumowania, port.Data, port.Karta_czloknowstwa) values(?,?,?,?,?)";

                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, rejestracja);
                pstmt.setString(2, Wlasciciel);
                pstmt.setString(3, MiejsceZacumowania);
                pstmt.setDate(4, Data);
                pstmt.setBoolean(5, KartaCzlonkowstwa);

                int rowsInserted = pstmt.executeUpdate();

                if(rowsInserted > 0){
                    JOptionPane.showMessageDialog(null, "Pomyślnie zarejestrowano łódź", "Rejestracja Łodzi", JOptionPane.INFORMATION_MESSAGE);
                }

                Person person = new Person();
                person.Nr_rej = rejestracja;
                person.Wlasciciel = Wlasciciel;
                person.Miejsce_zacumowania = MiejsceZacumowania;
                person.Data = Data;
                person.Karta_czlonkowstwa = KartaCzlonkowstwa;

                People.add(person);

                pstmt.close();
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Wystąpił błąd podczas rejestracji", "Rejestracja Łodzi", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Wszystkie pola są wymagane do rejestracji", "Rejestracja Łodzi", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static void removeData(Object value, int rowNumber){

        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rejestr_lodzi", "root", "");

            String sql = "delete from port where Id = " + rowNumber;

            System.out.println(sql);

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.executeUpdate();


            pstmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Pomyślnie usunięto rejestr", "Rejestracja Łodzi", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException EX){
            JOptionPane.showMessageDialog(null, "Wystąpił błąd podczas usuwania rejestru", "Rejestracja Łodzi", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void main(String[] args) {
        new rejestr();

        zapisz.addActionListener(e -> {
            Boolean radio_value = Boolean.FALSE;
            for(Enumeration<AbstractButton> buttons = karta_czlonkowstwa_group.getElements(); buttons.hasMoreElements();){
                AbstractButton buttonInGroup = buttons.nextElement();
                if (buttonInGroup.isSelected()) {
                    System.out.println(buttonInGroup.getText());
                    radio_value = buttonInGroup.getText() == "Ma" ? Boolean.TRUE : Boolean.FALSE;
                    break;
                }
            }


            Boolean finalRadio_value = radio_value;

            sendData(n_rej_input.getText(), owner_input.getText(), miejsce_zacumowania_select.getSelectedItem().toString(), wybor_daty_input.getDate() != null ? new java.sql.Date(wybor_daty_input.getDate().getTime()) : null, finalRadio_value);
        });

        wyswietl.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                try {
                    loadData();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
        });

        Delete.addActionListener(e -> {
            if(dataTable.getSelectionModel().isSelectionEmpty()){
                System.out.println("Not Selected");
            }else{
                int index = dataTable.getSelectedRow();
                if(index >= 0){
                    Object value = dataTable.getValueAt(index, 0);
                    System.out.println(value);
                    removeData(value, (Integer) value);
                    ((PersonTableModel) dataTable.getModel()).removeRow(index);
                }

            }
        });

        Back.addActionListener(e -> {
            frame.remove(dataPanel);
            frame.add(bgPanel);
            frame.revalidate();
            frame.repaint();
        });
    }


}
