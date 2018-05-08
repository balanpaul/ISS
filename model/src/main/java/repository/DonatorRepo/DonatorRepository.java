package repository.DonatorRepo;
import entities.Donator;
import repository.AbstractCRUDRepository;

public class DonatorRepository extends AbstractCRUDRepository<Donator> {
    public Donator update(int id, Donator echipa) {
        for(int i=0;i<list.size();i++){
            if(list.get(i).getCnp()==id){
                list.set(i,echipa);
            }
        }
        return echipa;
    }

    @Override
    public Donator delete(Donator b) {
        for (int i=0;i<list.size();i++){
            if(list.get(i).getCnp()==b.getCnp()){
                list.remove(i);
            }
        }
        return b;
    }

    public Donator findOne(int id){
        for(Donator b:getList()){
            if(b.getCnp()==id)
                return b;
        }
        return null;
    }
}

