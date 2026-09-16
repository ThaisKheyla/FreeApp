package com.example.freeapp.domain.usecase.location

import com.example.freeapp.domain.StateOptionData
import com.example.freeapp.domain.repository.LocationRepository

class LoadStatesUseCase(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(): Result<List<StateOptionData>> {
        return locationRepository.buscarEstados()
    }
}
