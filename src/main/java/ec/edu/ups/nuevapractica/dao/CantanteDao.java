package ec.edu.ups.nuevapractica.dao;

import ec.edu.ups.nuevapractica.idao.ICantanteDao;
import ec.edu.ups.nuevapractica.modelo.Cantante;
import ec.edu.ups.nuevapractica.modelo.Disco;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CantanteDao implements ICantanteDao {
    private List<Cantante> listaCantantes;
    private final String FILE_PATH = "src\\test\\java\\RutaDinamica\\nombrearchivo.dat";

    public CantanteDao() {
        listaCantantes = loadFromFile();
    }

    @Override
    public void create(Cantante cantante) {
        listaCantantes.add(cantante);
        saveToFile(listaCantantes);
    }

    @Override
    public Cantante read(int id) {
        for (Cantante cantante : listaCantantes) {
            if (cantante.getCodigo() == id) {
                return cantante;
            }
        }
        return null;
    }

    @Override
    public void update(Cantante cantante) {
        for (int i = 0; i < listaCantantes.size(); i++) {
            Cantante c = listaCantantes.get(i);
            if (c.getCodigo() == cantante.getCodigo()) {
                listaCantantes.set(i, cantante);
                saveToFile(listaCantantes);
                break;
            }
        }
    }

    @Override
    public void delete(Cantante cantante) {
        listaCantantes.removeIf(c -> c.getCodigo() == cantante.getCodigo());
        saveToFile(listaCantantes);
    }

    @Override
    public Cantante buscarPorNombreDeDisco(String valor) {
        for (Cantante cantante : listaCantantes) {
            for (Disco disco : cantante.getDiscos()) {
                if (disco.getNombre().equals(valor)) {
                    return cantante;
                }
            }
        }
        return null;
    }

    @Override
    public List<Cantante> findAll() {
        return listaCantantes;
    }

    private void saveToFile(List<Cantante> cantantes) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            outputStream.writeObject(cantantes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Cantante> loadFromFile() {
        List<Cantante> cantantes = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            cantantes = (List<Cantante>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            
        }
        return cantantes;
    }
    
}
