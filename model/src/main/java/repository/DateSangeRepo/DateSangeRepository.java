package repository.DateSangeRepo;
import entities.Clinica;
import repository.AbstractCRUDRepository;

public class DateSangeRepository extends AbstractCRUDRepository<Clinica> {
    public Clinica update(int id, Clinica echipa) {
        for(int i=0;i<list.size();i++){
            if(list.get(i).getIdClinica()==id){
                list.set(i,echipa);
            }
        }
        return echipa;
    }

    @Override
    public Clinica delete(Clinica b) {
        for (int i=0;i<list.size();i++){
            if(list.get(i).getIdClinica()==b.getIdClinica()){
                list.remove(i);
            }
        }
        return b;
    }

    public Clinica findOne(int id){
        for(Clinica b:getList()){
            if(b.getIdClinica()==id)
                return b;
        }
        return null;
    }
}

