package repository.PersonalRepo;
import entities.Personal;
import repository.AbstractCRUDRepository;

public class PersonalRepository extends AbstractCRUDRepository<Personal> {
    public Personal update(int id, Personal echipa) {
        for(int i=0;i<list.size();i++){
            if(list.get(i).getIdPersonal()==id){
                list.set(i,echipa);
            }
        }
        return echipa;
    }

    @Override
    public Personal delete(Personal b) {
        for (int i=0;i<list.size();i++){
            if(list.get(i).getIdPersonal()==b.getIdPersonal()){
                list.remove(i);
            }
        }
        return b;
    }

    public Personal findOne(int id){
        for(Personal b:getList()){
            if(b.getIdPersonal()==id)
                return b;
        }
        return null;
    }
}

