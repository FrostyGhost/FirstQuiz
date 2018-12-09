package com.fg.frostyghost.firstquizpj.LevelsSettings;

public class Level {
    private int id;
    private String emojiId_1;
    private String emojiId_2;
    private String emojiId_3;
    private String trueAnswer;
    private String falseAnswer_1;
    private String falseAnswer_2;
    private String falseAnswer_3;
    private int levelStatus;

    public void setLevelStatus(int levelStatus) {
        this.levelStatus = levelStatus;
    }

    public Level(int id, String emojiId_1, String emojiId_2, String emojiId_3, String trueAnswer, String falseAnswer_1, String falseAnswer_2, String falseAnswer_3, int levelStatus) {
        this.id = id;
        this.emojiId_1 = emojiId_1;
        this.emojiId_2 = emojiId_2;
        this.emojiId_3 = emojiId_3;
        this.trueAnswer = trueAnswer;
        this.falseAnswer_1 = falseAnswer_1;
        this.falseAnswer_2 = falseAnswer_2;
        this.falseAnswer_3 = falseAnswer_3;

        this.levelStatus = levelStatus;
    }

    public int getId() {
        return id;
    }

    public String getEmojiId_1() {
        return emojiId_1;
    }

    public String getEmojiId_2() {
        return emojiId_2;
    }

    public String getEmojiId_3() {
        return emojiId_3;
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public String getFalseAnswer_1() {
        return falseAnswer_1;
    }

    public String getFalseAnswer_2() {
        return falseAnswer_2;
    }

    public String getFalseAnswer_3() {
        return falseAnswer_3;
    }

    public int getLevelStatus() {
        return levelStatus;
    }
}
