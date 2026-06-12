package dbapp;

import dbapp.Odbor;
import dbapp.Osoba;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-07T10:19:51")
@StaticMetamodel(Predmet.class)
public class Predmet_ { 

    public static volatile SingularAttribute<Predmet, String> kod;
    public static volatile SingularAttribute<Predmet, Odbor> odbor;
    public static volatile SingularAttribute<Predmet, Osoba> prednasajuci;

}