package com.uktech.kladovka.service.pantry;


import com.uktech.pantry.domain.Settings;
import com.uktech.pantry.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    @Autowired
    private SettingRepository settingRepository;

    public Settings getUserSettings(Long userId){
        Settings setting = settingRepository.findByUserId(userId);
        return setting;
    }

    public void save(Settings settings) {
        settingRepository.save(settings);
    }
}
