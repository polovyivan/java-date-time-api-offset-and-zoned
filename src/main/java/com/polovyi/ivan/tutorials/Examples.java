package com.polovyi.ivan.tutorials;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class Examples {

    public static void main(String[] args) {

        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        // [Asia/Aden, America/Cuiaba, ...]
        System.out.println("availableZoneIds = " + availableZoneIds);

        int availableZoneQuantity = availableZoneIds.size();
        // 603
        System.out.println("availableZoneIds.size() = " + availableZoneQuantity);

        Map<String, String> shortIds = ZoneId.SHORT_IDS;
        // NST=Pacific/Auckland, HST=-10:00, ACT=Australia/Darwin ...
        System.out.println("shortIds = " + shortIds);
        String nst = ZoneId.SHORT_IDS.get("NST");
        // Pacific/Auckland
        System.out.println("nst = " + nst);

        // ==================================================================================

        ZoneId example1 = ZoneId.of("Europe/Kyiv");
        // Europe/Kyiv
        System.out.println("example1 = " + example1);

        Map<String, String> example2AliasMap = Map.of("UA", "Europe/Kyiv",
                "BR", "America/Cuiaba");
        ZoneId example2A = ZoneId.of("UA", example2AliasMap);
        // Europe/Kyiv
        System.out.println("example2A = " + example2A);

        ZoneId example2B = ZoneId.of("BR", example2AliasMap);
        // America/Cuiaba
        System.out.println("example2B = " + example2B);

        ZoneId example2C = ZoneId.of("America/Chicago", example2AliasMap);
        // America/Chicago
        System.out.println("example2C = " + example2C);

//        ZoneId example3 = ZoneId.of("MyZone");
//        // java.time.zone.ZoneRulesException: Unknown time-zone ID: MyZone
//        System.out.println("example3 = " + example3);

        ZoneId example4 = ZoneId.systemDefault();
        // returns system default time-zone
        System.out.println("example4 = " + example4);

        ZoneId example5 = ZoneId.from(ZonedDateTime.now(ZoneId.of("America/New_York")));
        // America/New_York
        System.out.println("example5 = " + example5);

        // ===========================================================================

        ZoneOffset example6 = ZoneOffset.MIN;
        // -18:00
        System.out.println("example6 = " + example6);

        ZoneOffset example7 = ZoneOffset.UTC;
        // Z
        System.out.println("example7 = " + example7);

        ZoneOffset example8 = ZoneOffset.MAX;
        // +18:00
        System.out.println("example8 = " + example8);

        ZoneOffset example9 = ZoneOffset.of("+2");
        // +02:00
        System.out.println("example9 = " + example9);

        // ZoneOffset example10 = ZoneOffset.of("+19");
        // java.time.DateTimeException: Zone offset hours not in valid range:
        // value 19 is not in the range -18 to 18
        // System.out.println("example10 = " + example10);

        ZoneOffset example11 = ZoneOffset.ofTotalSeconds(2 * 60 * 60);
        // +02:00
        System.out.println("example11 = " + example11);

        ZoneOffset example12 = ZoneOffset.ofHoursMinutes(1, 45);
        // +01:45
        System.out.println("example12 = " + example12);

        ZoneOffset example13 = ZoneOffset.ofHours(2);
        // +02:00
        System.out.println("example13 = " + example13);

        ZoneOffset example14 = ZoneOffset.ofHoursMinutesSeconds(2, 10, 30);
        // +02:10:30
        System.out.println("example14 = " + example14);

        ZoneId example15 = ZoneOffset.ofOffset("UTC", // GMT, UTC or UT
                ZoneOffset.ofHoursMinutes(1, 45));
        // UTC+01:45
        System.out.println("example15 = " + example15);

        // =====================================================================

        ZonedDateTime example16 = ZonedDateTime.of(2024, 9, 4, 7, 15,
                30, 50000, ZoneId.of("Europe/Kyiv"));
        // 2024-09-04T07:15:30.000050+03:00[Europe/Kyiv]
        System.out.println("example16 = " + example16);

        ZonedDateTime example17 = ZonedDateTime.of(LocalDate.of(2024, 9, 4),
                LocalTime.NOON, ZoneId.of("Europe/Kyiv"));
        // 2024-09-04T12:00+03:00[Europe/Kyiv]
        System.out.println("example17 = " + example17);

        ZonedDateTime example18 = ZonedDateTime.of(LocalDateTime.of(2024, 9, 4, 7, 10, 10),
                ZoneId.of("Europe/Kyiv"));
        // 2024-09-04T07:10:10+03:00[Europe/Kyiv]
        System.out.println("example18 = " + example18);

        ZonedDateTime example19 = ZonedDateTime.ofLocal(LocalDateTime.of(2024, 9, 4, 7, 10, 10),
                ZoneId.of("Europe/Kyiv"), ZoneOffset.of("+3"));
        // 2024-09-04T07:10:10+03:00[Europe/Kyiv]
        System.out.println("example19 = " + example19);

        ZonedDateTime example20 = ZonedDateTime.ofStrict(LocalDateTime.of(2024, 9, 4, 7, 10, 10),
                ZoneOffset.of("+3"), ZoneId.of("Europe/Kyiv"));
        // 2024-09-04T07:10:10+03:00[Europe/Kyiv]
        System.out.println("example20 = " + example20);

        // ZonedDateTime example21 = ZonedDateTime.ofStrict(LocalDateTime.of(2024, 9, 4, 7, 10, 10),
        //        ZoneOffset.of("+1"), ZoneId.of("Europe/Kyiv"));
        // java.time.DateTimeException ZoneOffset '+01:00' is not valid for LocalDateTime
        // '2024-09-04T07:10:10' in zone 'Europe/Kyiv'

        ZonedDateTime example22 = ZonedDateTime.now();
        // 2024-09-23T16:18:28.519514695-03:00[your time zone] (each run is different value)
        System.out.println("example22 = " + example22);

        ZonedDateTime example23 = ZonedDateTime.now(ZoneId.of("Europe/Kyiv"));
        // 2024-09-23T16:18:28.519514695-03:00[your time zone] (each run is different value)
        System.out.println("example22 = " + example23);

        ZonedDateTime example24 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1725494870000L),
                ZoneId.of("Europe/Kyiv"));
        // 2024-09-05T03:07:50+03:00[Europe/Kyiv]
        System.out.println("example24 = " + example24);

        ZonedDateTime example25 = ZonedDateTime.ofInstant(
                LocalDateTime.ofInstant(Instant.ofEpochMilli(1725494870000L), ZoneId.of("Europe/Kyiv")),
                ZoneOffset.ofHoursMinutes(1, 30),
                ZoneId.of("Europe/Kyiv"));
        // 2024-09-05T04:37:50+03:00[Europe/Kyiv]
        System.out.println("example25 = " + example25);

        // =====================================================================================================================

        ZonedDateTime example26 = ZonedDateTime.of(LocalDate.of(2024, 9, 4),
                LocalTime.NOON, ZoneId.of("Europe/Kyiv"));
        int example26Offset = example26.get(ChronoField.OFFSET_SECONDS) / (60 * 60);
        // 3 hours
        System.out.println("example26Offset = " + example26Offset);

        ZoneId example26Zone = example26.getZone();
        // Europe/Kyiv
        System.out.println("example26Zone = " + example26Zone);

        boolean example26Supported = example26.isSupported(ChronoField.HOUR_OF_DAY);
        // true
        System.out.println("example26Supported = " + example26Supported);

        long example26Long = example26.getLong(ChronoField.HOUR_OF_DAY);
        // 12
        System.out.println("example26Long = " + example26Long);

        TemporalUnit example26Queries = example26.query(TemporalQueries.precision());
        // Nanos
        System.out.println("example26Queries = " + example26Queries);

        // =====================================================================================================================

        ZonedDateTime example27 = ZonedDateTime.of(LocalDate.of(2024, 9, 4),
                LocalTime.NOON, ZoneId.of("Europe/Kyiv"));
        // 2024-09-04T12:00+03:00[Europe/Kyiv]
        System.out.println("example27 = " + example27);

        ZonedDateTime example27With = example27.with(ChronoField.HOUR_OF_DAY, 1);
        // 2024-09-04T01:00+03:00[Europe/Kyiv]
        System.out.println("example27With = " + example27With);

        ZonedDateTime example27WithHour = example27.withHour(1);
        // 2024-09-04T01:00+03:00[Europe/Kyiv]
        System.out.println("example27WithHour = " + example27WithHour);

        ZonedDateTime example27WithFixedOffsetZone = example27.withFixedOffsetZone();
        // 2024-09-04T12:00+03:00
        System.out.println("example27WithFixedOffsetZone = " + example27WithFixedOffsetZone);

        ZonedDateTime example27WithFixedOffsetZoneInstant = example27.withZoneSameInstant(ZoneId.of("America/Cuiaba"));
        // 2024-09-04T05:00-04:00[America/Cuiaba]
        System.out.println("example27WithFixedOffsetZone = " + example27WithFixedOffsetZoneInstant);

        ZonedDateTime example27WithZonedSameLocal = example27.withZoneSameLocal(ZoneId.of("America/Cuiaba"));
        // 2024-09-04T12:00-04:00[America/Cuiaba]
        System.out.println("example27WithZonedSameLocal = " + example27WithZonedSameLocal);

        ZonedDateTime example27WithEarlierOffsetAtOverlap = example25.withEarlierOffsetAtOverlap();
        //  2024-09-04T12:00+03:00[Europe/Kyiv]
        System.out.println("example27WithEarlierOffsetAtOverlap = " + example27WithEarlierOffsetAtOverlap);

        ZonedDateTime example27WithLaterOffsetAtOverlap = example27.withLaterOffsetAtOverlap();
        // 2024-09-04T12:00+03:00[Europe/Kyiv]
        System.out.println("example27WithLaterOffsetAtOverlap = " + example27WithLaterOffsetAtOverlap);

        // =====================================================================================================================

        ZonedDateTime example28 = ZonedDateTime.of(LocalDate.of(2024, 9, 4),
                LocalTime.NOON, ZoneId.of("Europe/Kyiv"));
        // 2024-09-04T12:00+03:00[Europe/Kyiv]
        System.out.println("example28 = " + example28);

        ZonedDateTime example28PlusDuration = example28.plus(Duration.ofHours(1));
        // 2024-09-04T13:00+03:00[Europe/Kyiv]
        System.out.println("example28PlusDuration = " + example28PlusDuration);

        ZonedDateTime example28PlusChronoUnit = example28.plus(1, ChronoUnit.HOURS);
        // 2024-09-04T13:00+03:00[Europe/Kyiv]
        System.out.println("example28PlusChronoUnit = " + example28PlusChronoUnit);

        ZonedDateTime example28PlusHour = example28.plusHours(1);
        // 2024-09-04T13:00+03:00[Europe/Kyiv]
        System.out.println("example28PlusHour = " + example28PlusHour);

        ZonedDateTime example28MinusDuration = example28.minus(Duration.ofHours(1));
        // 2024-09-04T11:00+03:00[Europe/Kyiv]
        System.out.println("example28MinusDuration = " + example28MinusDuration);

        ZonedDateTime example28MinusChronoUnit = example28.minus(1, ChronoUnit.HOURS);
        // 2024-09-04T11:00+03:00[Europe/Kyiv]
        System.out.println("example28MinusChronoUnit = " + example28MinusChronoUnit);

        ZonedDateTime example28MinusHour = example28.minusHours(1);
        // 2024-09-04T11:00+03:00[Europe/Kyiv]
        System.out.println("example28MinusHour = " + example28MinusHour);

        // =====================================================================================================================
        boolean example29 = ZonedDateTime.of(LocalDate.of(2024, 9, 4),
                        LocalTime.NOON, ZoneId.of("Europe/Kyiv"))
                .isBefore(ZonedDateTime.of(LocalDate.of(2024, 9, 4),
                        LocalTime.MAX, ZoneId.of("Europe/Kyiv")));
        // true
        System.out.println("example29 = " + example29);

        boolean example30 = ZonedDateTime.of(LocalDate.of(2024, 9, 4),
                        LocalTime.NOON, ZoneId.of("Europe/Kyiv"))
                .isBefore(ZonedDateTime.of(LocalDate.of(2024, 9, 4),
                        LocalTime.MAX, ZoneId.of("Europe/London")));
        // true
        System.out.println("example30 = " + example30);

        boolean example31 = ZonedDateTime.of(LocalDate.of(2024, 9, 4),
                        LocalTime.NOON, ZoneId.of("Europe/Kyiv"))
                .isBefore(ZonedDateTime.of(LocalDate.of(2024, 9, 4),
                        LocalTime.NOON, ZoneId.of("Asia/Tokyo")));
        // false
        System.out.println("example31 = " + example31);

        boolean example32 = ZonedDateTime.of(LocalDate.of(2024, 9, 4),
                        LocalTime.NOON, ZoneId.of("Europe/Kyiv"))
                .equals(ZonedDateTime.of(LocalDate.of(2024, 9, 4),
                        LocalTime.NOON, ZoneId.of("Europe/Kyiv")));
        // true
        System.out.println("example32 = " + example32);

        Optional<ZonedDateTime> example33 = Stream.of(
                ZonedDateTime.of(LocalDate.of(2024, 9, 1),
                        LocalTime.NOON, ZoneId.of("Europe/Kyiv")),
                ZonedDateTime.of(LocalDate.of(2024, 9, 2),
                        LocalTime.NOON, ZoneId.of("Europe/Kyiv")),
                ZonedDateTime.of(LocalDate.of(2024, 9, 3),
                        LocalTime.NOON, ZoneId.of("Europe/Kyiv"))
        ).max(Comparator.naturalOrder());
        // Optional[2024-09-03T12:00+03:00[Europe/Kyiv]]
        System.out.println("example33 = " + example33);

        List<ZonedDateTime> example34 = Stream.of(
                        ZonedDateTime.of(LocalDate.of(2024, 9, 1),
                                LocalTime.NOON, ZoneId.of("Europe/Kyiv")),
                        ZonedDateTime.of(LocalDate.of(2024, 9, 3),
                                LocalTime.NOON, ZoneId.of("Europe/Kyiv")),
                        ZonedDateTime.of(LocalDate.of(2024, 9, 2),
                                LocalTime.NOON, ZoneId.of("Europe/Kyiv"))
                )
                .sorted()
                .toList();
        // [2024-09-01T12:00+03:00[Europe/Kyiv],
        // 2024-09-02T12:00+03:00[Europe/Kyiv],
        // 2024-09-03T12:00+03:00[Europe/Kyiv]]
        System.out.println("example34 = " + example34);

        // =====================================================================================================================

        OffsetTime example35 = OffsetTime.MIN;
        // 00:00+18:00
        System.out.println("example35 = " + example35);
        OffsetTime example36 = OffsetTime.MAX;
        // 23:59:59.999999999-18:00
        System.out.println("example36 = " + example36);

        OffsetTime example37 = OffsetTime.of(10, 15, 30, 15, ZoneOffset.UTC);
        // 10:15:30.000000015Z
        System.out.println("example37 = " + example37);

        OffsetTime example38 = OffsetTime.of(10, 15, 30, 15, ZoneOffset.MIN);
        // 10:15:30.000000015-18:00
        System.out.println("example38 = " + example38);

        OffsetTime example39 = OffsetTime.ofInstant(Instant.ofEpochMilli(1725494870000L), ZoneId.of("Europe/Kyiv"));
        // 03:07:50+03:00
        System.out.println("example39 = " + example39);

        OffsetTime example40 = OffsetTime.now(ZoneId.of("Europe/Kyiv"));
        // 23:16:38.155048587+03:00 (each run is different value)
        System.out.println("example40 = " + example40);

        // =====================================================================================================================

        OffsetDateTime example41 = OffsetDateTime.MIN;
        // -999999999-01-01T00:00+18:00
        System.out.println("example41 = " + example41);

        OffsetDateTime example42 = OffsetDateTime.MAX;
        // +999999999-12-31T23:59:59.999999999-18:00
        System.out.println("example42 = " + example42);

        OffsetDateTime example43 = OffsetDateTime.of(LocalDate.of(2024, 9, 4),
                LocalTime.NOON, ZoneOffset.UTC);
        // 2024-09-04T12:00Z
        System.out.println("example43 = " + example43);

        OffsetDateTime example44 = OffsetDateTime.of(2024, 9, 4, 12, 15,
                59, 100, ZoneOffset.UTC);
        // 2024-09-04T12:15:59.000000100Z
        System.out.println("example44 = " + example44);

        OffsetDateTime example45 = OffsetDateTime.of(LocalDateTime.of(LocalDate.of(2024, 9, 4), LocalTime.NOON),
                ZoneOffset.UTC);
        // 2024-09-04T12:00Z
        System.out.println("example45 = " + example45);

        OffsetDateTime example46 = OffsetDateTime.now();
        // 2024-09-24T10:35:30.315164584 your offset
        System.out.println("example46 = " + example46);

        OffsetDateTime example47 = OffsetDateTime.now(ZoneId.of("Europe/Kyiv"));
        // 2024-09-24T16:36:45.261935297+03:00
        System.out.println("example47 = " + example47);

        OffsetDateTime example50 = OffsetDateTime.ofInstant(Instant.ofEpochMilli(1725494870000L),
                ZoneId.of("Europe/Kyiv"));
        // 2024-09-05T03:07:50+03:00
        System.out.println("example50 = " + example50);

        // ====================================================================================

        boolean example51 = OffsetTime.now(ZoneId.of("Europe/Kyiv"))
                .isBefore(OffsetTime.now(ZoneId.of("Europe/London")));
        // true
        System.out.println("example51 = " + example51);

        boolean example52 = OffsetTime.now(ZoneId.of("Europe/Kyiv"))
                .isAfter(OffsetTime.now(ZoneId.of("Europe/London")));
        // false
        System.out.println("example52 = " + example52);

        boolean example53 = OffsetTime.now(ZoneId.of("Europe/Kyiv"))
                .equals(OffsetTime.now(ZoneId.of("Europe/London")));
        // false
        System.out.println("example53 = " + example53);

        boolean example54 = OffsetDateTime.now(ZoneId.of("Europe/Kyiv"))
                .isBefore(OffsetDateTime.now(ZoneId.of("Europe/London")));
        // true
        System.out.println("example54 = " + example54);

        boolean example55 = OffsetDateTime.now(ZoneId.of("Europe/Kyiv"))
                .isAfter(OffsetDateTime.now(ZoneId.of("Europe/London")));
        // false
        System.out.println("example55 = " + example55);

        boolean example56 = OffsetDateTime.now(ZoneId.of("Europe/Kyiv"))
                .equals(OffsetDateTime.now(ZoneId.of("Europe/London")));
        // false
        System.out.println("example56 = " + example56);

        List<OffsetTime> example57 = Stream.of(OffsetTime.now(ZoneId.of("Europe/Kyiv")),
                        OffsetTime.now(ZoneId.of("Europe/London")))
                .sorted()
                .toList();
        // [17:09:35.817276338+03:00, 15:09:35.817291413+01:00]
        System.out.println("example57 = " + example57);

        List<OffsetDateTime> example58 = Stream.of(OffsetDateTime.now(ZoneId.of("Europe/Kyiv")),
                        OffsetDateTime.now(ZoneId.of("Europe/London")))
                .sorted()
                .toList();
        // [2024-09-24T17:10:31.580483809+03:00, 2024-09-24T15:10:31.580489594+01:00]
        System.out.println("example58 = " + example58);

    }
}