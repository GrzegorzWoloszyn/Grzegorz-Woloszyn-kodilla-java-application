package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloFacade {

    private final static Logger LOGGER = LoggerFactory.getLogger(TrelloFacade.class);

    @Autowired
    private TrelloService trelloService;
    @Autowired
    private TrelloMapper trelloMapper;
    @Autowired
    private TrelloValidator trelloValidator;

    public List<TrelloBoardDto> fetchTrelloBoards() {

        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoard(trelloService.fetchTrelloBoards());
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);

        return trelloMapper.mapToBoardDto(filteredBoards);
    }

    public CreatedTrelloCardDto createCard(final TrelloCardDto trelloCardDto) {
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        if(trelloCard.getName().contains("test")) {
            LOGGER.info("Someone ist testing my app.");
        } else {
            LOGGER.info("Seems that my app is used in proper way.");
        }
        return trelloService.creatTrelloCard(trelloMapper.mapToCardDto(trelloCard));
    }
}
