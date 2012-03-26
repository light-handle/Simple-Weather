package org.sonatype.mavenbook.weather.yahoo;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import org.sonatype.mavenbook.weather.Weather;
import org.sonatype.mavenbook.weather.WeatherFormatter;
import org.sonatype.mavenbook.weather.YahooParser;


import junit.framework.TestCase;

public class WeatherFormatterTest extends TestCase
{ 
  public WeatherFormatterTest(String name)
  {
    super(name);
  }
  
  public void testFormat() throws Exception
  {
    InputStream nyData = getClass().getClassLoader().getResourceAsStream("ny-weather.xml");
    
    Weather weather = new YahooParser().parse(nyData);
    String formattedResult = new WeatherFormatter().format(weather);
    InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("format-expected.dat");
    String expected = IOUtils.toString(expectedStream);
    assertEquals(expected.trim(), formattedResult.trim());
  }
}
