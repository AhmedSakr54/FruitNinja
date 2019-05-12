package gameModel;

import java.util.Random;


public enum ObjectType {


    APPLE("View/Resources/FruitImages/apple.png","View/Resources/FruitImages/apple-1.png","View/Resources/FruitImages/apple-2.png"),
    BANANA("View/Resources/FruitImages/banana.png","View/Resources/FruitImages/banana-2.png" , "View/Resources/FruitImages/banana-1.png"),
    WATERMELON("View/Resources/FruitImages/sandia.png" , "View/Resources/FruitImages/sandia-1.png" , "View/Resources/FruitImages/sandia-2.png"),
    PEACH("View/Resources/FruitImages/peach.png" , "View/Resources/FruitImages/peach-1.png" , "View/Resources/FruitImages/peach-2.png"),
    BASAHA("View/Resources/FruitImages/basaha.png" , "View/Resources/FruitImages/basaha-1.png" , "View/Resources/FruitImages/basaha-2.png"),
    FATALBOMB("View/Resources/FruitImages/boom.png" , null,null),
    DAMAGEBOMB("View/Resources/FruitImages/boom1.png" , null , null);


    private String firstURL;
    private String secondURL;
    private String thirdURL;

    public String getFirstURL(){
        return this.firstURL;
    }
    public String getSecondURL(){return this.secondURL;}
    public String getThridURL(){return this.thirdURL;}

    ObjectType(String firstURL , String secondURL , String thirdURL){

        this.firstURL = firstURL;
        this.secondURL = secondURL;
        this.thirdURL = thirdURL;
    }

    private static final Random random = new Random();

    public static ObjectType randomObject() {
        return ObjectType.values()[random.nextInt(ObjectType.values().length)];
    }
}
