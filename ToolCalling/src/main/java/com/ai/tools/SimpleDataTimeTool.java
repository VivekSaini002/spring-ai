package com.ai.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleDataTimeTool {

    private Logger logger = LoggerFactory.getLogger(SimpleDataTimeTool.class);

    @Tool(description = """
        Returns the current date and time.
        
        Always call this tool before setting an alarm if the user
        mentions relative dates such as
        
        today
        tomorrow
        yesterday
        this evening
        tonight
        next Monday
        """)
    public String getCurrentDataTime(){
        logger.info("Tool calling...");
        return LocalDateTime.now()
                .atZone(LocaleContextHolder.getTimeZone().toZoneId())
                .toString();
    }

    @Tool(description = """
        Sets an alarm.
        
        The time parameter must be in ISO-8601 format.
        
        If the user says
        
        today
        tomorrow
        next Monday
        
        first call getCurrentDateTime(),
        calculate the full ISO datetime,
        then call this tool.
        """)
    String setAlarm(@ToolParam(description = "Time in ISO-8601 format") String time){
        var dateTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        this.logger.info("Alarm is set {}", dateTime);
        return ("Alarm is set: "+ dateTime);
    }

}
