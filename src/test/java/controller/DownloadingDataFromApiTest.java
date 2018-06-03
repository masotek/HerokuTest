package controller;

import model.Point;
import org.junit.Test;

import javax.inject.Inject;
import javax.validation.constraints.AssertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class DownloadingDataFromApiTest {
@Inject
DownloadingDataFromApi downloadingDataFromApi;

//    @Test
//    public void checkDownloadDataOfCurrency() throws IOException {
//        int size = downloadingDataFromApi.downloadDataOfCurrency("BTCUSD").size();
//        int shouldBeMoreThan = 1000;
//        assertTrue(size>shouldBeMoreThan);
//    }

//    @Test
//    public void checkGrowthOrDrop() {
//Point mockedPoint1 = mock(Point.class);
//Point mockedPoint2 = mock(Point.class);
//mockedPoint1.setTime("2016,09,17");
//mockedPoint1.setAverage("10");
//mockedPoint1.setTime("2016,09,24");
//mockedPoint1.setAverage("20");
//List l = new ArrayList();
//l.add(mockedPoint1);
//l.add(mockedPoint2);
//
//        List[] list= downloadingDataFromApi.growthOrDrop(l);
//        assertEquals(list.length, 2);
//    }

//    @Test
//    public void checkListOfTheSameTrend() {
//
//        assertEquals(downloadingDataFromApi.listOfTheSameTrend(new List[]{}, new List[]{}, new List[]{}).length,2 );
//    }
}
