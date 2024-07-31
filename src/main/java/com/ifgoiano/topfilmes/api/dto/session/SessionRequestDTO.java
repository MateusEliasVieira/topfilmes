package com.ifgoiano.topfilmes.api.dto.session;

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
    private Date time;
    @NotNull
    private int tickets;
    @NotNull
    private int room;
    @NotNull
    private int codSession;

}
