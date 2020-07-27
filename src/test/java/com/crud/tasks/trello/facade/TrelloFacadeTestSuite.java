package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoard() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "trello list dto", false);
        List<TrelloListDto> trelloList = new ArrayList<>();
        trelloList.add(trelloListDto);

        TrelloBoardDto trelloBoard = new TrelloBoardDto("board_dto", "1", trelloList);
        List<TrelloBoardDto> boardDtoList = new ArrayList<>();
        boardDtoList.add(trelloBoard);

        //When
        List<TrelloBoard> mappedList = trelloMapper.mapToBoard(boardDtoList);

        //Then
        assertEquals(boardDtoList.get(0).getName(), mappedList.get(0).getName());
        assertEquals(boardDtoList.get(0).getId(), mappedList.get(0).getId());
        assertEquals(boardDtoList.get(0).getLists().get(0).isClosed(), mappedList.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToBoardDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "trello list dto", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("2", "trello board", trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);

        //When
        List<TrelloBoardDto> mappedList = trelloMapper.mapToBoardDto(trelloBoards);

        //Then
        Assert.assertEquals(trelloBoards.get(0).getId(),mappedList.get(0).getId());
        Assert.assertEquals(trelloBoards.get(0).getName(),mappedList.get(0).getName());
        Assert.assertEquals(trelloBoards.get(0).getLists().get(0).isClosed(),mappedList.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "trello list dto", false);
        List<TrelloListDto> trelloList = new ArrayList<>();
        trelloList.add(trelloListDto);

        //When
        List<TrelloList> mappedList = trelloMapper.mapToList(trelloList);

        //Then
        Assert.assertEquals(trelloList.get(0).getId(), mappedList.get(0).getId());
        Assert.assertEquals(trelloList.get(0).getName(), mappedList.get(0).getName());
        Assert.assertEquals(trelloList.get(0).isClosed(), mappedList.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "trello list dto", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        //When
        List<TrelloListDto> mappedList = trelloMapper.mapToListDto(trelloLists);

        //Then
        Assert.assertEquals(trelloLists.get(0).getId(), mappedList.get(0).getId());
        Assert.assertEquals(trelloLists.get(0).getName(), mappedList.get(0).getName());
        Assert.assertEquals(trelloLists.get(0).isClosed(), mappedList.get(0).isClosed());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card", "trello card", "pos1", "cardId");

        //When
        TrelloCardDto mappedCard = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals(trelloCard.getDescription(), mappedCard.getDescription());
        Assert.assertEquals(trelloCard.getName(), mappedCard.getName());
        Assert.assertEquals(trelloCard.getPos(), mappedCard.getPos());
        Assert.assertEquals(trelloCard.getListId(), mappedCard.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card2", "trello card dto", "pos2", "cardId2");

        //When
        TrelloCard mappedCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals(trelloCardDto.getDescription(), mappedCard.getDescription());
        Assert.assertEquals(trelloCardDto.getListId(), mappedCard.getListId());
        Assert.assertEquals(trelloCardDto.getName(), mappedCard.getName());
        Assert.assertEquals(trelloCardDto.getPos(), mappedCard.getPos());
    }
}
