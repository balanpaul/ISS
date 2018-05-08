package repository;

import java.util.ArrayList;

public abstract class AbstractCRUDRepository<T> {
    public ArrayList<T> list = new ArrayList<>();

    public int size(){
        return list.size();
    }

    public ArrayList<T> getList() {
        return list;
    }

    public T add(T t){
        list.add(t);
        return t;
    }

    public T update(int id, T t){

        return t;
    }

    public T delete(T t){
        return t;
    }


    public T findOne(int id){

        return null;
    }

}

