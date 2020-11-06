package com.drogariadopovo.domain.model

import com.drogariadopovo.domain.model.Branch
import com.drogariadopovo.domain.model.Role

data class Constants(
    val roles: List<Role>,
    val branches: List<Branch>
)