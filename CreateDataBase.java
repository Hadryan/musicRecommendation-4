import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateDataBase 
{
    public static void main(String[] args) 
    {
        String path = "C:\\Users\\DIEGO\\Desktop\\music.txt";
        Scanner read = new Scanner(System.in);
        ArrayList<String> l = new ArrayList<>();
        for(int i = 0; i < 40; i++)
        {
            System.out.println("--------------------------------------------------------------------");
            System.out.println("\n nombre de la canción: ");
            String cancion = read.nextLine();
            System.out.println("\n Banda o cantante: ");
            String artista = read.nextLine();
            System.out.println("\n álbum de la canción: ");
            String album = read.nextLine();
            System.out.println("\n año de salida: ");
            String año = read.nextLine();
            System.out.println("\n Género: ");
            String genre = read.nextLine();
            String registro = cancion + "|" + artista  + "|" + album  + "|" +  año  + "|" + genre + "\n";
            l.add(registro);
            System.out.println("--------------------------------------------------------------------");
        }
        String[] canciones = l.toArray(String[]::new);
        escribir(path, canciones);
        read.close();
    }    

    public static void escribir(String path, String[] content)
    {
        try 
        {
            File arch = new File(path);
            FileWriter a = new FileWriter(arch);
            BufferedWriter bw = new BufferedWriter(a);
            for(String i: content)
            {
                bw.append(i);
            }
            bw.close();
        } 
        catch (Exception e) 
        {
            
        }
        /**
         * throws IOException {
    String str = "Hello";
    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
    writer.write(str);
    
    writer.close();
         */
    }
}
