package com.uktech.pantry.repository;



import com.uktech.pantry.domain.Settings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends CrudRepository<Settings, Long> {

    Settings findByUserId(long userId);
}
