package com.tome.main.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tome.main.Enitities.Headrefs;
@Repository
public interface HeadrefsRepo extends JpaRepository<Headrefs, Integer> {
	
	
	@Query(value="SELECT value FROM headrefs WHERE parameter='max_XP'", nativeQuery=true)
	public int getMaxXp();

}
