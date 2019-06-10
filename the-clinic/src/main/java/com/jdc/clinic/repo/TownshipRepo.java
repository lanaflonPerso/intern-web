package com.jdc.clinic.repo;

import java.util.List;

import com.jdc.clinic.entity.Township;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface TownshipRepo extends BaseRepository<Township, Integer>{

	List<Township> findByDivisionId(int divisionId);

}
