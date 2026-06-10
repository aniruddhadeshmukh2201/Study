import java.sql.Date;

public final class Immutable {
    private final Integer id;
    private final Date d;


    public Immutable(Integer id, Date d) {
        this.id = id;
        this.d = new Date(d.getTime());
        
    }


    public Integer getId(){
        return this.id;
    }

    public Date getDate() {
        return this.d;
    }

}