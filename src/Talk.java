import java.time.LocalTime;

public class Talk {

    public enum TalkType
    {
        FIRST_HALF, LUNCH, SECOND_HALF, NETWORKING_EVENT
    }

    private LocalTime talkStartTime;
    private LocalTime talkEndTime;
    private String talkTitle;
    private int talkTime;
    private TalkType talkType;

    public LocalTime getTalkStartTime() {
        return talkStartTime;
    }

    public void setTalkStartTime(LocalTime talkStartTime) {
        this.talkStartTime = talkStartTime;
    }

    public LocalTime getTalkEndTime() {
        return talkEndTime;
    }

    public void setTalkEndTime(LocalTime talkEndTime) {
        this.talkEndTime = talkEndTime;
    }

    public String getTalkTitle() {
        return talkTitle;
    }

    public void setTalkTitle(String talkTitle) {
        this.talkTitle = talkTitle;
    }

    public int getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(int talkTime) {
        this.talkTime = talkTime;
    }

    public TalkType getTalkType() {
        return talkType;
    }

    public void setTalkType(TalkType talkType) {
        this.talkType = talkType;
    }
}
