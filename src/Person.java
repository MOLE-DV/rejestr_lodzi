import java.sql.Date;

class Person {
    int Id;
    String Nr_rej;
    String Wlasciciel;
    String Miejsce_zacumowania;
    Date Data;
    Boolean Karta_czlonkowstwa;

    @Override
    public String toString() {
        return "Person{" +
                "Id='" + Id + '\'' +
                ", Nr_rej='" + Nr_rej + '\'' +
                ", Wlasciciel='" + Wlasciciel + '\'' +
                ", Miejsce_zacumowania='" + Miejsce_zacumowania + '\'' +
                ", Data=" + Data +
                ", Karta_czlonkowstwa=" + Karta_czlonkowstwa +
                '}';
    }
}