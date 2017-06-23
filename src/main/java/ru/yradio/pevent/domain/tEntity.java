package ru.yradio.pevent.domain;

/**
 *
 * @author kboenko
 */
public class tEntity {
    private Long id;
    private String date;
    private String callerid;
    private String prize;
    private String prize_taken;
    
    public tEntity (Long id, String date, String callerid, String prize, String prize_taken)
    {
        this.id=id;
        this.date=date;
        this.callerid=callerid;
        this.prize=prize;
        this.prize_taken=prize_taken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCallerid() {
        return callerid;
    }

    public void setCallerid(String callerid) {
        this.callerid = callerid;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getPrize_taken() {
        return prize_taken;
    }

    public void setPrize_taken(String prize_taken) {
        this.prize_taken = prize_taken;
    }
    
    @Override
    public String toString() {
        return "Id=" + this.id + ", date=" + this.date + ", callerid=" + this.callerid + ", prize=" + this.prize + ", prize_taken="+ prize_taken;
    }
    
}
