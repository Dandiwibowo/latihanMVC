package dw.tyhmeleaf.paractice1.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import dw.tyhmeleaf.paractice1.entity.DataForm;

public interface IndexRepo extends PagingAndSortingRepository <DataForm, Long>{
    public List<DataForm> findByFirstname(String firstname);

	public List<DataForm> findByEmail(String email);
}
