package com.example.dto;

import com.example.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class GenerateScratchCardsResponseDTO {

    private List<String> scratchCardGUIDs;

    private ResponseStatus responseStatus;

    private String failureMessage;

}
