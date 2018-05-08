package repository.PersonalCliniciRepo;
import entities.PersonalClinici;
import repository.AbstractCRUDRepository;

public class PersonalCliniciRepository extends AbstractCRUDRepository<PersonalClinici> {
    public PersonalClinici update(int id, PersonalClinici echipa) {
        for(int i=0;i<list.size();i++){
            if(list.get(i).getIdPersonal()==id){
                list.set(i,echipa);
            }
        }
        return echipa;
    }

    @Override
    public PersonalClinici delete(PersonalClinici b) {
        for (int i=0;i<list.size();i++){
            if(list.get(i).getIdPersonal()==b.getIdPersonal()){
                list.remove(i);
            }
        }
        return b;
    }

    public PersonalClinici findOne(int id){
        for(PersonalClinici b:getList()){
            if(b.getIdPersonal()==id)
                return b;
        }
        return null;
    }
}

