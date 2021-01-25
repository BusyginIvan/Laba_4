package shorties.professions;

import shorties.ProfessionInterface;

public interface ScientistInterface extends ProfessionInterface {
    String professionName = "Учёный";
    //void suggestNumberOfSeats(int n);
    int getIQ();
    boolean isMan();
}