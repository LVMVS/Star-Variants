package com.wdiscute.starcatcher.compat;

import com.wdiscute.starcatcher.storage.FishProperties;
import com.wdiscute.starcatcher.storage.FishProperties.WorldRestrictions.Seasons;
import net.dries007.tfc.util.calendar.Calendars;
import net.dries007.tfc.util.calendar.Month;
import net.dries007.tfc.util.calendar.Season;
import net.minecraft.world.level.Level;

public class TerraFirmaCraftSeasonsCompat
{

    public static boolean canCatch(FishProperties fp, Level level)
    {
        if (!fp.wr().seasons().contains(Seasons.ALL))
        {
            return fp.wr().seasons().contains(getSeason(level)) || fp.wr().seasons().contains(getSubSeason(level));
        }
        return true;
    }

    public static Seasons getSeason(Level level)
    {
        Season season;
        if (level.isClientSide)
            season = Calendars.CLIENT.getAbsoluteCalendarMonthOfYear().getSeason();
        else
            season = Calendars.SERVER.getAbsoluteCalendarMonthOfYear().getSeason();

        return switch (season)
        {
            case SPRING -> Seasons.SPRING;
            case SUMMER -> Seasons.SUMMER;
            case FALL -> Seasons.AUTUMN;
            default -> Seasons.WINTER;
        };
    }

    public static Seasons getSubSeason(Level level)
    {
        Month month;
        if (level.isClientSide)
            month = Calendars.CLIENT.getAbsoluteCalendarMonthOfYear();
        else
            month = Calendars.SERVER.getAbsoluteCalendarMonthOfYear();

        return switch (month)
        {
            case JANUARY -> Seasons.MID_WINTER;
            case FEBRUARY -> Seasons.LATE_WINTER;
            case MARCH -> Seasons.EARLY_SPRING;
            case APRIL -> Seasons.MID_SPRING;
            case MAY -> Seasons.LATE_SPRING;
            case JUNE -> Seasons.EARLY_SUMMER;
            case JULY -> Seasons.MID_SUMMER;
            case AUGUST -> Seasons.LATE_SUMMER;
            case SEPTEMBER -> Seasons.EARLY_AUTUMN;
            case OCTOBER -> Seasons.MID_AUTUMN;
            case NOVEMBER -> Seasons.LATE_AUTUMN;
            case DECEMBER -> Seasons.EARLY_WINTER;
        };
    }

}
