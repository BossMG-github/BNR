package Interop;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// NyetHack이 아닌 다른 왕국의 괴물을 나타내는 클래스
public class Jhava {

    private int hitPoints = 52489112;
    private String greeting = "BLARGH";

    @NotNull
    public String utterGreeting() {
        return greeting;
    }

    @Nullable
    public String determineFriendshipLevel() {
        return null;
    }

    public int getHitPoints(){
        return hitPoints;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public void offerFood() {
        Hero.handOverFood("피자");
    }

    public static void main(String[] args) {
        System.out.println(Hero.makeProclamation());
    }
}
