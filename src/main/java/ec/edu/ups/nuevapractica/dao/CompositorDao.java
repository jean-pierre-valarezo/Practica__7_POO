package ec.edu.ups.nuevapractica.dao;

import ec.edu.ups.nuevapractica.idao.ICompositorDao;
import ec.edu.ups.nuevapractica.modelo.Cancion;
import ec.edu.ups.nuevapractica.modelo.Compositor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CompositorDao implements ICompositorDao {
    private final String FILE_PATH = "src\\test\\java\\RutaDinamica\\nombrearchivo.dat"; // Ruta del archivo binario
    private List<Compositor> listaCompositores;
   
    private CantanteDao cantanteDao;

    public CompositorDao(CantanteDao cantanteDao) {
        listaCompositores = loadFromFile();
        this.cantanteDao = cantanteDao;
    }

    public void create(Compositor compositor) {
        listaCompositores.add(compositor);
        saveToFile(listaCompositores);
    }

    public Compositor read(int codigo) {
        for (Compositor compositor : listaCompositores) {
            if (compositor.getCodigo() == codigo) {
                return compositor;
            }
        }
        return null;
    }

    public void update(Compositor compositorActualizado) {
        for (int i = 0; i < listaCompositores.size(); i++) {
            Compositor compositor = listaCompositores.get(i);
            if (compositor.getCodigo() == compositorActualizado.getCodigo()) {
                listaCompositores.set(i, compositorActualizado);
                break;
            }
        }
        saveToFile(listaCompositores);
    }

    public void delete(int codigo) {
        listaCompositores.removeIf(compositor -> compositor.getCodigo() == codigo);
        saveToFile(listaCompositores);
    }

    public List<Compositor> findAll() {
        return listaCompositores;
    }

    private void saveToFile(List<Compositor> compositores) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            outputStream.writeObject(compositores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Compositor> loadFromFile() {
        List<Compositor> compositores = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            compositores = (List<Compositor>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return compositores;
    }

    private static Compositor parseCompositor(String compositorData) {
        // Implementa el parseo del String si lo necesitas para leer desde el archivo de texto
        // Retorna el objeto Compositor creado
    }
    
}