package com.drogariadopovo.domain.repository

import com.drogariadopovo.domain.model.Constants
import io.reactivex.Single

interface ConstantsRepository{
    fun getConstants() : Single<Constants>

    fun saveConstants(constants: Constants)
}