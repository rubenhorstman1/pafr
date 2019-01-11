package persistence;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoad {
    public static void SaveObject (Object object) {
        try {
            FileOutputStream fileOut = new FileOutputStream("trains.obj");
            if((fileOut != null)) {
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(object);
                objectOut.close();
                System.out.println("wrote: " + object);
			}
        } catch (Exception e){
            System.out.println("Something went wrong while saving");
        }
    }

    public static Object LoadObject () {
        try {
            FileInputStream filein = new FileInputStream("trains.obj");
            ObjectInputStream objectin = new ObjectInputStream(filein);
            Object toreturn = objectin.readObject();
            objectin.close();
            System.out.println("Loaded " + toreturn);
            return toreturn;
        } catch (Exception e) {
            System.out.println("Data corrupt, or no data exists");
        }
        return null;
    }
}
