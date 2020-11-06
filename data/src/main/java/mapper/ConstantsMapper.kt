package mapper

import com.drogariadopovo.domain.model.Constants
import response.ConstantsResponse
import javax.inject.Inject

class ConstantsMapper @Inject constructor(private val branchMapper: BranchMapper,
                                          private val roleMapper: RoleMapper){

    fun map(response : ConstantsResponse) : Constants {
        return Constants(
                branches = branchMapper.map(response.branches),
                roles = roleMapper.map(response.roles)
        )
    }
}