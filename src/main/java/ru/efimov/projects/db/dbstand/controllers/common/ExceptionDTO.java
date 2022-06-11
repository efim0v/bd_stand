package ru.efimov.projects.db.dbstand.controllers.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExceptionDTO {
    private String message;
    private String url;
}
