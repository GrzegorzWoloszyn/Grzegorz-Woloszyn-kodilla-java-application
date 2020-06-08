package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrelloBoardDto {
    private String name;
    private String id;

    @Override
    public String toString() {
        return "TrelloBoardDto: " +
                "name ='" + name + '\'' +
                ", id ='" + id + '\'';
    }

}
