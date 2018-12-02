import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Method to read and create the list of talk
        boolean hasAnyValidTalkTitle = ReadInputAndCreateTalkList();

        if (hasAnyValidTalkTitle) {
            // Method to schedule the meeting
            ScheduleTalkTitles();
        }
        // Method to print and add the final out to Output.txt file
        PrintTrackSchedule();
    }

    // Method to create talk list by hours
    private static boolean ReadInputAndCreateTalkList() {

        boolean hasAnyValidTalkTitle = false;

        TalkListByHours talkListByHours = new TalkListByHours();
        List<TalkDetails> sevenHourTalkList = new ArrayList<>();
        List<TalkDetails> sixHourTalkList = new ArrayList<>();
        List<TalkDetails> fiveHourTalkList = new ArrayList<>();
        List<TalkDetails> fourHourTalkList = new ArrayList<>();
        List<TalkDetails> threeHourTalkList = new ArrayList<>();
        List<TalkDetails> twoHourTalkList = new ArrayList<>();
        List<TalkDetails> oneHourTalkList = new ArrayList<>();
        List<String> invalidTalkList = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(new FileReader("Input.txt"));
            String line;
            while ((line = in.readLine()) != null) {

                line = line.trim();

                if (!line.equals(null) && !line.equals("")) {

                    boolean isInvalid = false;

                    String time = line.substring(line.lastIndexOf(" ") + 1);

                    String talkTitle;

                    int index = line.lastIndexOf(" ");

                    if (index > 0) {
                        talkTitle = line.substring(0, index);
                    } else {
                        talkTitle = line;
                    }


                    int talkTime;

                    if (time.toUpperCase().equals("LIGHTNING")) {
                        talkTime = 5;

                        TalkDetails talkDetails = new TalkDetails();

                        talkDetails.setTalkTime(talkTime);
                        talkDetails.setTalkTitle(talkTitle);

                        oneHourTalkList.add(talkDetails);
                    } else if (time.substring(time.length() - 3).toUpperCase().equals("MIN")) {
                        String timeCheck = time.substring(0, time.length() - 3);

                        try {

                            talkTime = Integer.parseInt(timeCheck);

                            if (talkTime > 420 || talkTime <=0) {
                                isInvalid = true;
                            } else if (talkTime > 360 && talkTime <= 420) {
                                // Add talk details to seven hours list
                                TalkDetails talkDetails = new TalkDetails();

                                talkDetails.setTalkTime(talkTime);
                                talkDetails.setTalkTitle(talkTitle);

                                sevenHourTalkList.add(talkDetails);
                            } else if (talkTime > 300 && talkTime <= 360) {
                                // Add talk details to six hours list
                                TalkDetails talkDetails = new TalkDetails();

                                talkDetails.setTalkTime(talkTime);
                                talkDetails.setTalkTitle(talkTitle);

                                sixHourTalkList.add(talkDetails);
                            } else if (talkTime > 240 && talkTime <= 300) {
                                // Add talk details to five hours list
                                TalkDetails talkDetails = new TalkDetails();

                                talkDetails.setTalkTime(talkTime);
                                talkDetails.setTalkTitle(talkTitle);

                                fiveHourTalkList.add(talkDetails);
                            } else if (talkTime > 180 && talkTime <= 240) {
                                // Add talk details to four hours list
                                TalkDetails talkDetails = new TalkDetails();

                                talkDetails.setTalkTime(talkTime);
                                talkDetails.setTalkTitle(talkTitle);

                                fourHourTalkList.add(talkDetails);
                            } else if (talkTime > 120 && talkTime <= 180) {
                                // Add talk details to three hours list
                                TalkDetails talkDetails = new TalkDetails();

                                talkDetails.setTalkTime(talkTime);
                                talkDetails.setTalkTitle(talkTitle);

                                threeHourTalkList.add(talkDetails);
                            } else if (talkTime > 60 && talkTime <= 120) {
                                // Add talk details to two hours list
                                TalkDetails talkDetails = new TalkDetails();

                                talkDetails.setTalkTime(talkTime);
                                talkDetails.setTalkTitle(talkTitle);

                                twoHourTalkList.add(talkDetails);
                            } else if (talkTime > 0) {
                                // Add talk details to one hours list
                                TalkDetails talkDetails = new TalkDetails();

                                talkDetails.setTalkTime(talkTime);
                                talkDetails.setTalkTitle(talkTitle);

                                oneHourTalkList.add(talkDetails);
                            } else {
                                isInvalid = true;
                            }
                        } catch (Exception ex) {
                            isInvalid = true;
                        }

                    } else {
                        isInvalid = true;
                    }

                    if (isInvalid) {
                        // Add talk details to invalid talk list
                        invalidTalkList.add(line);

                    }
                }
            }
            in.close();

            if (sevenHourTalkList.size() > 0) {
                hasAnyValidTalkTitle = true;
                Collections.sort(sevenHourTalkList, new SortByTalkTime());
            }
            if (sixHourTalkList.size() > 0) {
                hasAnyValidTalkTitle = true;
                Collections.sort(sixHourTalkList, new SortByTalkTime());
            }
            if (fiveHourTalkList.size() > 0) {
                hasAnyValidTalkTitle = true;
                Collections.sort(fiveHourTalkList, new SortByTalkTime());
            }
            if (fourHourTalkList.size() > 0) {
                hasAnyValidTalkTitle = true;
                Collections.sort(fourHourTalkList, new SortByTalkTime());
            }
            if (threeHourTalkList.size() > 0) {
                hasAnyValidTalkTitle = true;
                Collections.sort(threeHourTalkList, new SortByTalkTime());
            }
            if (twoHourTalkList.size() > 0) {
                hasAnyValidTalkTitle = true;
                Collections.sort(twoHourTalkList, new SortByTalkTime());
            }
            if (oneHourTalkList.size() > 0) {
                hasAnyValidTalkTitle = true;
                Collections.sort(oneHourTalkList, new SortByTalkTime());
            }

            talkListByHours.setTalkListOf7Hours(sevenHourTalkList);
            talkListByHours.setTalkListOf6Hours(sixHourTalkList);
            talkListByHours.setTalkListOf5Hours(fiveHourTalkList);
            talkListByHours.setTalkListOf4Hours(fourHourTalkList);
            talkListByHours.setTalkListOf3Hours(threeHourTalkList);
            talkListByHours.setTalkListOf2Hours(twoHourTalkList);
            talkListByHours.setTalkListOf1Hour(oneHourTalkList);

            if (invalidTalkList.size() > 0) {
                PrintWriter writer = new PrintWriter("Invalid_Input.txt", "UTF-8");
                for (int i = 0; i < invalidTalkList.size(); i++) {
                    writer.println(invalidTalkList.get(i));
                }
                writer.close();
            }

        } catch (Exception ex) {
            System.out.println("Exception details: " + ex.getMessage());

        }

        return hasAnyValidTalkTitle;
    }

    // Method to print all the items in talk list
    private static void PrintItems() {
        TalkListByHours talkListByHours = new TalkListByHours();

        List<TalkDetails> sevenHourTalkList = talkListByHours.getTalkListOf7Hours();
        List<TalkDetails> sixHourTalkList = talkListByHours.getTalkListOf6Hours();
        List<TalkDetails> fiveHourTalkList = talkListByHours.getTalkListOf5Hours();
        List<TalkDetails> fourHourTalkList = talkListByHours.getTalkListOf4Hours();
        List<TalkDetails> threeHourTalkList = talkListByHours.getTalkListOf3Hours();
        List<TalkDetails> twoHourTalkList = talkListByHours.getTalkListOf2Hours();
        List<TalkDetails> oneHourTalkList = talkListByHours.getTalkListOf1Hour();

        if (sevenHourTalkList != null && sevenHourTalkList.size() > 0) {
            System.out.println("--- 7 Hour List ---");
            System.out.println("Size: " + sevenHourTalkList.size());
            for (int i = 0; i < sevenHourTalkList.size(); i++) {
                System.out.println("Talk " + i);
                System.out.println("Talk Title: " + sevenHourTalkList.get(i).getTalkTitle());
                System.out.println("Talk Time: " + sevenHourTalkList.get(i).getTalkTime());
            }
        }

        if (sixHourTalkList != null && sixHourTalkList.size() > 0) {
            System.out.println("--- 6 Hour List ---");
            System.out.println("Size: " + sixHourTalkList.size());
            for (int i = 0; i < sixHourTalkList.size(); i++) {
                System.out.println("Talk " + i);
                System.out.println("Talk Title: " + sixHourTalkList.get(i).getTalkTitle());
                System.out.println("Talk Time: " + sixHourTalkList.get(i).getTalkTime());
            }
        }

        if (fiveHourTalkList != null && fiveHourTalkList.size() > 0) {
            System.out.println("--- 5 Hour List ---");
            System.out.println("Size: " + fiveHourTalkList.size());
            for (int i = 0; i < fiveHourTalkList.size(); i++) {
                System.out.println("Talk " + i);
                System.out.println("Talk Title: " + fiveHourTalkList.get(i).getTalkTitle());
                System.out.println("Talk Time: " + fiveHourTalkList.get(i).getTalkTime());
            }
        }

        if (fourHourTalkList != null && fourHourTalkList.size() > 0) {
            System.out.println("--- 4 Hour List ---");
            System.out.println("Size: " + fourHourTalkList.size());
            for (int i = 0; i < fourHourTalkList.size(); i++) {
                System.out.println("Talk " + i);
                System.out.println("Talk Title: " + fourHourTalkList.get(i).getTalkTitle());
                System.out.println("Talk Time: " + fourHourTalkList.get(i).getTalkTime());
            }
        }

        if (threeHourTalkList != null && threeHourTalkList.size() > 0) {
            System.out.println("--- 3 Hour List ---");
            System.out.println("Size: " + threeHourTalkList.size());
            for (int i = 0; i < threeHourTalkList.size(); i++) {
                System.out.println("Talk " + i);
                System.out.println("Talk Title: " + threeHourTalkList.get(i).getTalkTitle());
                System.out.println("Talk Time: " + threeHourTalkList.get(i).getTalkTime());
            }
        }

        if (twoHourTalkList != null && twoHourTalkList.size() > 0) {
            System.out.println("--- 2 Hour List ---");
            System.out.println("Size: " + twoHourTalkList.size());
            for (int i = 0; i < twoHourTalkList.size(); i++) {
                System.out.println("Talk " + i);
                System.out.println("Talk Title: " + twoHourTalkList.get(i).getTalkTitle());
                System.out.println("Talk Time: " + twoHourTalkList.get(i).getTalkTime());
            }
        }

        if (oneHourTalkList != null && oneHourTalkList.size() > 0) {
            System.out.println("--- 1 Hour List ---");
            System.out.println("Size: " + oneHourTalkList.size());
            for (int i = 0; i < oneHourTalkList.size(); i++) {
                System.out.println("Talk " + i);
                System.out.println("Talk Title: " + oneHourTalkList.get(i).getTalkTitle());
                System.out.println("Talk Time: " + oneHourTalkList.get(i).getTalkTime());
            }
        }
    }

    // Method to schedule the talk titles
    private static void ScheduleTalkTitles() {

        TalkListByHours talkListByHours = new TalkListByHours();

        List<TalkDetails> sevenHourTalkList = talkListByHours.getTalkListOf7Hours();
        List<TalkDetails> sixHourTalkList = talkListByHours.getTalkListOf6Hours();
        List<TalkDetails> fiveHourTalkList = talkListByHours.getTalkListOf5Hours();
        List<TalkDetails> fourHourTalkList = talkListByHours.getTalkListOf4Hours();
        List<TalkDetails> threeHourTalkList = talkListByHours.getTalkListOf3Hours();
        List<TalkDetails> twoHourTalkList = talkListByHours.getTalkListOf2Hours();
        List<TalkDetails> oneHourTalkList = talkListByHours.getTalkListOf1Hour();

        TrackSchedule trackSchedule = new TrackSchedule();
        List<Track> trackList = new ArrayList<>();

        trackSchedule.setTrackList(trackList);

        // Method call to schedule the talk titles more than 6 hours
        ScheduleSevenHourTalkList(sevenHourTalkList, oneHourTalkList, twoHourTalkList);

        // Method call to schedule the talk titles more than 5 hours
        ScheduleMeetingLessThanSevenHours(sixHourTalkList, twoHourTalkList, threeHourTalkList);

        // Method call to schedule the talk titles more than 4 hours
        ScheduleMeetingLessThanSevenHours(fiveHourTalkList, threeHourTalkList, fourHourTalkList);

        if (fourHourTalkList.size() > 0) {

            // Method call to schedule the all the pending talk tiles in 4 hours list
            ScheduleMeetingForFourHourList(fourHourTalkList);
        }
    }

    // Method to return Lunch talk tiles between 12 PM to 1 PM
    private static Talk GetLunchTalk() {
        Talk lunchTalk = new Talk();

        lunchTalk.setTalkTitle("Lunch");
        lunchTalk.setTalkTime(60);
        lunchTalk.setTalkStartTime(LocalTime.NOON);
        lunchTalk.setTalkEndTime(LocalTime.NOON.plusHours(1));
        lunchTalk.setTalkType(Talk.TalkType.LUNCH);

        return lunchTalk;
    }

    // Method to print the talk title schedule
    private static void PrintTrackSchedule() {

        TrackSchedule trackSchedule = new TrackSchedule();

        try {

            PrintWriter writer = new PrintWriter("Output.txt", "UTF-8");

            if (trackSchedule.getTrackList() != null && trackSchedule.getTrackList().size() > 0) {

                for (Track track : trackSchedule.getTrackList()) {

                    System.out.println(track.getTrackName() + ":");
                    writer.println(track.getTrackName() + ":");
                    if (track.getTalkList() != null && track.getTalkList().size() > 0) {
                        //System.out.println("Track Size: " + track.getTalkList().size());

                        for (Talk talk : track.getTalkList()) {

                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");

                            String talkStartTime = dtf.format(talk.getTalkStartTime());
                            //String talkEndTime = dtf.format(talk.getTalkEndTime());

                            System.out.println(talkStartTime + " "
                                    //+ " - " + talkEndTime + ": "
                                    + talk.getTalkTitle());

                            writer.println(talkStartTime + " " + talk.getTalkTitle());
                        }
                        writer.println();
                    }
                }
            } else {

                writer.println("There are no talk titles in Input to schedule the event...!!!");
                System.out.println("There are no talk titles in Input to schedule the event...!!!");
            }

            writer.close();

        } catch (Exception ex) {
            System.out.println("Exception details: " + ex.getMessage());
        }
    }

    //Method to schedule meetings in seven hours list
    private static void ScheduleSevenHourTalkList(List<TalkDetails> sevenHourTalkList, List<TalkDetails> oneHourTalkList, List<TalkDetails> twoHourTalkList) {
        TrackSchedule trackSchedule = new TrackSchedule();
        List<Track> trackList = new ArrayList<>();

        if (trackSchedule.getTrackList() != null && trackSchedule.getTrackList().size() > 0) {
            trackList = trackSchedule.getTrackList();
        } else {
            trackSchedule.setTrackList(trackList);
        }

        if (sevenHourTalkList.size() > 0) {
            for (int i = 0; i < sevenHourTalkList.size(); i++) {

                if (sevenHourTalkList.get(i).getTalkTime() == 420) {
                    Track track = new Track();
                    List<Talk> talkList = new ArrayList<>();

                    // First half session

                    Talk firstHalfTalk = new Talk();
                    firstHalfTalk.setTalkTitle(sevenHourTalkList.get(i).getTalkTitle());
                    firstHalfTalk.setTalkTime(sevenHourTalkList.get(i).getTalkTime());
                    firstHalfTalk.setTalkStartTime(LocalTime.MIN.plusHours(9));
                    firstHalfTalk.setTalkEndTime(LocalTime.NOON);
                    firstHalfTalk.setTalkType(Talk.TalkType.FIRST_HALF);

                    // Lunch event
                    Talk lunchTalk = GetLunchTalk();

                    // Second half session
                    Talk secondHalfTalk = new Talk();
                    secondHalfTalk.setTalkTitle(sevenHourTalkList.get(i).getTalkTitle());
                    secondHalfTalk.setTalkTime(sevenHourTalkList.get(i).getTalkTime());
                    secondHalfTalk.setTalkStartTime(LocalTime.NOON.plusHours(1));
                    secondHalfTalk.setTalkEndTime(LocalTime.NOON.plusHours(4));
                    secondHalfTalk.setTalkType(Talk.TalkType.SECOND_HALF);

                    // Networking event
                    Talk networkingEventTalk = new Talk();
                    networkingEventTalk.setTalkTitle("Networking Event");
                    networkingEventTalk.setTalkTime(60);
                    networkingEventTalk.setTalkStartTime(LocalTime.NOON.plusHours(5));
                    networkingEventTalk.setTalkEndTime(LocalTime.NOON.plusHours(6));
                    networkingEventTalk.setTalkType(Talk.TalkType.NETWORKING_EVENT);

                    talkList.add(firstHalfTalk);
                    talkList.add(lunchTalk);
                    talkList.add(secondHalfTalk);
                    talkList.add(networkingEventTalk);

                    int trackNumber = trackSchedule.getTrackList().size() + 1;

                    track.setTrackName("Track " + trackNumber);
                    track.setTalkList(talkList);
                    trackList.add(track);
                } else {

                    int talkTime = sevenHourTalkList.get(i).getTalkTime();
                    int availableTime = 420 - talkTime;

                    Track track = new Track();
                    List<Talk> talkList = new ArrayList<>();
                    Talk firstHalfTalk = new Talk();

                    firstHalfTalk.setTalkTitle(sevenHourTalkList.get(i).getTalkTitle());
                    firstHalfTalk.setTalkTime(sevenHourTalkList.get(i).getTalkTime());
                    firstHalfTalk.setTalkStartTime(LocalTime.MIN.plusHours(9));
                    firstHalfTalk.setTalkEndTime(LocalTime.NOON);
                    firstHalfTalk.setTalkType(Talk.TalkType.FIRST_HALF);

                    Talk lunchTalk = GetLunchTalk();
                    Talk secondHalfTalk = new Talk();

                    secondHalfTalk.setTalkTitle(sevenHourTalkList.get(i).getTalkTitle());
                    secondHalfTalk.setTalkTime(sevenHourTalkList.get(i).getTalkTime());
                    secondHalfTalk.setTalkStartTime(LocalTime.NOON.plusHours(1));
                    secondHalfTalk.setTalkEndTime(LocalTime.NOON.plusMinutes(300 - availableTime));
                    secondHalfTalk.setTalkType(Talk.TalkType.SECOND_HALF);

                    talkList.add(firstHalfTalk);
                    talkList.add(lunchTalk);
                    talkList.add(secondHalfTalk);


                    List<TalkDetails> tempTalk = new ArrayList<>();

                    while (availableTime > 0) {

                        boolean isTalkAvailable = false;

                        for (int j = 0; j < oneHourTalkList.size(); j++) {


                            if (oneHourTalkList.get(j).getTalkTime() <= availableTime) {
                                isTalkAvailable = true;
                                tempTalk.add(oneHourTalkList.get(j));
                                availableTime -= oneHourTalkList.get(j).getTalkTime();
                                oneHourTalkList.remove(j);
                                break;
                            }
                        }

                        if (!isTalkAvailable) {
                            availableTime = 0;
                        }
                    }

                    if (tempTalk.size() > 0) {
                        for (TalkDetails tempTalkDetails : tempTalk) {

                            Talk additionalTalk = new Talk();

                            additionalTalk.setTalkTitle(tempTalkDetails.getTalkTitle());
                            additionalTalk.setTalkTime(tempTalkDetails.getTalkTime());
                            int numberOfTalk = talkList.size();
                            LocalTime startTime = talkList.get(numberOfTalk - 1).getTalkEndTime();
                            additionalTalk.setTalkStartTime(startTime);
                            LocalTime endTime = startTime.plusMinutes(tempTalkDetails.getTalkTime());
                            additionalTalk.setTalkEndTime(endTime);
                            additionalTalk.setTalkType(Talk.TalkType.SECOND_HALF);

                            talkList.add(additionalTalk);

                        }
                    }

                    // Add Networking Event

                    Talk networkingEventTalk = new Talk();

                    networkingEventTalk.setTalkTitle("Networking Event");
                    networkingEventTalk.setTalkTime(60);
                    int numberOfTalk = talkList.size();
                    LocalTime startTime = talkList.get(numberOfTalk - 1).getTalkEndTime();
                    networkingEventTalk.setTalkStartTime(startTime);
                    LocalTime endTime = startTime.plusMinutes(60);
                    networkingEventTalk.setTalkEndTime(endTime);
                    networkingEventTalk.setTalkType(Talk.TalkType.NETWORKING_EVENT);

                    talkList.add(networkingEventTalk);

                    int trackNumber = trackSchedule.getTrackList().size() + 1;

                    track.setTrackName("Track " + trackNumber);
                    track.setTalkList(talkList);
                    trackList.add(track);
                }
            }

            // Clear all the seven hour talk list since its all scheduled now
            sevenHourTalkList.clear();
        }

        // Move all pending talk tiles in one hour list to two hours list
        if (oneHourTalkList.size() > 0) {
            twoHourTalkList.addAll(oneHourTalkList);
            oneHourTalkList.clear();
        }
    }

    // Common method to schedule six and five hours talk titles list
    private static void ScheduleMeetingLessThanSevenHours(List<TalkDetails> MainTalkList, List<TalkDetails> CompareTalkList, List<TalkDetails> NextTalkList) {

        TrackSchedule trackSchedule = new TrackSchedule();
        List<Track> trackList = new ArrayList<>();

        if (trackSchedule.getTrackList() != null && trackSchedule.getTrackList().size() > 0) {
            trackList = trackSchedule.getTrackList();
        } else {
            trackSchedule.setTrackList(trackList);
        }


        if (MainTalkList.size() > 0) {
            for (int i = 0; i < MainTalkList.size(); i++) {
                int talkTime = MainTalkList.get(i).getTalkTime();
                int availableTime = 420 - talkTime;

                Track track = new Track();
                List<Talk> talkList = new ArrayList<>();


                Talk firstHalfTalk = new Talk();

                firstHalfTalk.setTalkTitle(MainTalkList.get(i).getTalkTitle());
                firstHalfTalk.setTalkTime(MainTalkList.get(i).getTalkTime());
                firstHalfTalk.setTalkStartTime(LocalTime.MIN.plusHours(9));
                firstHalfTalk.setTalkEndTime(LocalTime.NOON);
                firstHalfTalk.setTalkType(Talk.TalkType.FIRST_HALF);

                Talk lunchTalk = GetLunchTalk();


                Talk secondHalfTalk = new Talk();

                secondHalfTalk.setTalkTitle(MainTalkList.get(i).getTalkTitle());
                secondHalfTalk.setTalkTime(MainTalkList.get(i).getTalkTime());
                secondHalfTalk.setTalkStartTime(LocalTime.NOON.plusHours(1));
                secondHalfTalk.setTalkEndTime(LocalTime.NOON.plusMinutes(300 - availableTime));
                secondHalfTalk.setTalkType(Talk.TalkType.SECOND_HALF);

                talkList.add(firstHalfTalk);
                talkList.add(lunchTalk);
                talkList.add(secondHalfTalk);

                List<TalkDetails> tempTalk = new ArrayList<>();

                while (availableTime > 0) {

                    boolean isTalkAvailable = false;

                    for (int j = 0; j < CompareTalkList.size(); j++) {

                        if (CompareTalkList.get(j).getTalkTime() <= availableTime) {
                            isTalkAvailable = true;
                            tempTalk.add(CompareTalkList.get(j));
                            availableTime -= CompareTalkList.get(j).getTalkTime();
                            CompareTalkList.remove(j);
                            break;
                        }
                    }

                    if (!isTalkAvailable) {
                        availableTime = 0;
                    }
                }

                if (tempTalk.size() > 0) {
                    for (TalkDetails tempTalkDetails : tempTalk) {

                        Talk additionalTalk = new Talk();

                        additionalTalk.setTalkTitle(tempTalkDetails.getTalkTitle());
                        additionalTalk.setTalkTime(tempTalkDetails.getTalkTime());
                        int numberOfTalk = talkList.size();
                        LocalTime startTime = talkList.get(numberOfTalk - 1).getTalkEndTime();
                        additionalTalk.setTalkStartTime(startTime);
                        LocalTime endTime = startTime.plusMinutes(tempTalkDetails.getTalkTime());
                        additionalTalk.setTalkEndTime(endTime);
                        additionalTalk.setTalkType(Talk.TalkType.SECOND_HALF);

                        talkList.add(additionalTalk);

                    }
                }

                int totalTimeOccupied = 0;

                for (Talk talk : talkList) {
                    if (talk.getTalkType() != Talk.TalkType.LUNCH) {
                        long timeOfTalk = Duration.between(talk.getTalkStartTime(), talk.getTalkEndTime()).toMinutes();
                        totalTimeOccupied += timeOfTalk;
                    }

                }

                if (totalTimeOccupied < 360) {

                    int timeToPushTalk = 360 - totalTimeOccupied;

                    for (Talk talk : talkList) {


                        if (talk.getTalkType() == Talk.TalkType.FIRST_HALF) {
                            talk.setTalkStartTime(talk.getTalkStartTime().plusMinutes(timeToPushTalk));
                            if (!talk.getTalkEndTime().equals(LocalTime.NOON)) {
                                talk.setTalkEndTime(talk.getTalkEndTime().plusMinutes(timeToPushTalk));
                            }
                        } else if (talk.getTalkType() == Talk.TalkType.SECOND_HALF) {
                            talk.setTalkEndTime(talk.getTalkEndTime().plusMinutes(timeToPushTalk));
                            if (!talk.getTalkStartTime().equals(LocalTime.NOON.plusHours(1))) {
                                talk.setTalkStartTime(talk.getTalkStartTime().plusMinutes(timeToPushTalk));
                            }
                        }
                    }
                }

                // Add Networking Event

                Talk networkingEventTalk = new Talk();

                networkingEventTalk.setTalkTitle("Networking Event");
                networkingEventTalk.setTalkTime(60);
                int numberOfTalk = talkList.size();
                LocalTime startTime = talkList.get(numberOfTalk - 1).getTalkEndTime();
                networkingEventTalk.setTalkStartTime(startTime);
                LocalTime endTime = startTime.plusMinutes(60);
                networkingEventTalk.setTalkEndTime(endTime);
                networkingEventTalk.setTalkType(Talk.TalkType.NETWORKING_EVENT);

                talkList.add(networkingEventTalk);

                int trackNumber = trackSchedule.getTrackList().size() + 1;

                track.setTrackName("Track " + trackNumber);
                track.setTalkList(talkList);
                trackList.add(track);
            }
        }
        MainTalkList.clear();


        if (CompareTalkList.size() > 0) {
            NextTalkList.addAll(CompareTalkList);
            CompareTalkList.clear();
        }
    }

    // Method to schedule four hour talk titles
    private static void ScheduleMeetingForFourHourList(List<TalkDetails> FourHourTalkList) {


        TrackSchedule trackSchedule = new TrackSchedule();
        List<Track> trackList = new ArrayList<>();

        if (trackSchedule.getTrackList() != null && trackSchedule.getTrackList().size() > 0) {
            trackList = trackSchedule.getTrackList();
        } else {
            trackSchedule.setTrackList(trackList);
        }

        int totalTalkTimeInFourHourList;


        while (FourHourTalkList.size() > 0) {

            int secondHalftalkTime = FourHourTalkList.get(0).getTalkTime();


            Track track = new Track();
            List<Talk> talkList = new ArrayList<>();

            List<Talk> tempSecondHalfTalkList = new ArrayList<>();
            List<Talk> tempFirstHalfTalkList = new ArrayList<>();


            Talk secondHalfTalk = new Talk();

            secondHalfTalk.setTalkTitle(FourHourTalkList.get(0).getTalkTitle());
            secondHalfTalk.setTalkTime(FourHourTalkList.get(0).getTalkTime());
            secondHalfTalk.setTalkStartTime(LocalTime.NOON.plusHours(1));
            secondHalfTalk.setTalkEndTime(LocalTime.NOON.plusHours(1).plusMinutes(secondHalftalkTime));
            secondHalfTalk.setTalkType(Talk.TalkType.SECOND_HALF);

            tempSecondHalfTalkList.add(secondHalfTalk);

            FourHourTalkList.remove(0);

            totalTalkTimeInFourHourList = CalculateTotalTalkTime(FourHourTalkList);

            if (totalTalkTimeInFourHourList > 0) {
                int availableTimeInNoonSession = 240 - secondHalftalkTime;
                int availableTimeInMorningSession = 180;

                List<TalkDetails> tempSecondHalfTalk = new ArrayList<>();
                List<TalkDetails> tempFirstHalfTalk = new ArrayList<>();
                List<Integer> indexToBeDeleted = new ArrayList<>();

                while (availableTimeInNoonSession > 0 || availableTimeInMorningSession > 0) {

                    boolean isTalkAvailable = false;

                    for (int j = 0; j < FourHourTalkList.size(); j++) {

                        if (FourHourTalkList.get(j).getTalkTime() <= availableTimeInNoonSession && !indexToBeDeleted.contains(j)) {

                            isTalkAvailable = true;
                            tempSecondHalfTalk.add(FourHourTalkList.get(j));
                            indexToBeDeleted.add(j);
                            availableTimeInNoonSession -= FourHourTalkList.get(j).getTalkTime();
                        } else if (FourHourTalkList.get(j).getTalkTime() <= availableTimeInMorningSession && !indexToBeDeleted.contains(j)) {
                            isTalkAvailable = true;
                            tempFirstHalfTalk.add(FourHourTalkList.get(j));
                            indexToBeDeleted.add(j);
                            availableTimeInMorningSession -= FourHourTalkList.get(j).getTalkTime();
                        }
                    }

                    if (FourHourTalkList.size() == tempSecondHalfTalk.size() + tempFirstHalfTalk.size()) {
                        break;
                    }

                    if (!isTalkAvailable) {
                        availableTimeInNoonSession = 0;
                        availableTimeInMorningSession = 0;
                    }
                }

                if (tempSecondHalfTalk.size() > 0 || tempFirstHalfTalk.size() > 0) {
                    for (TalkDetails tempTalkDetails : tempSecondHalfTalk) {

                        Talk additionalTalk = new Talk();

                        additionalTalk.setTalkTitle(tempTalkDetails.getTalkTitle());
                        additionalTalk.setTalkTime(tempTalkDetails.getTalkTime());
                        int numberOfTalk = tempSecondHalfTalkList.size();
                        LocalTime startTime = tempSecondHalfTalkList.get(numberOfTalk - 1).getTalkEndTime();
                        additionalTalk.setTalkStartTime(startTime);
                        LocalTime endTime = startTime.plusMinutes(tempTalkDetails.getTalkTime());
                        additionalTalk.setTalkEndTime(endTime);
                        additionalTalk.setTalkType(Talk.TalkType.SECOND_HALF);
                        tempSecondHalfTalkList.add(additionalTalk);
                    }

                    int talkCount = 0;

                    for (TalkDetails tempTalkDetails : tempFirstHalfTalk) {

                        Talk additionalTalk = new Talk();

                        additionalTalk.setTalkTitle(tempTalkDetails.getTalkTitle());
                        additionalTalk.setTalkTime(tempTalkDetails.getTalkTime());
                        int numberOfTalk = tempFirstHalfTalkList.size();

                        LocalTime startTime;

                        if (talkCount > 0) {
                            int index = numberOfTalk - 1;
                            startTime = tempFirstHalfTalkList.get(index).getTalkEndTime();

                        } else {
                            startTime = LocalTime.MIN.plusHours(9);
                        }

                        additionalTalk.setTalkStartTime(startTime);
                        LocalTime endTime = startTime.plusMinutes(tempTalkDetails.getTalkTime());
                        additionalTalk.setTalkEndTime(endTime);
                        additionalTalk.setTalkType(Talk.TalkType.FIRST_HALF);
                        tempFirstHalfTalkList.add(additionalTalk);

                        talkCount++;
                    }

                    Collections.sort(indexToBeDeleted, new SortIndex());

                    for (int index = 0; index < indexToBeDeleted.size(); index++) {
                        int x = indexToBeDeleted.get(index);
                        FourHourTalkList.remove(x);
                    }

                }
            }

            // Code to find time gap and adjust the talk time as per time gap

            if (tempFirstHalfTalkList.size() > 0) {
                int totalTimeOccupiedInMorningSession = 0;

                for (Talk talk : tempFirstHalfTalkList) {

                    long timeOfTalk = Duration.between(talk.getTalkStartTime(), talk.getTalkEndTime()).toMinutes();
                    totalTimeOccupiedInMorningSession += timeOfTalk;
                }

                if (totalTimeOccupiedInMorningSession < 180) {

                    int timeToPushTalk = 180 - totalTimeOccupiedInMorningSession;

                    for (Talk talk : tempFirstHalfTalkList) {

                        talk.setTalkStartTime(talk.getTalkStartTime().plusMinutes(timeToPushTalk));
                        talk.setTalkEndTime(talk.getTalkEndTime().plusMinutes(timeToPushTalk));

                    }
                }


                Talk lunchTalk = GetLunchTalk();

                talkList.addAll(tempFirstHalfTalkList);
                talkList.add(lunchTalk);
            }

            int totalTimeOccupiedInNoonSession = 0;

            for (Talk talk : tempSecondHalfTalkList) {

                long timeOfTalk = Duration.between(talk.getTalkStartTime(), talk.getTalkEndTime()).toMinutes();
                totalTimeOccupiedInNoonSession += timeOfTalk;
            }

            if (totalTimeOccupiedInNoonSession < 180) {

                int timeToPushTalk = 180 - totalTimeOccupiedInNoonSession;

                for (Talk talk : tempSecondHalfTalkList) {

                    talk.setTalkStartTime(talk.getTalkStartTime().plusMinutes(timeToPushTalk));
                    talk.setTalkEndTime(talk.getTalkEndTime().plusMinutes(timeToPushTalk));

                }

                for (Talk talk : tempFirstHalfTalkList) {

                    talk.setTalkStartTime(talk.getTalkStartTime().plusMinutes(timeToPushTalk));
                    talk.setTalkEndTime(talk.getTalkEndTime().plusMinutes(timeToPushTalk));

                    if (tempFirstHalfTalkList.size() == 1) {
                        Talk talkToBeAddedInNoonSession = new Talk();

                        talkToBeAddedInNoonSession.setTalkTitle(talk.getTalkTitle());
                        talkToBeAddedInNoonSession.setTalkTime(talk.getTalkTime());
                        talkToBeAddedInNoonSession.setTalkStartTime(LocalTime.NOON.plusHours(1));
                        talkToBeAddedInNoonSession.setTalkEndTime(LocalTime.NOON.plusHours(1).plusMinutes(timeToPushTalk));
                        talkToBeAddedInNoonSession.setTalkType(Talk.TalkType.SECOND_HALF);

                        tempSecondHalfTalkList.add(0, talkToBeAddedInNoonSession);
                    }
                }
            }

            talkList.addAll(tempSecondHalfTalkList);

            // Add Networking Event

            Talk networkingEventTalk = new Talk();

            networkingEventTalk.setTalkTitle("Networking Event");
            networkingEventTalk.setTalkTime(60);
            int numberOfTalk = tempSecondHalfTalkList.size();
            LocalTime startTime = tempSecondHalfTalkList.get(numberOfTalk - 1).getTalkEndTime();

            networkingEventTalk.setTalkStartTime(startTime);
            LocalTime endTime = startTime.plusMinutes(60);
            networkingEventTalk.setTalkEndTime(endTime);
            networkingEventTalk.setTalkType(Talk.TalkType.NETWORKING_EVENT);

            talkList.add(networkingEventTalk);

            int trackNumber = trackSchedule.getTrackList().size() + 1;

            track.setTrackName("Track " + trackNumber);
            track.setTalkList(talkList);
            trackList.add(track);

            totalTalkTimeInFourHourList = CalculateTotalTalkTime(FourHourTalkList);
            if (totalTalkTimeInFourHourList == 0) {
                break;
            }
        }
    }

    // Method to calculate the total time left in the given talk details list
    private static int CalculateTotalTalkTime(List<TalkDetails> TalkDetailsList) {
        int totalTalkTime = 0;

        if (TalkDetailsList.size() > 0) {
            for (TalkDetails talkDetails : TalkDetailsList) {

                totalTalkTime += talkDetails.getTalkTime();
            }
        }

        return totalTalkTime;
    }
}

// Class to sort the Talk Details by talk title time
class SortByTalkTime implements Comparator<TalkDetails> {

    @Override
    public int compare(TalkDetails td1, TalkDetails td2) {
        return td2.getTalkTime() - td1.getTalkTime();
    }
}

// Class to sort the List of Integer in ascending order
class SortIndex implements Comparator<Integer> {

    @Override
    public int compare(Integer i1, Integer i2) {
        return i2 - i1;
    }
}