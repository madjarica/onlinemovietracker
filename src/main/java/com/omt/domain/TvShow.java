package com.omt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tvshows")
public class TvShow extends Video {

    public TvShow() {
    }

    @Column
    @DateTimeFormat
    @JsonProperty("first_air_date")
    private Date pilotAirDate;

    @Column
    @DateTimeFormat
    @JsonProperty("last_air_date")
    private Date finalAirDate;

    public Date getPilotAirDate() {
        return pilotAirDate;
    }

    public void setPilotAirDate(Date pilotAirDate) {
        this.pilotAirDate = pilotAirDate;
    }

    public Date getFinalAirDate() {
        return finalAirDate;
    }

    public void setFinalAirDate(Date finalAirDate) {
        this.finalAirDate = finalAirDate;
    }
}
