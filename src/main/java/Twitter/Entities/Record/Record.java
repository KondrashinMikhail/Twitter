package Twitter.Entities.Record;

import Twitter.Entities.User.Model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column
    @NotBlank(message = "Text can't be null or empty")
    private String Text;
    @Column
    private Date date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User User;
    public Record() {
    }
    public Record(String Text, Date RecordDate, User User) {
        this.Text = Text;
        this.date = RecordDate;
        this.User = User;
    }
    public Long getId() {
        return Id;
    }
    public String getText() {
        return Text;
    }
    public Date getDate() {
        return date;
    }
    public User getUser(){
        return User;
    }

    public void setText(String Text) {
        this.Text = Text;
    }
    public void setDate(Date RecordDate) {
        this.date = RecordDate;
    }
    public void setUser(User User){
        this.User = User;
    }
}
