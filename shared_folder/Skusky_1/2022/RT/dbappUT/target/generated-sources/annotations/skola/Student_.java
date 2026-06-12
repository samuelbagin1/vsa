package skola;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import skola.Predmet;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-06-15T02:42:38")
@StaticMetamodel(Student.class)
public class Student_ { 

    public static volatile SetAttribute<Student, Predmet> predmety;
    public static volatile SingularAttribute<Student, String> meno;
    public static volatile SingularAttribute<Student, Long> id;
    public static volatile SingularAttribute<Student, Date> narodeny;

}