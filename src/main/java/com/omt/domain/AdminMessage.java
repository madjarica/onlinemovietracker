package com.omt.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "admin_messages")
public class AdminMessage extends BaseEntity{

    @NotNull
    @Column(nullable = false)
    private String type;

    @NotNull
    @Column
    @DateTimeFormat
    private Date createdDate;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String message;

    @NotNull
    @Column
    private boolean read;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "admin_message_id"),
    inverseJoinColumns = @JoinColumn(name = "watchlist_id"))
    private Set<Watchlist> watchlist;
    
    /*
    private List<Comment> comments;
    private Watchlist watchlist;
    */

    
    public AdminMessage(String type, Date createdDate, String message, boolean read, Set<Watchlist> watchlist) {
		super();
		this.type = type;
		this.createdDate = createdDate;
		this.message = message;
		this.read = read;
		this.watchlist = watchlist;
	}


	public String getType() {
        return type;
    }

	public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }


	public Set<Watchlist> getWatchlist() {
		return watchlist;
	}


	public void setWatchlist(Set<Watchlist> watchlist) {
		this.watchlist = watchlist;
	}

}
