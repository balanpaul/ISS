package repository.BoalaRepo;

import entities.Boala;
import repository.AbstractCRUDRepository;

public class BoalaRepository extends AbstractCRUDRepository<Boala> {

    @Override
    public Boala update(int id, Boala echipa) {
        for(int i=0;i<list.size();i++){
            if(list.get(i).getIdBoala()==id){
                list.set(i,echipa);
            }
        }
        return echipa;
    }

    @Override
    public Boala delete(Boala b) {
        for (int i=0;i<list.size();i++){
            if(list.get(i).getIdBoala()==b.getIdBoala()){
                list.remove(i);
            }
        }
        return b;
    }

    public Boala findOne(int id){
        for(Boala b:getList()){
            if(b.getIdBoala()==id)
                return b;
        }
        return null;
    }
}
