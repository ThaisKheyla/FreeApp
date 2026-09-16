package com.example.freeapp.domain.usecase.location

import com.example.freeapp.domain.repository.LocationRepository

class LoadCitiesUseCase(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(
        stateCode: String
    ): Result<List<String>> {
        return locationRepository.buscarCidades(stateCode)
    }
}
