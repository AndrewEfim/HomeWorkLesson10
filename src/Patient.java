import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Patient implements Serializable {
    private String name;
    private String lastName;
    private String birth;
    private boolean isSick;

    public Patient(String name, String lastName, String birth, boolean isSick) {
        this.name = name;
        this.lastName = lastName;
        this.birth = birth;
        this.isSick = isSick;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass( ) != o.getClass( )) return false;

        Patient patient = (Patient) o;

        if (!name.equals(patient.name)) return false;
        if (!lastName.equals(patient.lastName)) return false;
        return birth.equals(patient.birth);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode( );
        result = 31 * result + lastName.hashCode( );
        result = 31 * result + birth.hashCode( );
        return result;
    }

    public String getName() {
        return this.name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getBirth() {
        return this.birth;
    }

    public boolean isSick() {
        return this.isSick;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birth='" + birth + '\'' +
                ", isSick=" + isSick +
                '}';
    }
}

