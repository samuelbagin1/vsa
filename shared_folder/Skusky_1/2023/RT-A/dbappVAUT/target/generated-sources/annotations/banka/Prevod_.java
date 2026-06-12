package banka;

import banka.Ucet;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-06-15T02:06:45")
@StaticMetamodel(Prevod.class)
public class Prevod_ { 

    public static volatile SingularAttribute<Prevod, Double> suma;
    public static volatile SingularAttribute<Prevod, Ucet> ucetZ;
    public static volatile SingularAttribute<Prevod, Ucet> ucetNa;
    public static volatile SingularAttribute<Prevod, Date> realizovany;
    public static volatile SingularAttribute<Prevod, Long> id;

}