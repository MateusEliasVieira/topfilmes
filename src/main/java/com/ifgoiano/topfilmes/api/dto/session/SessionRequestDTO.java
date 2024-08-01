package com.ifgoiano.topfilmes.api.dto.session;

import com.ifgoiano.topfilmes.api.dto.cinema.CinemaIDRequestDTO;
import com.ifgoiano.topfilmes.api.dto.user.UserIDRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionRequestDTO {

    private Long idSession;
    @NotNull
    private Date start;
    @NotNull
    private Date end;
    @NotNull
    private int tickets;
    @NotNull
    private int room;
    @NotNull
    private int codSession;

    @NotNull
    private UserIDRequestDTO user;

}
