package Twitter.Entities.Record;

import Twitter.Entities.User.Model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class RecordDto {
    private Long Id;
    @NotBlank(message = "Text can't be null or empty")
    private String Text;
    private Date date;
    private Twitter.Entities.User.Model.User User;
    public RecordDto() {
    }
    public RecordDto(Record record){
        this.Text = record.getText();
        this.date = record.getDate();
        this.User = record.getUser();
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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

    public String getUserLogin(){
        return User != null ? User.getLogin() : "<none>";
    }
    public void setText(String Text) {
        this.Text = Text;
    }
    public void setDate(Date RecordDate) {
        this.date = RecordDate;
    }
    public void setUser(User User) {
        this.User = User;
    }
}
