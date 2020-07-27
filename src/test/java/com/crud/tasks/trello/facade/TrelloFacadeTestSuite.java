package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuite {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloMapper trelloMapper;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloService trelloService;

    @Test
    public void testFetchEmptyList() {
        //Given
        List<TrelloListDto> trelloList = new ArrayList<>();
        trelloList.add(new TrelloListDto("1", "list1", false));

        List<TrelloBoardDto> trelloBoard = new ArrayList<>();
        trelloBoard.add(new TrelloBoardDto("1", "board_dto", trelloList));

        List<TrelloList> mappedTrelloList = new ArrayList<>();
        mappedTrelloList.add(new TrelloList("1", "list1", false));

        List<TrelloBoard> mappedTrelloBoard = new ArrayList<>();
        mappedTrelloBoard.add(new TrelloBoard("1","trello_dto", mappedTrelloList));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoard);
        when(trelloMapper.mapToBoard(trelloBoard)).thenReturn(mappedTrelloBoard);
        when(trelloMapper.mapToBoardDto(anyList())).thenReturn(trelloBoard);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoard)).thenReturn(mappedTrelloBoard);

        //When
        List<TrelloBoardDto> resultList = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(resultList);

    }
}
