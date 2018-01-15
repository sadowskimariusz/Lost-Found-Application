package com.lostandfoundapp.service;

import com.lostandfoundapp.dao.lostitemoperations.LostItemDAO;
import com.lostandfoundapp.parsers.common.Parser;
import com.lostandfoundapp.parsers.cracow.ParserCracow;
import com.lostandfoundapp.parsers.gdansk.ParserGdansk;
import com.lostandfoundapp.parsers.item.Item;
import com.lostandfoundapp.parsers.warsaw.ParserWarsaw;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {

    @InjectMocks
    Service service;

    @Mock
    ParserCracow cracow;

    @Mock
    ParserGdansk gdansk;

    @Mock
    ParserWarsaw warsaw;

    @Before
    public void setUp() throws Exception {

        List<Item> fakeList = new ArrayList<>();
        Item fake = new Item();
        fakeList.add(fake);

        when(cracow.getParsedData()).thenReturn(fakeList);
        when(gdansk.getParsedData()).thenReturn(fakeList);
        when(warsaw.getParsedData()).thenReturn(fakeList);

        List<Parser> parsers = new ArrayList<>();

        parsers.add(cracow);
        parsers.add(gdansk);
        parsers.add(warsaw);


        service = new Service(parsers);

        service.downloadData();
    }

    @Test
    public void downloadDataSizeTest() throws Exception {
        assertEquals(3, service.getAllItems());
    }

}