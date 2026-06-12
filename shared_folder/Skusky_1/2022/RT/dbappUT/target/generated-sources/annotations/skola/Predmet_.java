package skola;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import skola.Odbor;
import skola.Student;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-06-15T02:42:38")
@StaticMetamodel(Predmet.class)
public class Predmet_ { 

    public static volatile SingularAttribute<Predmet, String> kod;
    public static volatile SingularAttribute<Predmet, Odbor> odbor;
    public static volatile SetAttribute<Predmet, Student> studenti;

}