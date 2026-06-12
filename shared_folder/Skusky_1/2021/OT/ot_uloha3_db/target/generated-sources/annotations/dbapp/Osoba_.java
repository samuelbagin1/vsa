package dbapp;

import dbapp.Predmet;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-07T10:19:51")
@StaticMetamodel(Osoba.class)
public class Osoba_ { 

    public static volatile ListAttribute<Osoba, Predmet> prednasky;
    public static volatile SingularAttribute<Osoba, String> meno;
    public static volatile SingularAttribute<Osoba, Long> id;

}