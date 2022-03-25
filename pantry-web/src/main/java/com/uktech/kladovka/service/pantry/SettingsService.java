package com.uktech.kladovka.service.pantry;


import com.uktech.pantry.domain.Settings;

import com.uktech.pantry.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    public Settings getUserSettings(Long userId){
        Settings setting = settingsRepository.findByUserId(userId);
        return setting;
    }

    public void save(Settings settings) {
        settingsRepository.save(settings);
    }
}
