package repository.CentruRepo;

import entities.Centru;
import repository.AbstractCRUDRepository;

public class CentruRepository extends AbstractCRUDRepository<Centru> {
    public Centru update(int id, Centru echipa) {
        for(int i=0;i<list.size();i++){
            if(list.get(i).getIdCentru()==id){
                list.set(i,echipa);
            }
        }
        return echipa;
    }

    @Override
    public Centru delete(Centru b) {
        for (int i=0;i<list.size();i++){
            if(list.get(i).getIdCentru()==b.getIdCentru()){
                list.remove(i);
            }
        }
        return b;
    }

    public Centru findOne(int id){
        for(Centru b:getList()){
            if(b.getIdCentru()==id)
                return b;
        }
        return null;
    }
}
