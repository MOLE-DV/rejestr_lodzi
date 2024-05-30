import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PersonTableModel extends AbstractTableModel {
    private final List<Person> people;
    private final String[] columnNames = {"Id", "Nr_rej", "Wlasciciel", "Miejsce_zacumowania", "Data", "Karta_czlonkowstwa"};

    public PersonTableModel(List<Person> people) {
        this.people = people;
    }

    @Override
    public int getRowCount() {
        return people.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person person = people.get(rowIndex);
        switch (columnIndex) {
            case 0: return person.Id;
            case 1: return person.Nr_rej;
            case 2: return person.Wlasciciel;
            case 3: return person.Miejsce_zacumowania;
            case 4: return person.Data;
            case 5: return person.Karta_czlonkowstwa;
            default: return null;
        }
    }

    public void addRow(Person person){
        people.add(person);
        fireTableRowsInserted(0, 0);
    }

    public void removeRow(int index) {
        people.remove(index);
        fireTableRowsDeleted(index, index);
    }
}
