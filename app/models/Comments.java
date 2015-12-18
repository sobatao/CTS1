package models;

import play.data.validation.Constraints;
import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Meaks on 12/9/2015.
 */

@Entity
public class Comments extends Model {

    @Constraints.Required
    public String User;
    public Date postedAt;

    @Lob
    public String content;

    @ManyToOne
    public Product post;

    public Comments (Product post, String User, String content) {
        this.post = post;
        this.User = User;
        this.content = content;
        this.postedAt = new Date();
    }

}


