package dw.tyhmeleaf.paractice1.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dw.tyhmeleaf.paractice1.entity.DataForm;
import dw.tyhmeleaf.paractice1.repo.IndexRepo;

@Service ("indexService")
@Transactional
public class IndexService {
    
    @Autowired
    private IndexRepo indexRepo;

    public DataForm save(DataForm form){
        return indexRepo.save(form);
    }

    public Iterable<DataForm> findAll(){
        return indexRepo.findAll();
    }

    public List<DataForm> findAll(int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);
        return indexRepo.findAll(pageable).getContent();
    }

    public List<DataForm> findByFirstname(String firstname){
        return indexRepo.findByFirstname(firstname);
    }

    public DataForm findByEmail(String email){
        return indexRepo.findByEmail(email);
    }
    
    public boolean delete(Long id) {
		indexRepo.deleteById(id);
		return true;
	}
}
