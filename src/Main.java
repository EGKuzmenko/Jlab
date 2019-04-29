import team.HabitatController;
import team.HabitatModel;
import team.HabitatView;

public class Main {

    public static void main(String[] args) {

        HabitatView view = new HabitatView(750, 560, 100, 100);
        HabitatModel habitat = new HabitatModel(0.5, 25,
                                                1,
                                                1);
        HabitatController controllers = new HabitatController(view, habitat);
    }

}
