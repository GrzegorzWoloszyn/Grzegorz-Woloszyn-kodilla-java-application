package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoard() {
        //Given
        List<TrelloListDto> trelloList = Arrays.asList(new TrelloListDto("1", "trello list dto", false));
        List<TrelloBoardDto> boardDtoList = Arrays.asList(new TrelloBoardDto("1", "board_dto", trelloList));

        //When
        List<TrelloBoard> mappedList = trelloMapper.mapToBoard(boardDtoList);

        //Then
        assertThat(mappedList)
                .extracting(e -> e.getId(), e -> e.getName())
                .contains(tuple("1", "board_dto"));
    }

    @Test
    public void testMapToBoardDto() {
        //Given
        List<TrelloList> trelloLists = Arrays.asList(new TrelloList("1", "trello list dto", true));
        List<TrelloBoard> trelloBoards = Arrays.asList(new TrelloBoard("2", "trello board", trelloLists));

        //When
        List<TrelloBoardDto> mappedList = trelloMapper.mapToBoardDto(trelloBoards);

        //Then
        assertThat(mappedList)
                .extracting(e -> e.getId(), e -> e.getName())
                .containsOnly(tuple("2", "trello board"));
        assertThat(mappedList.contains(trelloLists)).isEqualTo(trelloBoards.contains(trelloLists));
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloList = Arrays.asList(new TrelloListDto("1", "trello list dto", false));

        //When
        List<TrelloList> mappedList = trelloMapper.mapToList(trelloList);

        //Then
        assertThat(mappedList)
                .extracting(e -> e.getId(), e -> e.getName(), e -> e.isClosed())
                .containsOnly(tuple("1", "trello list dto", false));
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloLists = Arrays.asList(new TrelloList("1", "trello list dto", true));

        //When
        List<TrelloListDto> mappedList = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertThat(mappedList)
                .extracting(e -> e.getId(), e -> e.getName(), e -> e.isClosed())
                .containsOnly(
                        tuple("1", "trello list dto", true));
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card","trello card", "pos1", "cardId");

        //When
        TrelloCardDto mappedCard = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertThat(mappedCard)
                .extracting(e -> e.getName(), e -> e.getDescription(), e -> e.getPos(), e -> e.getListId())
                .containsOnly("card", "trello card", "pos1", "cardId");
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card2", "trello card dto", "pos2", "cardId2");

        //When
        TrelloCard mappedCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertThat(mappedCard)
                .extracting(e -> e.getName(), e -> e.getDescription(), e -> e.getPos(), e -> e.getListId())
                .containsOnly("card2", "trello card dto", "pos2", "cardId2");
    }
}
